package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Newsletter;
import cn.edu.dhu.library.littlebee.entity.Notice;
import cn.edu.dhu.library.littlebee.repository.NewsletterRepository;
import cn.edu.dhu.library.littlebee.service.NewsletterService;
import cn.edu.dhu.library.littlebee.service.NoticeService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

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
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("newsletter/list");

        // fetch newsletter
        Page<Newsletter> newsletters = null;
        newsletters = newsletterService.listOrderByTime(0, 20);
        mav.addObject("newsletters", newsletters);

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



}