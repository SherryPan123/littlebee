package cn.edu.dhu.library.littlebee.repository;

import cn.edu.dhu.library.littlebee.entity.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created by sherry on 15-11-17.
 */
@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, UUID> {

    List<Newsletter> findByTitleLike(String title); //通过新闻标题找

    List<Newsletter> findByActivityNameLike(String name); //通过活动名称找

}
