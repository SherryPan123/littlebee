package cn.edu.dhu.library.littlebee.service.impl;

import cn.edu.dhu.library.littlebee.entity.Resource;
import cn.edu.dhu.library.littlebee.repository.ResourceRepository;
import cn.edu.dhu.library.littlebee.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by sherry on 15-12-29.
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);

    @Autowired
    private ResourceRepository resourceRepository;

    private Sort sortByTimeDesc() {
        return new Sort(Sort.Direction.DESC, "createdDate");
    }

    public String calcDigest(byte[] bytes) throws NoSuchAlgorithmException {
        // calculate the file checksum
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bytes);
        byte digestByteData[] = md.digest();

        //convert the byte to hex format
        StringBuilder sb = new StringBuilder();
        for (byte byteData : digestByteData) {
            sb.append(Integer.toString((byteData & 0xff) + 0x100, 16).substring(1));
        }
        logger.info("File digest Hex format: {}", sb.toString());
        return sb.toString();
    }

    @Override
    public Resource findByDigest(String digest) {
        return resourceRepository.findByDigest(digest);
    }

    @Override
    public Resource save(Resource resource) {
        return resourceRepository.save(resource);
    }

    public String getFilePath(String digest, String originalFilename) {
        String filename = "uploads";
        int []div = { 4, 8, 20, 32 };
        int pre = 0;
        for( int i = 0; i < 4; i++){
            String s = digest.substring(pre, pre + div[i]);
            filename += File.separator + s;
            pre = pre + div[i];
        }
        new File(filename).mkdirs();  // create directories recursively
        filename += File.separator + originalFilename;
        System.out.println(filename); //
        return filename;
    }

    @Override
    public Page<Resource> getResourcesByType(String type, Integer page, int size) {
        Pageable pageable = new PageRequest(page, size, sortByTimeDesc());
        Page<Resource> resources = resourceRepository.findByType(type, pageable);
        return resources;
    }

    @Override
    public boolean delete(Integer id) {
        Resource resource = resourceRepository.findOne(id);
        if(resource != null){
            resourceRepository.delete(id);
            return true;
        }
        return false;
    }

    //得到中文名字的下载资源
    private static final char[] HEX_CHARS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
            'C', 'D', 'E', 'F' };

    @Override
    public String getFilename(String filename) {
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request.getHeader("User-Agent").indexOf("MSIE") != -1) {
                return '\"' + java.net.URLEncoder.encode(filename, "UTF-8") + '\"';
            }
            byte[] bytes = filename.getBytes("UTF-8");
            StringBuilder buff = new StringBuilder(bytes.length << 2);
            buff.append("=?UTF-8?Q?");
            for (byte b : bytes) {
                int unsignedByte = b & 0xFF;
                buff.append('=').append(HEX_CHARS[unsignedByte >> 4]).append(HEX_CHARS[unsignedByte & 0xF]);
            }
            return buff.append("?=").toString();
        }catch(UnsupportedEncodingException e){
            return filename;
        }
    }

    public void saveFile(byte[] bytes, String digest, String originalFilename) throws IOException {
        // store the bytes to file
        // File digest format: `{Digest1}-{Digest2}-{Digest3}-{Digest4}`
        // Save file to: `uploads/{Digest1}/{Digest2}/{Digest3}/{Digest4}/{OriginalFilename}`
        Files.write(FileSystems.getDefault().getPath(getFilePath(digest, originalFilename)), bytes, StandardOpenOption.CREATE);
    }
}
