package cn.edu.dhu.library.littlebee.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sherry on 15-11-12.
 */
@Entity
@Table(name = "newsletter")
public class Newsletter extends BaseEntity {

    /*通讯稿标题*/
    @Length(min = 0, max = 255)
    @NotEmpty
    @Column(name = "title", unique = true, nullable = false, length = 255)
    private String title;

    /*通讯稿内容*/
    @Column(name = "content", nullable = true, length = 255)
    private String content;

    /*图片序列*/
    @OneToMany
    private List<Resource> resources;

    /*发表人*/
    @ManyToOne
    private User author;

    /*活动*/
    @ManyToOne
    private Activity activity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
