package cn.edu.dhu.library.littlebee.repository;

import cn.edu.dhu.library.littlebee.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sherry on 16-1-7.
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}
