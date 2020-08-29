/*
信息:
*/
package test;

public class TDept {
    private String deptId;
    private String orgId;
    private String deptName;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDept tDept = (TDept) o;

        if (deptId != null ? !deptId.equals(tDept.deptId) : tDept.deptId != null) return false;
        if (orgId != null ? !orgId.equals(tDept.orgId) : tDept.orgId != null) return false;
        if (deptName != null ? !deptName.equals(tDept.deptName) : tDept.deptName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deptId != null ? deptId.hashCode() : 0;
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (deptName != null ? deptName.hashCode() : 0);
        return result;
    }
}
