package cn.edu.dhu.library.littlebee.service.impl;

import cn.edu.dhu.library.littlebee.bean.CurrentUser;
import cn.edu.dhu.library.littlebee.controller.form.PasswordChangeForm;
import cn.edu.dhu.library.littlebee.controller.form.UserCreateForm;
import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.repository.UserRepository;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by sherry on 15-11-22.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void reloadSessionUser() {
        CurrentUser user = new CurrentUser(getUserById(getSessionUser().getId()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public User getSessionUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByUserNumber(String userNumber) {
        System.out.println(userNumber);
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
        //user.setEntryDate(form.getEntryDate());
        user.setMajor(form.getMajor());
        user.setSex(form.getSex());

        user.setIcon(form.getIcon());
        user.setSelfIntroduction(form.getSelfIntroduction());

        return userRepository.save(user);
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }

    @Override
    public void changePassword(String userNumber, PasswordChangeForm form) {
        User user = userRepository.findByUserNumber(userNumber);
        if (user == null) {
            return;
        }
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        userRepository.save(user);
    }

}
