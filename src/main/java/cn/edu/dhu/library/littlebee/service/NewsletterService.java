package cn.edu.dhu.library.littlebee.service;

import cn.edu.dhu.library.littlebee.entity.Newsletter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

/**
 * Created by sherry on 15-11-26.
 */
public interface NewsletterService {

    Page<Newsletter> listOrderByTime(int page, int size);

    Newsletter findOne(Integer id);

    boolean save(Newsletter newsletter);

    boolean delete(Integer id);

    boolean editNewsletter(Newsletter editNewsletter);
}
