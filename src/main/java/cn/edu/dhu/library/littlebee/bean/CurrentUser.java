package cn.edu.dhu.library.littlebee.bean;

import cn.edu.dhu.library.littlebee.entity.Permission;
import cn.edu.dhu.library.littlebee.entity.Role;
import cn.edu.dhu.library.littlebee.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sherry on 15-11-22.
 */
public class CurrentUser extends User implements UserDetails {

    public CurrentUser(User user) {
        if (user != null) {
            this.setId(user.getId());
            this.setUsername(user.getUserNumber());
            this.setUserNumber(user.getUsername());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
            this.setRoles(user.getRoles());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = this.getRoles();
        if (roles != null) {
            for (Role role : roles) {
                List<Permission> permissions = role.getPermissions();
                for (Permission permission : permissions) {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission.getName());
                    authorities.add(authority);
                }
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}