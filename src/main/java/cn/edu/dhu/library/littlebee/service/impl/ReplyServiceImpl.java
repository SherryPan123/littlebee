package cn.edu.dhu.library.littlebee.service.impl;

import cn.edu.dhu.library.littlebee.entity.Reply;
import cn.edu.dhu.library.littlebee.repository.ReplyRepository;
import cn.edu.dhu.library.littlebee.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sherrypan on 16-3-2.
 */
@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Override
    public Reply findOne(Integer id) {
        return replyRepository.findOne(id);
    }

    @Override
    public boolean delete(Integer id) {
        Reply reply = replyRepository.findOne(id);
        if (reply != null) {
            replyRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public void save(Reply reply) {
        replyRepository.save(reply);
    }
}
