/*
信息:
*/
package taxServices.role.dao.imp;

import org.hibernate.Query;
import taxServices.role.dao.RoleDao;
import taxServices.role.entity.Role;
import yd.itcast.core.dao.imp.BaseDaoImp;

public class RoleDaoImp extends BaseDaoImp<Role> implements RoleDao  {
    @Override
    public void deleteRolePrivilegeByRoleId(String roleId) {
        Query query=currentSession().createQuery("DELETE RolePrivilege WHERE id.role.roleId=?");
        query.setParameter(0,roleId);
        query.executeUpdate();
    }
}
