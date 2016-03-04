package cn.edu.dhu.library.littlebee.service.impl;

import cn.edu.dhu.library.littlebee.entity.Permission;
import cn.edu.dhu.library.littlebee.repository.PermissionRepository;
import cn.edu.dhu.library.littlebee.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public boolean save(Permission permission) {
        if(permission != null){
            permissionRepository.save(permission);
            return true;
        }
        return false;
    }

    @Override
    public Permission findOne(Integer id) {
        return permissionRepository.findOne(id);
    }

    @Override
    public boolean editPermission(Permission editPermission) {
        if(editPermission != null){
            permissionRepository.save(editPermission);
            return true;
        }
        return false;
    }

    @Override
    public Page<Permission> listOrderByTime(int page, int size) {
        Pageable pageable = new PageRequest(page, size, sortByTimeDesc());
        return permissionRepository.findAll(pageable);
    }

    private Sort sortByTimeDesc() {
        return new Sort(Sort.Direction.DESC, "createdDate");
    }

}
