package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Question;
import cn.edu.dhu.library.littlebee.entity.Reply;
import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.service.QuestionService;
import cn.edu.dhu.library.littlebee.service.ReplyService;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

    @RequestMapping(value = "/reply/save/{id}", method = RequestMethod.POST)
    public ModelAndView reply(@PathVariable("id") Integer questionId,
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
            mav.setView(new RedirectView("/question/list.html", true));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
        return mav;
    }

    @RequestMapping(value = "/reply/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id,
                         final RedirectAttributes redirectAttributes) {
        try {
            Reply reply = replyService.findOne(id);
            if (null == reply)
                throw new ServiceException("invalid Reply id");
            replyService.delete(id);
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }

        return "redirect:/question/list.html";
    }

}
