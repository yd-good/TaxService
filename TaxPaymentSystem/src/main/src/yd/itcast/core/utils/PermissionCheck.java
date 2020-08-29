/*
信息:
*/
package yd.itcast.core.utils;

import taxServices.role.entity.Role;
import taxServices.role.entity.RolePrivilege;
import taxServices.role.service.RoleService;
import taxServices.user.entity.User;
import taxServices.user.entity.UserRole;
import taxServices.user.service.UserService;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PermissionCheck {
    @Resource
    private UserService userService;
//    用户权限判断
    public boolean isAccess(User user, String code) {
        List<UserRole> roleList=user.getUserRoles();
        if (roleList==null){
            roleList=userService.findUserRoleByUserId(user.getId());
        }
        if (roleList!=null&&roleList.size()>0){
            for (UserRole userRole:roleList){
                Role role=userRole.getId().getRole();
                for (RolePrivilege privilege:role.getRolePrivileges()){
                    if (code.equals(privilege.getId().getCode())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
