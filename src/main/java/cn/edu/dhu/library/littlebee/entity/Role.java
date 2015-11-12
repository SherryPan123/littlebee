package cn.edu.dhu.library.littlebee.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by sherry on 15-11-12.
 */
public class Role extends BaseEntity{

    /*角色名称*/
    @Length(min = 4, max = 20)
    @NotEmpty
    @Column(name = "role_name", unique = true, nullable = false, length = 45)
    private String roleName;

    /*角色描述*/
    @Column(name = "description", nullable = true, length = 100)
    private String description;

    /*权限序列*/
    @ManyToMany
    private List<Permission> permissions;

}
