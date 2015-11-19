package cn.edu.dhu.library.littlebee.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sherry on 15-11-19.
 */
@Entity
@Table(name = "notice")
public class Notice extends BaseEntity{

    /*通知标题*/
    @NotEmpty
    @Column(name = "title", nullable = false, length = 45)
    private String title;

    /*通知内容*/
    @Column(name = "content", nullable = true, length = 100)
    private String content;

}
