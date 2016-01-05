package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Activity;
import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.service.ActivityService;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sherry on 15-11-30.
 */
@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        //property editor for user
        binder.registerCustomEditor(List.class, "users",
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String text)
                            throws IllegalArgumentException {
                        List<User> users = new ArrayList<User>();
                        if (null != text && !"".equals(text)) {
                            String[] strs = text.split(",");
                            for (String str : strs) {
                                if (null != str && !"".equals(str)) {
                                    String userNumber = str.trim();
                                    User user = null;
                                    user = userService.getUserByUserNumber(userNumber);
                                    users.add(user);
                                }
                            }
                        }
                        this.setValue(users);
                    }
                });
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView savePage(@RequestParam(defaultValue = "0") Integer page) {
        ModelAndView mav = new ModelAndView("activity/list");
        //fetch activities
        Page<Activity> AllActivity = activityService.listOrderByTime(page, 5);
        List<Activity> activities = AllActivity.getContent();
        Integer pageCount = AllActivity.getTotalPages();
        Integer pageCur = page;
        mav.addObject("activities", activities);
        mav.addObject("pageCount", pageCount);
        mav.addObject("pageCur", pageCur);
        return mav;
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String postPage(Model model) {
        model.addAttribute("activity", new Activity());
        return "activity/post";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveActivity(@ModelAttribute("activity") Activity activity,
                               final RedirectAttributes redirectAttributes) {

        if (activityService.save(activity)) {
            redirectAttributes.addFlashAttribute("saveActivity", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveActivity", "unsuccess");
        }
        return "redirect:/activity/list";
    }

    @RequestMapping(value = "/{operation}/{id}", method = RequestMethod.GET)
    public String editRemoveActivity(@PathVariable("operation") String operation,
                                     @PathVariable("id") Integer id, final RedirectAttributes redirectAttributes,
                                     Model model) {
        if (operation.equals("delete")) {
            if (activityService.delete(id)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if (operation.equals("edit")) {
            Activity editActivity = activityService.findOne(id);
            if (editActivity != null) {
                model.addAttribute("editActivity", editActivity);
                return "activity/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }
        return "redirect:/activity/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateActivity(@ModelAttribute("editActivity") Activity editActivity,
                                 final RedirectAttributes redirectAttributes) {
        Activity oldActivity = activityService.findOne(editActivity.getId());
        oldActivity.setName(editActivity.getName());
        oldActivity.setDescription(editActivity.getDescription());
        oldActivity.setUsers(editActivity.getUsers());
        oldActivity.setModifiedDate(ZonedDateTime.now());
        if (activityService.editActivity(oldActivity)) {
            redirectAttributes.addFlashAttribute("edit", "success");
        } else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
        return "redirect:/activity/list";
    }

}
