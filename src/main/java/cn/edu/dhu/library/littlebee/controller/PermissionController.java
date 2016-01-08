package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Permission;
import cn.edu.dhu.library.littlebee.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/permission/list", method = RequestMethod.GET)
    public ModelAndView savePage(@RequestParam(defaultValue = "0") Integer page) {
        ModelAndView mav = new ModelAndView("/admin/permission/list");
        //fetch permissions
        List<Permission> permissions = permissionService.findAll();
        mav.addObject("permissions", permissions);
        return mav;
    }

    @RequestMapping(value = "/permission/post", method = RequestMethod.GET)
    public String postPage(Model model) {
        List<Permission> permissions = permissionService.findAll();
        model.addAttribute("permission", new Permission());
        return "/admin/permission/post";
    }

    @RequestMapping(value = "/permission/save", method = RequestMethod.POST)
    public String savePermission(@ModelAttribute("permission") Permission permission,
                           final RedirectAttributes redirectAttributes) {
        if (permissionService.save(permission)) {
            redirectAttributes.addFlashAttribute("savePermission", "success");
        } else {
            redirectAttributes.addFlashAttribute("savePermission", "unsuccess");
        }
        return "redirect:/admin/permission/list";
    }


    @RequestMapping(value = "/permission/{operation}/{id}", method = RequestMethod.GET)
    public String editRemovePermission(@PathVariable("operation") String operation,
                                 @PathVariable("id") Integer id, final RedirectAttributes redirectAttributes,
                                 Model model) {
        if (operation.equals("delete")) {
            if (permissionService.delete(id)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if (operation.equals("edit")) {
            Permission editPermission = permissionService.findOne(id);
            if (editPermission != null) {
                model.addAttribute("editPermission", editPermission);
                return "/admin/permission/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }
        return "redirect:/admin/permission/list";
    }

    @RequestMapping(value = "/permission/update", method = RequestMethod.POST)
    public String updatePermission(@ModelAttribute("editPermission") Permission editPermission,
                             final RedirectAttributes redirectAttributes) {
        Permission oldPermission = permissionService.findOne(editPermission.getId());
        oldPermission.setName(editPermission.getName());
        oldPermission.setDescription(editPermission.getDescription());
        oldPermission.setModifiedDate(ZonedDateTime.now());
        if (permissionService.editPermission(oldPermission)) {
            redirectAttributes.addFlashAttribute("edit", "success");
        } else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
        return "redirect:/admin/permission/list";
    }

}
