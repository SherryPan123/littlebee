package cn.edu.dhu.library.littlebee.service;

import cn.edu.dhu.library.littlebee.entity.Question;
import org.springframework.data.domain.Page;

/**
 * Created by sherrypan on 16-3-2.
 */
public interface QuestionService {

    Page<Question> listOrderByTime(int page, int max);

    Question findOne(Integer id);

    void save(Question question);

    boolean delete(Integer id);

}
