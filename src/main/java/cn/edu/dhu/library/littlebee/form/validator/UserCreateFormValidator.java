package cn.edu.dhu.library.littlebee.form.validator;

import cn.edu.dhu.library.littlebee.form.UserCreateForm;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by sherry on 15-11-22.
 */
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
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    //验证学号是否重复
    private void validateUserNumber(Errors errors, UserCreateForm form) {
        if (userService.getUserByUserNumber(form.getUserNumber()).isPresent()) {
            errors.reject("email.exists", "User with this userNumber already exists");
        }
    }

    // TODO: add other validators

}
