package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Permission;
import cn.edu.dhu.library.littlebee.entity.Role;
import cn.edu.dhu.library.littlebee.entity.User;
import cn.edu.dhu.library.littlebee.service.PermissionService;
import cn.edu.dhu.library.littlebee.service.RoleService;
import cn.edu.dhu.library.littlebee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * Created by sherrypan on 16-3-3.
 */
@Controller
public class InitDatabaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/initDatabase", method = RequestMethod.GET)
    public String InitDataBaseController() {
        System.out.println("开始初始化数据库");

        Permission permission = new Permission();
        permission.setName("admin");
        permission.setDescription("后台管理中大的权限和角色管理");
        permissionService.save(permission);
        permission = new Permission();
        permission.setName("manageUser");
        permission.setDescription("管理用户(修改、删除用户)");
        permissionService.save(permission);
        permission = new Permission();
        permission.setName("manageNewsletter");
        permission.setDescription("管理通讯稿");
        permissionService.save(permission);
        permission = new Permission();
        permission.setName("manageNotice");
        permission.setDescription("管理通知");
        permissionService.save(permission);
        permission = new Permission();
        permission.setName("manageActivity");
        permission.setDescription("管理活动");
        permissionService.save(permission);
        permission = new Permission();
        permission.setName("manageResource");
        permission.setDescription("管理上传的资源");
        permissionService.save(permission);
        permission = new Permission();
        permission.setName("download");
        permission.setDescription("下载文档");
        permissionService.save(permission);
        permission = new Permission();
        permission.setName("askQuestion");
        permission.setDescription("提问");
        permissionService.save(permission);
        permission = new Permission();
        permission.setName("manageQuestion");
        permission.setDescription("提问");
        permissionService.save(permission);

        Role role = new Role();
        role.setRoleName("admin");
        role.setDescription("管理员");
        role.setPermissions(new ArrayList<>());
        role.getPermissions().add(permissionService.findOne(1));
        role.getPermissions().add(permissionService.findOne(2));
        role.getPermissions().add(permissionService.findOne(3));
        role.getPermissions().add(permissionService.findOne(4));
        role.getPermissions().add(permissionService.findOne(5));
        role.getPermissions().add(permissionService.findOne(6));
        role.getPermissions().add(permissionService.findOne(7));
        role.getPermissions().add(permissionService.findOne(8));
        role.getPermissions().add(permissionService.findOne(9));
        roleService.save(role);
        role = new Role();
        role.setRoleName("队长");
        role.setDescription("队长");
        role.setPermissions(new ArrayList<>());
        role.getPermissions().add(permissionService.findOne(2));
        role.getPermissions().add(permissionService.findOne(3));
        role.getPermissions().add(permissionService.findOne(4));
        role.getPermissions().add(permissionService.findOne(5));
        role.getPermissions().add(permissionService.findOne(6));
        role.getPermissions().add(permissionService.findOne(7));
        role.getPermissions().add(permissionService.findOne(8));
        role.getPermissions().add(permissionService.findOne(9));
        roleService.save(role);
        role = new Role();
        role.setRoleName("队委");
        role.setDescription("队委");
        role.setPermissions(new ArrayList<>());
        role.getPermissions().add(permissionService.findOne(3));
        role.getPermissions().add(permissionService.findOne(4));
        role.getPermissions().add(permissionService.findOne(5));
        role.getPermissions().add(permissionService.findOne(6));
        role.getPermissions().add(permissionService.findOne(7));
        role.getPermissions().add(permissionService.findOne(8));
        role.getPermissions().add(permissionService.findOne(9));
        roleService.save(role);
        role = new Role();
        role.setRoleName("队员");
        role.setDescription("队员");
        role.setPermissions(new ArrayList<>());
        role.getPermissions().add(permissionService.findOne(7));
        role.getPermissions().add(permissionService.findOne(8));
        roleService.save(role);
        role = new Role();
        role.setRoleName("用户");
        role.setDescription("普通用户");
        role.setPermissions(new ArrayList<>());
        role.getPermissions().add(permissionService.findOne(8));
        roleService.save(role);

        User user = new User();
        user.setUserNumber("littlebee");
        user.setUsername("littlebee");
        user.setPassword(new BCryptPasswordEncoder().encode("dhulittlebee"));
        user.setRoles(new ArrayList<>());
        user.getRoles().add(roleService.findOne(1));
        userService.save(user);
        user = new User();
        user.setUserNumber("testuser");
        user.setUsername("testuser");
        user.setPassword(new BCryptPasswordEncoder().encode("test"));
        user.setRoles(new ArrayList<>());
        user.getRoles().add(roleService.findOne(2));
        userService.save(user);

        System.out.println("数据库初始化结束");
        return "redirect:/";
    }

}
