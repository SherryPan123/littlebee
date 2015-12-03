package cn.edu.dhu.library.littlebee.repository;

import cn.edu.dhu.library.littlebee.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by sherry on 15-11-26.
 */
@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    Notice findByTitle(String title);

}

