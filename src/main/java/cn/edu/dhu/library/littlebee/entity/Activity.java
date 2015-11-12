package cn.edu.dhu.library.littlebee.entity;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by sherry on 15-11-12.
 */
public class Activity {

    /*活动名称*/
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    /*活动描述*/
    @Column(name = "description", nullable = true, length = 100)
    private String description;

    /*负责人序列*/
    @ManyToMany
    private List<User> users;/*通讯稿序列*/

    /*通讯稿序列*/
    @OneToMany
    private List<Newsletter> newsletters;

    /*图片序列*/
    @OneToMany
    private List<Resource> photos;

}
