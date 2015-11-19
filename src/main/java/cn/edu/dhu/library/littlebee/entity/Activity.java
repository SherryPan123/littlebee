package cn.edu.dhu.library.littlebee.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sherry on 15-11-12.
 */
@Entity
@Table(name = "activity")
public class Activity extends BaseEntity {

    /*活动名称*/
    @NotEmpty
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    /*活动描述*/
    @Column(name = "description", nullable = true, length = 100)
    private String description;

    /*负责人序列*/
    @ManyToMany
    private List<User> users;

    /*通讯稿序列*/
    @OneToMany
    private List<Newsletter> newsletters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Newsletter> getNewsletters() {
        return newsletters;
    }

    public void setNewsletters(List<Newsletter> newsletters) {
        this.newsletters = newsletters;
    }

}
