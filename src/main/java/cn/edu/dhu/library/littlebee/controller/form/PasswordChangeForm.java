package cn.edu.dhu.library.littlebee.controller.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by sherry on 15-12-3.
 */
public class PasswordChangeForm {

    @NotEmpty
    private String password = "";

    @NotEmpty
    private String passwordRepeated = "";

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
}
