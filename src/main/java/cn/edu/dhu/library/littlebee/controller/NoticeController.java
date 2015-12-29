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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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
    @RequestMapping(value = "/notice/list", method = RequestMethod.GET)
    public ModelAndView noticeList(@RequestParam(defaultValue = "0") Integer page) {
        ModelAndView mav = new ModelAndView("notice/list");
        //fetch notice
        Page<Notice> AllNotice = noticeService.listOrderByTime(page, 5);
        List<Notice> notices = AllNotice.getContent();
        Integer pageCount = AllNotice.getTotalPages();
        Integer pageCur = page;
        mav.addObject("notices", notices);
        mav.addObject("pageCount", pageCount);
        mav.addObject("pageCur", pageCur);
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

    @RequestMapping(value = "/notice/{operation}/{id}", method = RequestMethod.GET)
    public String editRemoveNotice(@PathVariable("operation") String operation,
                                   @PathVariable("id") Integer id, final RedirectAttributes redirectAttributes,
                                   Model model) {
        if (operation.equals("delete")) {
            if (noticeService.delete(id)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if (operation.equals("edit")) {
            Notice editNotice = noticeService.findOne(id);
            if (editNotice != null) {
                model.addAttribute("editNotice", editNotice);
                redirectAttributes.addFlashAttribute("status", "success");
                return "/notice/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }
        return "redirect:/notice/list";
    }

    @RequestMapping(value = "/notice/update", method = RequestMethod.POST)
    public String noticeUpdate(@ModelAttribute("editNotice") Notice editNotice,
                               final RedirectAttributes redirectAttributes) {
        if (noticeService.editNotice(editNotice)) {
            redirectAttributes.addFlashAttribute("edit", "success");
        } else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
        return "redirect:/notice/list";
    }

}