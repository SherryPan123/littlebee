package cn.edu.dhu.library.littlebee.repository;

import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.entity.RewardPenalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by sherry on 15-11-17.
 */
@Repository
public interface RewardPenaltyRepository extends JpaRepository<RewardPenalty, Integer> {

    List<RewardPenalty> findByDate(ZonedDateTime date);  //通过月份找

}
