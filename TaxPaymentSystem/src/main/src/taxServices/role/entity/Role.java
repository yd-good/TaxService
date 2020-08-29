/*
信息:
*/
package taxServices.role.entity;


import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable {
    private String roleId;
    private String roleName;
    private String roleStatus;
    private Set<RolePrivilege> rolePrivileges;
    //角色状态
    private static String ROLE_STATE_VALID="1";//有效
    private static String ROLE_STATE_INVALID="0";//无效
    public Role() {
    }

    public Role(String roleId) {
        this.roleId = roleId;
    }

    public Role(String roleId, String roleName, String roleStatus, Set<RolePrivilege> rolePrivileges) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleStatus = roleStatus;
        this.rolePrivileges = rolePrivileges;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    public Set<RolePrivilege> getRolePrivileges() {
        return rolePrivileges;
    }

    public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }
}
