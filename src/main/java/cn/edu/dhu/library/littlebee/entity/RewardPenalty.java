package cn.edu.dhu.library.littlebee.entity;

import org.hibernate.validator.constraints.Length;
import org.omg.CORBA.INTERNAL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by sherry on 15-11-12.
 */
@Entity
@Table(name = "reward_penalty")
public class RewardPenalty extends BaseEntity {

    /*用户*/
    @ManyToMany
    private List<User> users;

    /*发生日期*/
    @Column(name = "date")
    private ZonedDateTime date;

    /*奖惩描述*/
    @Length(min = 6, max = 256)
    @Column(name = "description", nullable = true, length = 255)
    private String description;

    /*类型*/
    @NotNull
    @Column(name = "type", nullable = false)
    private Integer type;

}
