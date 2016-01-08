package cn.edu.dhu.library.littlebee.service.impl;

import cn.edu.dhu.library.littlebee.entity.Role;
import cn.edu.dhu.library.littlebee.repository.RoleRepository;
import cn.edu.dhu.library.littlebee.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean save(Role role) {
        if(role != null){
            roleRepository.save(role);
            return true;
        }
        return false;
    }

    @Override
    public Page<Role> listOrderByTime(int page, int size) {
        Pageable pageable = new PageRequest(page, size, sortByTimeDesc());
        return roleRepository.findAll(pageable);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public boolean editRole(Role editRole) {
        if(editRole != null){
            roleRepository.save(editRole);
            return true;
        }
        return false;
    }

    @Override
    public Role findOne(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    public boolean delete(Integer id) {
        Role role = roleRepository.findOne(id);
        if(role != null){
            roleRepository.delete(id);
            return true;
        }
        return false;
    }

    private Sort sortByTimeDesc() {
        return new Sort(Sort.Direction.DESC, "createdDate");
    }

}
