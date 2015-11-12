package cn.edu.dhu.library.littlebee.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;

/**
 * Created by sherry on 15-11-12.
 */
public class Role extends BaseEntity{

    @Length(min = 4, max = 20)
    @NotEmpty
    @Column(name = "role_name", unique = true, nullable = false, length = 45)
    private String roleName;


}
