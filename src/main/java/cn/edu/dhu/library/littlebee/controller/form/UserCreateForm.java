package cn.edu.dhu.library.littlebee.controller.form;

import org.hibernate.validator.constraints.NotEmpty;

import java.time.ZonedDateTime;

/**
 * Created by sherry on 15-11-22.
 */
public class UserCreateForm {

    @NotEmpty
    private String username = "";

    @NotEmpty
    private String userNumber = "";

    @NotEmpty
    private String password = "";

    @NotEmpty
    private String passwordRepeated = "";

    @NotEmpty
    private String email = "";

    @NotEmpty
    private String phone = "";

    @NotEmpty
    private String sex = "";

    @NotEmpty
    private String major = "";

//    @NotEmpty
//    private ZonedDateTime entryDate;

    private String icon;

    private String selfIntroduction;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
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

//    public ZonedDateTime getEntryDate() {
//        return entryDate;
//    }
//
//    public void setEntryDate(ZonedDateTime entryDate) {
//        this.entryDate = entryDate;
//    }

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
}
