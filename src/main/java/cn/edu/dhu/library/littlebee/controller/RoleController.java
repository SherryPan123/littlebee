package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Permission;
import cn.edu.dhu.library.littlebee.entity.Role;
import cn.edu.dhu.library.littlebee.service.ActivityService;
import cn.edu.dhu.library.littlebee.service.PermissionService;
import cn.edu.dhu.library.littlebee.service.RoleService;
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
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
    public ModelAndView savePage(@RequestParam(defaultValue = "0") Integer page) {
        ModelAndView mav = new ModelAndView("/admin/role/list");
        //fetch roles
        Page<Role> AllRoles = roleService.listOrderByTime(page, 20);
        List<Role> roles = AllRoles.getContent();
        Integer pageCount = AllRoles.getTotalPages();
        Integer pageCur = page;
        mav.addObject("roles", roles);
        mav.addObject("pageCount", pageCount);
        mav.addObject("pageCur", pageCur);
        return mav;
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/role/post", method = RequestMethod.GET)
    public String postPage(Model model) {
        List<Permission> permissions = permissionService.findAll();
        model.addAttribute("role", new Role());
        model.addAttribute("permissions", permissions);
        return "/admin/role/post";
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    public String saveRole(@ModelAttribute("role") Role role,
                                 final RedirectAttributes redirectAttributes) {
        if (roleService.save(role)) {
            redirectAttributes.addFlashAttribute("saveRole", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveRole", "unsuccess");
        }
        return "redirect:/admin/role/list";
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/role/{operation}/{id}", method = RequestMethod.GET)
    public String editRemoveRole(@PathVariable("operation") String operation,
                                 @PathVariable("id") Integer id, final RedirectAttributes redirectAttributes,
                                 Model model) {
        if (operation.equals("delete")) {
            if (roleService.delete(id)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if (operation.equals("edit")) {
            List<Role> roles = roleService.findAll();
            Role editRole = roleService.findOne(id);
            List<Permission> permissions = permissionService.findAll();
            if (editRole != null) {
                model.addAttribute("editRole", editRole);
                model.addAttribute("permissions", permissions);
                return "/admin/role/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }
        return "redirect:/admin/role/list";
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    public String updateRole(@ModelAttribute("editRole") Role editRole,
                                   final RedirectAttributes redirectAttributes) {
        Role oldRole = roleService.findOne(editRole.getId());
        oldRole.setRoleName(editRole.getRoleName());
        oldRole.setDescription(editRole.getDescription());
        oldRole.setPermissions(editRole.getPermissions());
        oldRole.setModifiedDate(ZonedDateTime.now());
        if (roleService.editRole(oldRole)) {
            redirectAttributes.addFlashAttribute("edit", "success");
        } else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
        return "redirect:/admin/role/list";
    }

}
