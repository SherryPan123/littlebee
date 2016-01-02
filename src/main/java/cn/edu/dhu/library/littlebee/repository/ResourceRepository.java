package cn.edu.dhu.library.littlebee.repository;

import cn.edu.dhu.library.littlebee.entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created by sherry on 15-11-17.
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {

    Resource findByDigest(String digest);

    Page<Resource> findByContentType(String contentType, Pageable pageable);

    Page<Resource> findByType(String type, Pageable pageable);
}
