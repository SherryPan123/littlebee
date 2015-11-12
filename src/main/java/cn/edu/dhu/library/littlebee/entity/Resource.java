package cn.edu.dhu.library.littlebee.entity;

import javax.persistence.Column;

/**
 * Created by sherry on 15-11-12.
 */
public class Resource extends BaseEntity {

    /*特征码*/
    @Column(name = "digest", nullable = false, length = 255)
    private String digest;

    /*资源说明*/
    @Column(name = "description", nullable = true, length = 100)
    private String description;

    /*文件类型*/
    @Column(name = "content_type", nullable = true, length = 255)
    private String contentType;

}
