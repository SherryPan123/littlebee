package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.controller.form.UserCreateForm;
import cn.edu.dhu.library.littlebee.controller.form.validator.UserCreateFormValidator;
import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Created by sherry on 15-11-19.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCreateFormValidator userCreateFormValidator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    /*注册*/
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
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
    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam(value = "id", required = false) UUID id) {
        try {
            ModelAndView mav = new ModelAndView("user/profile");
            User user;
            if (id == null) {
                user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                mav.addObject("user",user);
            } else {
                user = userService.getUserById(id);
                if (user == null) {
                    //// TODO: throw exception
                    throw new ServiceException("Invalid user ID");
                }
                mav.addObject("user", user);
            }
            return mav;

        } catch (Exception e) {
            //// TODO: throw exception
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

}



