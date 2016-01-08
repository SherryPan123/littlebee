package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Role;
import cn.edu.dhu.library.littlebee.service.ActivityService;
import cn.edu.dhu.library.littlebee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by sherry on 16-1-8.
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/role/post", method = RequestMethod.GET)
    public String postPage(Model model) {
        model.addAttribute("role", new Role());
        return "role/post";
    }

    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    public String saveActivity(@ModelAttribute("role") Role role,
                               final RedirectAttributes redirectAttributes) {

        if (roleService.save(role)) {
            redirectAttributes.addFlashAttribute("saveRole", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveRole", "unsuccess");
        }
        return "redirect:/admin";
    }

}
