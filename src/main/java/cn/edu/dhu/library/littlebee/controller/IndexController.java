package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Newsletter;
import cn.edu.dhu.library.littlebee.entity.Notice;
import cn.edu.dhu.library.littlebee.entity.Resource;
import cn.edu.dhu.library.littlebee.service.NewsletterService;
import cn.edu.dhu.library.littlebee.service.NoticeService;
import cn.edu.dhu.library.littlebee.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * Created by sherry on 15-11-20.
 */
@Controller
public class IndexController {

    @Autowired
    private NewsletterService newsletterService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");

        // fetch newsletter
        Page<Newsletter> newsletters = newsletterService.listOrderByTime(0, 10);
        mav.addObject("newsletters", newsletters);

        // fetch notice
        Page<Notice> notices = noticeService.listOrderByTime(0, 11);
        mav.addObject("notices", notices);

        // fetch resource
        Page<Resource> resources = resourceService.getResourcesByType("Downloads", 0, 11);
        mav.addObject("resources", resources);

        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        return new ModelAndView("login", "error", error);
    }

}
