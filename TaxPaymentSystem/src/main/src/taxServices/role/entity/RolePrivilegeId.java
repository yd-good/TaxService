/*
信息:联合主键必须实现Serializable接口和重写HashCode,equals方法
*/
package taxServices.role.entity;

import java.io.Serializable;

public class RolePrivilegeId implements Serializable {
    private String code;
    private Role role;

    public RolePrivilegeId() {
    }

    public RolePrivilegeId(String code, Role role) {
        this.code = code;
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
