package cn.edu.dhu.library.littlebee.service.impl;

import cn.edu.dhu.library.littlebee.entity.Question;
import cn.edu.dhu.library.littlebee.repository.QuestionRepository;
import cn.edu.dhu.library.littlebee.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sherrypan on 16-3-2.
 */
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Page<Question> listOrderByTime(int page, int max) {
        Pageable pageable = new PageRequest(page, max, sortByTimeDesc());
        return questionRepository.findAll(pageable);
    }

    @Override
    public Question findOne(Integer id) {
        return questionRepository.findOne(id);
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public boolean delete(Integer id) {
        Question question = questionRepository.findOne(id);
        if (question != null) {
            questionRepository.delete(id);
            return true;
        }
        return false;
    }

    private Sort sortByTimeDesc() {
        return new Sort(Sort.Direction.DESC, "createdDate");
    }

}
