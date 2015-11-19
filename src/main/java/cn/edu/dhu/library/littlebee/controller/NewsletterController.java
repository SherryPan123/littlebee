package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Newsletter;
import cn.edu.dhu.library.littlebee.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by sherry on 15-11-19.
 */
@Controller
public class NewsletterController {

    @Autowired
    private NewsletterRepository newsletterReposiory;

}
