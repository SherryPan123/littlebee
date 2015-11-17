package cn.edu.dhu.library.littlebee.repository;

import cn.edu.dhu.library.littlebee.entity.Salary;
import cn.edu.dhu.library.littlebee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by sherry on 15-11-17.
 */
@Repository
public interface SalaryRepository extends JpaRepository<User, UUID> {

    Salary findById(UUID id);

    List<Salary> findByDate(ZonedDateTime date);  //通过月份找

    List<Salary> findByUserUserNumber(String userNumber); //通过学号找

    List<Salary> findByUserUsername(String username); //通过姓名找

    Salary findByUserUserNumberAndDate(String userNumber,ZonedDateTime date);//通过学号和月份查

}
