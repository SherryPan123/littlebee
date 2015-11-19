package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by sherry on 15-11-19.
 */
@Controller
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "users",method = RequestMethod.GET)
    List<User> getAll(){

        return userRepository.findAll();
    }

    /*登陆验证*/
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String authenticate(@RequestParam String userNumber,@RequestParam String password){
        //安全性验证

        logger.info("Aunthenticating {}"); //验证用户名
        return "";
    }


    /*注册*/
    @Transactional
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") @Valid User user, BindingResult result, @RequestParam String confirm, RedirectAttributes redirectAttributes) {
        user = (User) result.getTarget();
        if (confirm == null || !user.getPassword().equals(confirm)) {
            result.addError(new FieldError("user", "password", "The two passwords are not match."));
        }
//
//        if (userRepository.findByUsername(user.getUsername()) != null) {
//            result.addError(new FieldError("user", "username", "The username has been used."));
//        }
//        if (result.hasErrors()) {
//            return "users/new";
//        }
//
//        user.setPassword(passwordService.encryptPassword(user.getPassword()));
//        user.getRoles().add(roleRepository.findByName("USER"));
//        userRepository.save(user);
//
//        redirectAttributes.addFlashAttribute("message", "Sign up successfully.");
        return "redirect:/";
    }

}



