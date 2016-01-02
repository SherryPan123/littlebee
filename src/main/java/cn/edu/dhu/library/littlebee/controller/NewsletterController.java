package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Newsletter;
import cn.edu.dhu.library.littlebee.service.NewsletterService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by sherry on 15-11-19.
 */
@Controller
@RequestMapping("/newsletter")
public class NewsletterController {

    @Autowired
    private NewsletterService newsletterService;

    /*通知列表*/
    @RequestMapping(value = "/list")
    public ModelAndView savePage(@RequestParam(defaultValue = "0") Integer page) {
        ModelAndView mav = new ModelAndView("newsletter/list");
        //fetch newsletters
        Page<Newsletter> AllNewsletters = newsletterService.listOrderByTime(page, 5);
        List<Newsletter> newsletters = AllNewsletters.getContent();
        Integer pageCount = AllNewsletters.getTotalPages();
        Integer pageCur = page;
        mav.addObject("newsletters", newsletters);
        mav.addObject("pageCount", pageCount);
        mav.addObject("pageCur", pageCur);
        return mav;
    }

    /*通讯稿视图*/
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam("id") Integer id) {
        try {
            Newsletter newsletter = newsletterService.findOne(id);
            if (newsletter == null) {
                //// TODO: throw exception
                throw new ServiceException("Invalid Newsletter ID");
            }
            ModelAndView mav = new ModelAndView("newsletter/view");
            mav.addObject("newsletter", newsletter);
            return mav;

        } catch (Exception e) {
            //// TODO: throw exception
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String postPage(Model model) {
        model.addAttribute("newsletter", new Newsletter());
        return "newsletter/post";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNewsletter(@ModelAttribute("newsletter") Newsletter newsletter,
                               final RedirectAttributes redirectAttributes) {

        if (newsletterService.save(newsletter)) {
            redirectAttributes.addFlashAttribute("saveNewsletter", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveNewsletter", "unsuccess");
        }
        return "redirect:/newsletter/list";
    }

    @RequestMapping(value = "/{operation}/{id}", method = RequestMethod.GET)
    public String editRemoveNewsletter(@PathVariable("operation") String operation,
                                     @PathVariable("id") Integer id, final RedirectAttributes redirectAttributes,
                                     Model model) {
        if (operation.equals("delete")) {
            if (newsletterService.delete(id)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if (operation.equals("edit")) {
            Newsletter editNewsletter = newsletterService.findOne(id);
            if (editNewsletter != null) {
                model.addAttribute("editNewsletter", editNewsletter);
                return "newsletter/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }
        return "redirect:/newsletter/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateNewsletter(@ModelAttribute("editNewsletter") Newsletter editNewsletter,
                                 final RedirectAttributes redirectAttributes) {
        if (newsletterService.editNewsletter(editNewsletter)) {
            redirectAttributes.addFlashAttribute("edit", "success");
        } else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
        return "redirect:/newsletter/list";
    }

}