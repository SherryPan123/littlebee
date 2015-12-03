package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.controller.form.PasswordChangeForm;
import cn.edu.dhu.library.littlebee.controller.form.UserCreateForm;
import cn.edu.dhu.library.littlebee.controller.form.validator.PasswordChangeFormValidator;
import cn.edu.dhu.library.littlebee.controller.form.validator.UserCreateFormValidator;
import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by sherry on 15-11-19.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCreateFormValidator userCreateFormValidator;

    @Autowired
    private PasswordChangeFormValidator passwordChangeFormValidator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @InitBinder("form1")
    public void initBinder1(WebDataBinder binder) {
        binder.addValidators(passwordChangeFormValidator);
    }


    /*注册*/
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getUserCreateForm() {
        return new ModelAndView("register", "form", new UserCreateForm());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("userNumber.exists", "User Number already exists");
            return "register";
        }
        return "redirect:/";
    }

    /*profile show*/
    @Transactional
    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public String view(Model model) {
        User user = userService.getSessionUser();
        model.addAttribute("user", user);
        return "user/profile";
    }

    /*修改密码*/
    @RequestMapping(value = "/user/passwordChange",method = RequestMethod.GET)
    public ModelAndView getPasswordChangeForm() {
        return new ModelAndView("/user/passwordChange", "form1", new PasswordChangeForm());
    }

    @RequestMapping(value = "/user/passwordChange", method = RequestMethod.POST)
    public String handlePasswordChangeForm(@Valid @ModelAttribute("form1") PasswordChangeForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/passwordChange";
        }
        userService.changePassword(userService.getSessionUser().getUserNumber(), form);
        return "redirect:/";
    }

}



