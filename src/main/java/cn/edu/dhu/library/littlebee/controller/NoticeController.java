package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.controller.form.NoticeCreateForm;
import cn.edu.dhu.library.littlebee.controller.form.validator.NoticeCreateFormValidator;
import cn.edu.dhu.library.littlebee.entity.Notice;
import cn.edu.dhu.library.littlebee.service.NoticeService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Created by sherry on 15-11-26.
 */
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeCreateFormValidator noticeCreateFormValidator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(noticeCreateFormValidator);
    }


    /*通知列表*/
    @RequestMapping(value="/notice/list", method = RequestMethod.GET)
    public ModelAndView noticeList(@RequestParam(defaultValue = "0") Integer page) {
        ModelAndView mav = new ModelAndView("notice/list");
        //fetch notice
        Page<Notice> AllNotice = noticeService.listOrderByTime(page, 5);
        List<Notice> notices = AllNotice.getContent();
        Integer pageCount = AllNotice.getTotalPages();
        Integer pageCur = page;
        mav.addObject("notices",notices);
        mav.addObject("pageCount",pageCount);
        mav.addObject("pageCur",pageCur);
        return mav;
    }

    /*通知视图*/
    @RequestMapping(value = "/notice/view", method = RequestMethod.GET)
    public ModelAndView noticeView(@RequestParam("id") Integer id) {
        try {
            Notice notice = noticeService.findOne(id);
            if (notice == null) {
                //// TODO: throw exception
                throw new ServiceException("Invalid Notice ID");
            }
            ModelAndView mav = new ModelAndView("notice/view");
            mav.addObject("notice", notice);
            return mav;

        } catch (Exception e) {
            //// TODO: throw exception
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    /*post notice*/
    @RequestMapping(value = "/notice/post", method = RequestMethod.GET)
    public ModelAndView getNoticeCreatePage() {
        return new ModelAndView("notice/post", "form", new NoticeCreateForm());
    }

    //todo:安全权限验证
    @RequestMapping(value = "/notice/post", method = RequestMethod.POST)
    public String handleNoticeCreateForm(@Valid @ModelAttribute("form") NoticeCreateForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "notice/post";
        }
        try {
            noticeService.create(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("title.exists", "Notice failed to post");
            return "notice/post";
        }
        return "redirect:/notice/list";
    }

    /*删除通知*/
    @RequestMapping(value = "/notice/delete")
    public String delete(@RequestParam("id") Integer id) {
        Notice notice = noticeService.findOne(id);
        noticeService.delete(notice);
        return "redirect:/notice/list";
    }

}