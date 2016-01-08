package cn.edu.dhu.library.littlebee.service;

import cn.edu.dhu.library.littlebee.entity.Role;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by sherry on 16-1-8.
 */
public interface RoleService {

    boolean save(Role role);

    Page<Role> listOrderByTime(int page, int size);

    List<Role> findAll();

    boolean editRole(Role oldRole);

    Role findOne(Integer id);

    boolean delete(Integer id);

}
