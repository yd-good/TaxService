/*
信息:
*/
package taxServices.user.entity;

import taxServices.role.entity.Role;

import java.io.Serializable;
import java.util.Objects;

public class UserRoleId implements Serializable {
    private String userId;
    private Role role;
    public UserRoleId() {
    }
    public UserRoleId(String userId, Role role) {
        this.userId = userId;
        this.role = role;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleId)) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getRole(), that.getRole());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getRole());
    }
}
