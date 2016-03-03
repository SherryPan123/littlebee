package cn.edu.dhu.library.littlebee.service;

import cn.edu.dhu.library.littlebee.entity.Question;
import cn.edu.dhu.library.littlebee.entity.Reply;

/**
 * Created by sherrypan on 16-3-2.
 */
public interface ReplyService {

    Reply findOne(Integer id);

    boolean delete(Integer id);

    void save(Reply reply);

}
