package cn.edu.dhu.library.littlebee.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by sherry on 15-11-12.
 */
@Entity
@Table(name = "resource", indexes = { @Index(columnList = "digest") })
public class Resource extends BaseEntity {

    /*特征码*/
    @NotEmpty
    @Column(name = "digest", nullable = false, length = 255)
    private String digest;

    /*资源说明*/
    @Column(name = "description", nullable = true, length = 255)
    private String description;

    @Column(name = "type", nullable = true, length = 255)
    private String type;

    /*文件类型*/
    @Column(name = "content_type", nullable = true, length = 255)
    private String contentType;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "size", nullable = false)
    private Long size;

    @Transient
    private String url;

    public Resource() {

    }

    public Resource(Long size, String digest, String name, String contentType) {
        this.size = size;
        this.digest = digest;
        this.name = name;
        this.contentType = contentType;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
