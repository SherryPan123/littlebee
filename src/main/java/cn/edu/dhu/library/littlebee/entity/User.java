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

    public User(String username, String password, String userNumber){
        this.username = username;
        this.password = password;
        this.userNumber = userNumber;
    }

}
