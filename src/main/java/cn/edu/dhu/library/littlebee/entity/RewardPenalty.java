package cn.edu.dhu.library.littlebee.entity;

import org.hibernate.validator.constraints.Length;
import org.omg.CORBA.INTERNAL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * Created by sherry on 15-11-12.
 */
@Entity
@Table(name = "reward_penalty")
public class RewardPenalty extends BaseEntity {

    /*用户*/
    @ManyToOne
    private User user;

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
