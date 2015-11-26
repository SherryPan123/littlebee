package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Newsletter;
import cn.edu.dhu.library.littlebee.entity.Notice;
import cn.edu.dhu.library.littlebee.repository.NewsletterRepository;
import cn.edu.dhu.library.littlebee.service.NewsletterService;
import cn.edu.dhu.library.littlebee.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by sherry on 15-11-19.
 */
@Controller
public class NewsletterController {

    @Autowired
    @Qualifier("NewsletterService")
    private NewsletterService newsletterService;

    @RequestMapping(value="/newslist")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();

        // fetch newsletter
        Page<Newsletter> newsletters = null;
        newsletters = newsletterService.listOrderByTime(0, 10);
        mav.addObject("newsletters", newsletters);

        return mav;
    }

}
