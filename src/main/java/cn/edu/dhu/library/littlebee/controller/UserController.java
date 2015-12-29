package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.controller.form.PasswordChangeForm;
import cn.edu.dhu.library.littlebee.controller.form.UserCreateForm;
import cn.edu.dhu.library.littlebee.controller.form.validator.PasswordChangeFormValidator;
import cn.edu.dhu.library.littlebee.controller.form.validator.UserCreateFormValidator;
import cn.edu.dhu.library.littlebee.entity.Resource;
import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.service.ResourceService;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    private ResourceService resourceService;

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

    @Transactional
    @RequestMapping(value = "/user/avatar", method = RequestMethod.POST)
    public String handleAvatarUpload(@RequestParam MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new FileUploadException("The file is empty.");
            }
            if (!file.getContentType().contains("image")) {
                throw new FileUploadException("The file type is not supported. Image only.");
            }

            String digest = resourceService.calcDigest(file.getBytes());

            // metadata persistence
            Resource resource = resourceService.findByDigest(digest);
            if (resource == null) {
                resource = new Resource(file.getSize(), digest, file.getOriginalFilename(), file.getContentType());
            }
            resource = resourceService.save(resource);
            System.out.println("Resource Digest: " + resource.getDigest());

            resourceService.saveFile(file.getBytes(), resource.getDigest(), file.getOriginalFilename());

            resource.setUrl("/resource/view/" + resource.getDigest());

            User user = userService.getUserById(userService.getSessionUser().getId());
            user.setIcon(resource.getUrl());
            userService.save(user);
            userService.reloadSessionUser();
            return "redirect:/user/profile";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*修改密码*/
    @RequestMapping(value = "/user/passwordChange", method = RequestMethod.GET)
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

    @RequestMapping(value = "/user/getUserInJson", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUserInJson(String userNumber) {
        return userService.getUserByUserNumber(userNumber);
    }

}



