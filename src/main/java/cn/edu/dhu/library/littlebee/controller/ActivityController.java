package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Activity;
import cn.edu.dhu.library.littlebee.service.ActivityService;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by sherry on 15-11-30.
 */
@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "activity/list", method = RequestMethod.GET)
    public String savePage(Model model) {
        model.addAttribute("activities", activityService.getAllActivities());
        model.addAttribute("users", userService.getAllUsers());
        return "activity/list";
    }

    @RequestMapping(value = "activity/post", method = RequestMethod.GET)
    public String postPage(Model model) {
        model.addAttribute("activity", new Activity());
        return "activity/post";
    }

    @RequestMapping(value = "/activity/save", method = RequestMethod.POST)
    public String saveActivity(@ModelAttribute("activity") Activity activity,
                               final RedirectAttributes redirectAttributes) {

        if(activityService.save(activity)) {
            redirectAttributes.addFlashAttribute("saveActivity", "success");
        }
        else {
            redirectAttributes.addFlashAttribute("saveActivity", "unsuccess");
        }
        return "redirect:/activity/list";
    }

    @RequestMapping(value = "/activity/{operation}/{id}", method = RequestMethod.GET)
    public String editRemoveActivity(@PathVariable("operation") String operation,
                                     @PathVariable("id") Integer id, final RedirectAttributes redirectAttributes,
                                     Model model) {
        if(operation.equals("delete")) {
            if(activityService.delete(id)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            }
            else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        }
        else if(operation.equals("edit")){
            Activity editActivity = activityService.findOne(id);
            if(editActivity != null) {
                model.addAttribute("editActivity", editActivity);
                return "activity/edit";
            }
            else {
                redirectAttributes.addFlashAttribute("status","notfound");
            }
        }
        return "redirect:/activity/list";
    }

    @RequestMapping(value = "/activity/update", method = RequestMethod.POST)
    public String updateActivity(@ModelAttribute("editActivity") Activity editActivity,
                                 final RedirectAttributes redirectAttributes) {
        if(activityService.editActivity(editActivity)) {
            redirectAttributes.addFlashAttribute("edit", "success");
        }
        else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
        return "redirect:/activity/list";
    }

}
