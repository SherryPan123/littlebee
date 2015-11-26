package cn.edu.dhu.library.littlebee.service.impl;

import cn.edu.dhu.library.littlebee.bean.CurrentUser;
import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by sherry on 15-11-22.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userNumber) throws UsernameNotFoundException {
        User user = userService.getUserByUserNumber(userNumber);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with userNumber=%s was not found", userNumber));
        }
        return new CurrentUser(user);
    }

}