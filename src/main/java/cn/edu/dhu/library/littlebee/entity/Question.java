package cn.edu.dhu.library.littlebee.entity;

import org.hibernate.annotations.ListIndexBase;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sherrypan on 16-3-1.
 */
@Entity
@Table(name = "question")
public class Question extends BaseEntity {

    /*问题标题*/
    @Column(name = "title", nullable = true, length = 2000)
    private String title;

    /*问题补充内容*/
    @Column(name = "content", nullable = true, length = 20000)
    private String content;

    /*提问者*/
    @ManyToOne
    private User user;

    /*回答个数*/
    @Column(name = "status")
    private Integer status;

    /*回答*/
    @OneToMany(fetch = FetchType.LAZY)
    @OrderColumn(name = "sequence", nullable = false)
    @ListIndexBase(1)
    private List<Reply> replies;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replys) {
        this.replies = replys;
    }

}
