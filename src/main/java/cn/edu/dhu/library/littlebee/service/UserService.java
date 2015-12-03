package cn.edu.dhu.library.littlebee.service;

import cn.edu.dhu.library.littlebee.controller.form.PasswordChangeForm;
import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.controller.form.UserCreateForm;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by sherry on 15-11-22.
 */
public interface UserService {

    User getSessionUser();

    User getUserById(Integer id);

    User getUserByUserNumber(String userNumber);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

    void save(User user);

    void changePassword(String userNumber, PasswordChangeForm form);

}
