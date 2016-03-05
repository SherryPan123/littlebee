package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Question;
import cn.edu.dhu.library.littlebee.entity.Reply;
import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.service.QuestionService;
import cn.edu.dhu.library.littlebee.service.ReplyService;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sherrypan on 16-3-2.
 */
@Controller
public class ReplyController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ReplyService replyService;

    @PreAuthorize("hasAuthority('askQuestion')")
    @RequestMapping(value = "/reply/save/{questionId}", method = RequestMethod.POST)
    public ModelAndView reply(@PathVariable("questionId") Integer questionId,
                              @ModelAttribute("reply") Reply reply) {
        ModelAndView mav = new ModelAndView();
        try {
            User user = userService.getSessionUser();
            if(user != null) {
                System.out.println("reply的ID是"+reply.getId());
                reply.setUser(user);
                Question question = questionService.findOne(questionId);
                question.getReplies().add(reply);
                reply.setQuestion(question);
                replyService.save(reply);
                questionService.save(question);
            }
            mav.setView(new RedirectView("/question/view/" + questionId, true));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
        return mav;
    }

    @PreAuthorize("hasAuthority('manageQuestion')")
    @RequestMapping(value = "/reply/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {
        Question question;
        try {
            Reply reply = replyService.findOne(id);
            if (null == reply) {
                throw new ServiceException("Invalid Reply Id");
            }
            question = reply.getQuestion();
            List<Reply> replyList = new ArrayList<>(question.getReplies());
            question.getReplies().clear();
            questionService.save(question);
            replyList.remove(reply);
            question.setReplies(replyList);
            questionService.save(question);
            if (replyService.delete(id)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }

        return "redirect:/question/view/" + question.getId();
    }

}
