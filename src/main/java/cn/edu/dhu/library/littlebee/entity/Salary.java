package cn.edu.dhu.library.littlebee.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Created by sherry on 15-11-12.
 */
@Entity
@Table(name = "salary", indexes = {@Index(name = "index_salary_on_user_id_and_date", columnList = "user_id, date", unique = true)})
public class Salary extends BaseEntity {

    /*工资表月份*/
    @Column(name = "date")
    private ZonedDateTime date;

    /*用户*/
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

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getUnityPay() {
        return unityPay;
    }

    public void setUnityPay(BigDecimal unityPay) {
        this.unityPay = unityPay;
    }

    public BigDecimal getManHour() {
        return manHour;
    }

    public void setManHour(BigDecimal manHour) {
        this.manHour = manHour;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(BigDecimal totalPay) {
        this.totalPay = totalPay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
