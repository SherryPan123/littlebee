package cn.edu.dhu.library.littlebee.service.impl;

import cn.edu.dhu.library.littlebee.entity.Newsletter;
import cn.edu.dhu.library.littlebee.repository.NewsletterRepository;
import cn.edu.dhu.library.littlebee.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by sherry on 15-11-26.
 */
@Service
@Transactional
public class NewsletterServiceImpl implements NewsletterService {

    @Autowired
    private NewsletterRepository newsletterRepository;

    /*按照创建时间降序分页排列*/
    @Override
    public Page<Newsletter> listOrderByTime(int page, int size){
        Pageable pageable = new PageRequest(page, size, sortByTimeDesc());
        return newsletterRepository.findAll(pageable);
    }

    private Sort sortByTimeDesc() {
        return new Sort(Sort.Direction.DESC, "createdDate");
    }

    /*ID对应Newsletter*/
    @Override
    public Newsletter findOne(Integer id){
        return newsletterRepository.findOne(id);
    }

}
