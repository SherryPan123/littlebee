package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Resource;
import cn.edu.dhu.library.littlebee.service.ResourceService;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by sherry on 15-12-29.
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String formUpload() {
        return "resource/upload";
    }

    @Transactional
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new FileUploadException("The file is empty.");
            }

            // metadata persistence
            String digest = resourceService.calcDigest(file.getBytes());
            Resource resource = resourceService.findByDigest(digest);
            if (resource == null) {
                resource = new Resource(file.getSize(), digest, file.getOriginalFilename(), file.getContentType());
            }
            resource = resourceService.save(resource);
            logger.info("Resource Digest: '{}'", resource.getDigest());

            // save to filesystem
            resourceService.saveFile(file.getBytes(), resource.getDigest(), file.getOriginalFilename());

            // save pins to timeline
            resource.setUrl("/resources/view/" + resource.getId());

            return "resource/view";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/view/{digest}", method = RequestMethod.GET)
    public void viewDigest(HttpServletResponse response, @PathVariable String digest) {
        try {
            Resource resource = resourceService.findByDigest(digest);
            File file = new File(resourceService.getFilePath(digest, resource.getName()));
            response.setContentType(resource.getContentType());
            response.setContentLength(resource.getSize().intValue());
            InputStream is = new FileInputStream(file);
            IOUtils.copy(is, response.getOutputStream());
        } catch (Exception e) {
            logger.error("Could not show resource " + digest, e);
            //throw new ResourceNotFoundException(e.getMessage(), e.getCause());
        }
    }

}
