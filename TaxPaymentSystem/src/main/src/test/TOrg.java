/*
信息:
*/
package test;

public class TOrg {
    private String orgId;
    private String orgName;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TOrg tOrg = (TOrg) o;

        if (orgId != null ? !orgId.equals(tOrg.orgId) : tOrg.orgId != null) return false;
        if (orgName != null ? !orgName.equals(tOrg.orgName) : tOrg.orgName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orgId != null ? orgId.hashCode() : 0;
        result = 31 * result + (orgName != null ? orgName.hashCode() : 0);
        return result;
    }
}
