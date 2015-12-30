package cn.edu.dhu.library.littlebee.controller.form.validator;

import cn.edu.dhu.library.littlebee.controller.form.PasswordChangeForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by sherry on 15-12-3.
 */
@Component
public class PasswordChangeFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(PasswordChangeForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordChangeForm form = (PasswordChangeForm) target;
        validatePasswords(errors, form);
    }

    private void validatePasswords(Errors errors, PasswordChangeForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.rejectValue("password", "password.mismatch", "两次输入的密码不一致!");
        }
    }

}
