package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Role;
import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.service.RoleService;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by sherry on 16-1-8.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView mav = new ModelAndView("/admin/main");
        return mav;
    }

    @PreAuthorize("hasAuthority('manageUser')")
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public ModelAndView savePage(@RequestParam(defaultValue = "0") Integer page) {
        ModelAndView mav = new ModelAndView("/admin/user/list");
        //fetch users
        Page<User> AllUsers = userService.listOrderByUserNumber(page, 20);
        List<User> users = AllUsers.getContent();
        Integer pageCount = AllUsers.getTotalPages();
        Integer pageCur = page;
        mav.addObject("users", users);
        mav.addObject("pageCount", pageCount);
        mav.addObject("pageCur", pageCur);
        return mav;
    }

    @PreAuthorize("hasAuthority('manageUser')")
    @RequestMapping(value = "/user/{operation}/{id}", method = RequestMethod.GET)
    public String editRemoveUser(@PathVariable("operation") String operation,
                                       @PathVariable("id") Integer id, final RedirectAttributes redirectAttributes,
                                       Model model) {
        if (operation.equals("delete")) {
            if (userService.delete(id)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if (operation.equals("edit")) {
            List<Role> roles = roleService.findAll();
            User editUser = userService.getUserById(id);
            if (editUser != null) {
                model.addAttribute("editUser", editUser);
                model.addAttribute("roles", roles);
                return "/admin/user/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }
        return "redirect:/admin/user/list";
    }

    @PreAuthorize("hasAuthority('manageUser')")
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("editUser") User editUser,
                                   final RedirectAttributes redirectAttributes) {
        User oldUser = userService.getUserById(editUser.getId());
        oldUser.setUserNumber(editUser.getUserNumber());
        oldUser.setUsername(editUser.getUsername());
        oldUser.setModifiedDate(ZonedDateTime.now());
        oldUser.setMajor(editUser.getMajor());
        oldUser.setEntryDate(editUser.getEntryDate());
        oldUser.setLeaveDate(editUser.getLeaveDate());
        oldUser.setPhone(editUser.getPhone());
        oldUser.setEmail(editUser.getEmail());
        oldUser.setRoles(editUser.getRoles());
        if (userService.editUser(oldUser)) {
            redirectAttributes.addFlashAttribute("edit", "success");
        } else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
        return "redirect:/admin/user/list";
    }

}
