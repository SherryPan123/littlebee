package cn.edu.dhu.library.littlebee.controller.form.validator;

import cn.edu.dhu.library.littlebee.controller.form.UserCreateForm;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by sherry on 15-11-22.
 */
@Component
public class UserCreateFormValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCreateForm form = (UserCreateForm) target;
        validatePasswords(errors, form);
        validateUserNumber(errors, form);
    }

    //验证密码是否相同
    private void validatePasswords(Errors errors, UserCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.rejectValue("password", "password.mismatch", "两次输入的密码不一致!");
        }
    }

    //验证学号是否重复
    private void validateUserNumber(Errors errors, UserCreateForm form) {
        if (userService.getUserByUserNumber(form.getUserNumber()) != null) {
            errors.rejectValue("userNumber", "userNumber.exist", "用户名已被使用!");
        }
    }

    // TODO: add other validators

}
