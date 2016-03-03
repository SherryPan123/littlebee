package cn.edu.dhu.library.littlebee.entity;

import javax.persistence.*;

/**
 * Created by sherrypan on 16-3-1.
 */
@Entity
@Table(name = "reply")
public class Reply extends BaseEntity {

    /*回答内容*/
    @Column(name = "content", nullable = false, length = 20000)
    private String content;

    /*回答者*/
    @ManyToOne
    private User user;

    /*回答的问题*/
    @ManyToOne
    private Question question;

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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}
