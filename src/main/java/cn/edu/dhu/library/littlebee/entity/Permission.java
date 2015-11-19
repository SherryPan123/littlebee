package cn.edu.dhu.library.littlebee.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sherry on 15-11-12.
 */
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {

    /*权限名称*/
    @NotEmpty
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    /*权限描述*/
    @Column(name = "description", nullable = true, length = 255)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
