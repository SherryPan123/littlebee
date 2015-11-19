package cn.edu.dhu.library.littlebee.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sherry on 15-11-12.
 */
@Entity
@Table(name = "resource")
public class Resource extends BaseEntity {

    /*特征码*/
    @NotEmpty
    @Column(name = "digest", nullable = false, length = 255)
    private String digest;

    /*资源说明*/
    @Column(name = "description", nullable = true, length = 255)
    private String description;

    /*文件类型*/
    @Column(name = "content_type", nullable = true, length = 255)
    private String contentType;

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

}
