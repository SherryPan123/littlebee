package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Newsletter;
import cn.edu.dhu.library.littlebee.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by sherry on 15-11-19.
 */
@Controller
public class NewsletterController {

    @Autowired
    private NewsletterRepository newsletterReposiory;

    @RequestMapping(value="/newslist")
    public String newslist(Model model){
        List<Newsletter> newsletterList = null;
        newsletterList = newsletterReposiory.findAll();
        return "";
    }

}
