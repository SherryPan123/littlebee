package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Newsletter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by sherry on 15-11-20.
 */
@Controller
public class IndexController {

    @RequestMapping(value="/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value="/login")
    public String login(Model model){
        return "login";
    }

}
