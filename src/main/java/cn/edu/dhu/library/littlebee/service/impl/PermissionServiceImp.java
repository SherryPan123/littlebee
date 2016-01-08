package cn.edu.dhu.library.littlebee.service.impl;

import cn.edu.dhu.library.littlebee.entity.Permission;
import cn.edu.dhu.library.littlebee.repository.PermissionRepository;
import cn.edu.dhu.library.littlebee.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sherry on 16-1-8.
 */
@Service
@Transactional
public class PermissionServiceImp implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public boolean delete(Integer id) {
        Permission permission = permissionRepository.findOne(id);
        if(permission != null){
            permissionRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

}
