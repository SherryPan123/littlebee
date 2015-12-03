package cn.edu.dhu.library.littlebee.repository;

import cn.edu.dhu.library.littlebee.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created by sherry on 15-11-17.
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findByNameLike(String title); //通过活动名称找

}
