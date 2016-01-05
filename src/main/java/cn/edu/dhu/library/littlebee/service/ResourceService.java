package cn.edu.dhu.library.littlebee.service;

import cn.edu.dhu.library.littlebee.entity.Resource;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by sherry on 15-12-29.
 */
public interface ResourceService {
    
    String calcDigest(byte[] bytes) throws NoSuchAlgorithmException;

    Resource findByDigest(String digest);

    Resource save(Resource resource);

    void saveFile(byte[] bytes, String digest, String originalFilename) throws IOException;

    String getFilePath(String digest, String name);

    Page<Resource> getResourcesByType(String type, Integer page, int size);

    boolean delete(Integer id);

//    Resource save(Resource resource);
}
