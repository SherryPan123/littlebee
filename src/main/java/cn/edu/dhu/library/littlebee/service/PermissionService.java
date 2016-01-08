package cn.edu.dhu.library.littlebee.service;

import cn.edu.dhu.library.littlebee.entity.Permission;

import java.util.List;

/**
 * Created by sherry on 16-1-7.
 */
public interface PermissionService {

    public boolean delete(Integer id);

    List<Permission> findAll();

    boolean save(Permission permission);

    Permission findOne(Integer id);

    boolean editPermission(Permission oldPermission);

}
