package cn.edu.dhu.library.littlebee.bean;

import cn.edu.dhu.library.littlebee.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.UUID;

/**
 * Created by sherry on 15-11-22.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getUserNumber(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRoles().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public UUID getId() {
        return user.getId();
    }

}