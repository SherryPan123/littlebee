package cn.edu.dhu.library.littlebee.service;

import cn.edu.dhu.library.littlebee.entity.Resource;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sherry on 15-12-29.
 */
public interface ResourceService {
    
    String calcDigest(byte[] bytes) throws NoSuchAlgorithmException;

    Resource findByDigest(String digest);

    Resource save(Resource resource);

    void saveFile(byte[] bytes, String digest, String originalFilename) throws IOException;

    String getFilePath(String digest, String name);

//    Resource save(Resource resource);
}
