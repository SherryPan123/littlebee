package cn.edu.dhu.library.littlebee.service;

import cn.edu.dhu.library.littlebee.controller.form.NoticeCreateForm;
import cn.edu.dhu.library.littlebee.entity.Notice;
import org.springframework.data.domain.Page;

import java.util.UUID;

/**
 * Created by sherry on 15-11-26.
 */
public interface NoticeService {

    Page<Notice> listOrderByTime(int page, int size);

    Notice findOne(Integer id);

    Notice create(NoticeCreateForm form);

    void delete(Notice notice);

    Notice findByTitle(String title);
}
