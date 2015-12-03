package cn.edu.dhu.library.littlebee.repository;

import cn.edu.dhu.library.littlebee.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by sherry on 15-11-17.
 */
@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {

    List<Salary> findByDate(ZonedDateTime date);  //通过月份找

    List<Salary> findByUser_UserNumber(String userNumber); //通过学号找

    List<Salary> findByUser_Username(String username); //通过姓名找

    Salary findByUser_UserNumberAndDate(String userNumber,ZonedDateTime date);//通过学号和月份查

}
