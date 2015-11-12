package cn.edu.dhu.library.littlebee.entity;

import javax.persistence.Column;

/**
 * Created by sherry on 15-11-12.
 */
public class Permission extends BaseEntity{

    /*权限名称*/
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    /*权限描述*/
    @Column(name = "description", nullable = true, length = 100)
    private String description;

}
