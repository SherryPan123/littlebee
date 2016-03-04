package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Question;
import cn.edu.dhu.library.littlebee.entity.Reply;
import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.service.QuestionService;
import cn.edu.dhu.library.littlebee.service.ReplyService;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Created by sherrypan on 16-3-2.
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    /*问题列表*/
    @RequestMapping(value = "/question/list", method = RequestMethod.GET)
    public ModelAndView questionList(@RequestParam(defaultValue = "0") int page) {
        ModelAndView mav = new ModelAndView("question/list");
        //fetch questions
        Page<Question> allQuestions = questionService.listOrderByTime(page, 15);
        List<Question> questions = allQuestions.getContent();
        int pageCount = allQuestions.getTotalPages();
        int pageCur = page;

        if ((pageCur > 0 && pageCur >= pageCount) || (pageCur < 0)) {
            throw new IllegalArgumentException("Page index is illegal.");
        }

        mav.addObject("questions", questions);
        mav.addObject("pageCount", pageCount);
        mav.addObject("pageCur", pageCur);
        return mav;
    }

    /*问题视图*/
    @RequestMapping(value = "/question/view/{id}", method = RequestMethod.GET)
    public ModelAndView questionView(@PathVariable("id") Integer id) {
        try {
            Question question = questionService.findOne(id);
            if (question == null) {
                throw new ServiceException("Invalid Question ID");
            }
            ModelAndView mav = new ModelAndView("question/view");
            mav.addObject("question", question);
            mav.addObject("replies", question.getReplies());
            System.out.println("问题的回复个数为 "+question.getReplies().size());
            mav.addObject("reply", new Reply());
            return mav;

        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @PreAuthorize("hasAuthority('askQuestion')")
    @RequestMapping(value = "/question/add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("question/post");
        mav.addObject("question", new Question());
        return mav;
    }

    @PreAuthorize("hasAuthority('askQuestion')")
    @RequestMapping(value = "/question/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("question") Question question) {
        ModelAndView mav = new ModelAndView();
        try {
            User user = userService.getSessionUser();
            if(user != null) {
                question.setUser(user);
                questionService.save(question);
            }
            mav.setView(new RedirectView("/question/list.html", true));
            return mav;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @PreAuthorize("hasAuthority('manageQuestion')")
    @RequestMapping(value = "/question/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {
        try {
            Question question = questionService.findOne(id);
            if (null == question)
                throw new ServiceException("Invalid Question Id");
            if(questionService.delete(id)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            }
            else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }

        return "redirect:/question/list.html";
    }

}
