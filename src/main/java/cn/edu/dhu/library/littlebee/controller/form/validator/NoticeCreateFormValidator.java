package cn.edu.dhu.library.littlebee.controller.form.validator;

import cn.edu.dhu.library.littlebee.controller.form.NoticeCreateForm;
import cn.edu.dhu.library.littlebee.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by sherry on 15-12-3.
 */
@Component
public class NoticeCreateFormValidator implements Validator {

    @Autowired
    private NoticeService noticeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(NoticeCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NoticeCreateForm form = (NoticeCreateForm) target;
        validateTitle(errors, form);
    }

    private void validateTitle(Errors errors, NoticeCreateForm form) {
        if (noticeService.findByTitle(form.getTitle()) != null) {
            errors.rejectValue("notice", "notice.exist", "Notice with this title already exists");
        }
    }

}
