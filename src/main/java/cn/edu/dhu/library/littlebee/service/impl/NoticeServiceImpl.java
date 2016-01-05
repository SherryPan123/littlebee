package cn.edu.dhu.library.littlebee.service.impl;

import cn.edu.dhu.library.littlebee.controller.form.NoticeCreateForm;
import cn.edu.dhu.library.littlebee.entity.Notice;
import cn.edu.dhu.library.littlebee.repository.NoticeRepository;
import cn.edu.dhu.library.littlebee.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by sherry on 15-11-26.
 */
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public Page<Notice> listOrderByTime(int page, int size){
        Pageable pageable = new PageRequest(page, size, sortByTimeDesc());
        return noticeRepository.findAll(pageable);
    }

    private Sort sortByTimeDesc() {
        return new Sort(Sort.Direction.DESC, "createdDate");
    }

    @Override
    public Notice findOne(Integer id){
        return noticeRepository.findOne(id);
    }

    @Override
    public Notice create(NoticeCreateForm form) {
        Notice notice = new Notice();
        notice.setTitle(form.getTitle());
        notice.setContent(form.getContent());
        return noticeRepository.save(notice);
    }

    @Override
    public boolean delete(Integer id) {
        Notice notice = noticeRepository.findOne(id);
        if(notice != null){
            noticeRepository.delete(id);
            return true;
        }
        return false;

    }

    @Override
    public boolean editNotice(Notice editNotice) {
        if(editNotice != null){
            noticeRepository.save(editNotice);
            return true;
        }
        return false;
    }

    @Override
    public Notice findByTitle(String title) {
        return noticeRepository.findByTitle(title);
    }

    @Override
    public boolean save(Notice notice) {
        if(notice != null){
            noticeRepository.save(notice);
            return true;
        }
        return false;
    }

}
