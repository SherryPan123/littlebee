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

    private Sort sortByTimeDesc() {
        return new Sort(Sort.Direction.DESC, "createdDate");
    }

}
