package cn.edu.dhu.library.littlebee.entity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Created by sherry on 15-11-12.
 */
public class Salary extends BaseEntity{

    /*工资表月份*/
    @Column(name = "year_month")
    private ZonedDateTime yearMonth;

    /*用户学号*/
    @ManyToOne
    private User user;

    /*时薪*/
    @Column(name = "unity_pay")
    private BigDecimal unityPay;

    /*工时*/
    @Column(name = "man_hour")
    private BigDecimal manHour;

    /*奖金*/
    @Column(name = "bonus")
    private BigDecimal bonus;

    /*总薪资*/
    @Column(name = "total_pay")
    private BigDecimal totalPay;

    /*备注说明*/
    @Column(name = "description", nullable = true, length = 255)
    private String description;

}
