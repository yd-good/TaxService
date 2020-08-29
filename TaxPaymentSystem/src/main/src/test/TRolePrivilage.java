/*
信息:
*/
package test;

public class TRolePrivilage {
    private String roleId;
    private String prilivage;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPrilivage() {
        return prilivage;
    }

    public void setPrilivage(String prilivage) {
        this.prilivage = prilivage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRolePrivilage that = (TRolePrivilage) o;

        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (prilivage != null ? !prilivage.equals(that.prilivage) : that.prilivage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (prilivage != null ? prilivage.hashCode() : 0);
        return result;
    }
}
