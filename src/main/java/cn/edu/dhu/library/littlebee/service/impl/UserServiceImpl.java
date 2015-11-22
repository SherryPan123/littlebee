package cn.edu.dhu.library.littlebee.service.impl;

import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.form.UserCreateForm;
import cn.edu.dhu.library.littlebee.repository.UserRepository;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by sherry on 15-11-22.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByUserNumber(String userNumber) {
        return userRepository.findByUserNumber(userNumber);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll(new Sort("userNumber"));
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();

        user.setUserNumber(form.getUserNumber());
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setEmail(form.getEmail());
        user.setPhone(form.getPhone());
        user.setUsername(form.getUsername());
        user.setEntryDate(form.getEntryDate());
        user.setMajor(form.getMajor());
        user.setSex(form.getSex());

        user.setIcon(form.getIcon());
        user.setSelfIntroduction(form.getSelfIntroduction());

        return userRepository.save(user);
    }

}
