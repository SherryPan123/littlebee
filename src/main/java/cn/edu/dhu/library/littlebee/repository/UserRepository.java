package cn.edu.dhu.library.littlebee.repository;

import cn.edu.dhu.library.littlebee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by sherry on 15-11-10.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    User findById(UUID id);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByUsernameOrEmail(String username, String email);

    List<User> findByUsernameLike(String username);

}
