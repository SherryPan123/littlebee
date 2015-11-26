package cn.edu.dhu.library.littlebee.repository;

import cn.edu.dhu.library.littlebee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by sherry on 15-11-10.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findById(UUID id);

    User findByUsername(String username); //通过姓名找

    User findByUserNumber(String userNumber);  //通过学号找

    List<User> findByUsernameLike(String username); //姓名like

    List<User> findByUserNumberLike(String username);  //学号like

}
