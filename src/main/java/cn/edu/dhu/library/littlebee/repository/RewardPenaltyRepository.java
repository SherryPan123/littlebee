package cn.edu.dhu.library.littlebee.repository;

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
public interface RewardPenaltyRepository extends JpaRepository<RewardPenalty, UUID> {

    RewardPenalty findById(UUID id);

    List<RewardPenalty> findByDate(ZonedDateTime date);  //通过月份找

    List<RewardPenalty> findByUserUserNumber(String userNumber); //通过学号找

    List<RewardPenalty> findByUserUsername(String username); //通过姓名找

    RewardPenalty findByUserUserNumberAndDate(String userNumber,ZonedDateTime date);//通过学号和月份查

}
