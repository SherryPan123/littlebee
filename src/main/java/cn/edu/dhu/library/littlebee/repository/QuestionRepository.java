package cn.edu.dhu.library.littlebee.repository;

import cn.edu.dhu.library.littlebee.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sherrypan on 16-3-2.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
