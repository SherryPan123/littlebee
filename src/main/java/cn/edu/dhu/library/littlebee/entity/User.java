package cn.edu.dhu.library.littlebee.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by sherry on 15-11-10.
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    /*用户姓名*/
    @Length(min = 4, max = 20)
    @NotEmpty
    @Column(name = "username", nullable = false, length = 45)
    private String username;

    /*用户密码*/
    @Length(min = 6, max = 256)
    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;

    /*用户学号或工号*/
    @Length(min = 6, max = 10)
    @NotEmpty
    @Column(name = "user_number", unique = true, nullable = false)
    private String userNumber;

    /*用户邮箱*/
    @Email
    @NotEmpty
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /*手机号*/
    @Column(name = "phone")
    private String phone;

    /*性别*/
    @Column(name = "sex")
    private String sex;

    /*专业班级*/
    @Column(name = "major")
    private String major;

    /*入职时间*/
    @Column(name = "entry_date")
    private ZonedDateTime entryDate;

    /*离职时间*/
    @Column(name = "leave_date")
    private ZonedDateTime leaveDate;

    /*角色序列*/
    @ManyToMany
    private List<Role> roles;

    /*头像*/
    @Column(name = "icon")
    private String icon;

    /*个人介绍*/
    @Column(name = "self_introduction")
    private String selfIntroduction;

    /*奖惩情况*/
    @ManyToMany
    private List<RewardPenalty> rewardPenalties;

    public User(String username, String password, String userNumber){
        this.username = username;
        this.password = password;
        this.userNumber = userNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public ZonedDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(ZonedDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public ZonedDateTime getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(ZonedDateTime leaveDate) {
        this.leaveDate = leaveDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public List<RewardPenalty> getRewardPenalties() {
        return rewardPenalties;
    }

    public void setRewardPenalties(List<RewardPenalty> rewardPenalties) {
        this.rewardPenalties = rewardPenalties;
    }

}
