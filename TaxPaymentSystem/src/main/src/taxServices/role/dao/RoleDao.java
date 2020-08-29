/*
信息:
*/
package taxServices.role.dao;

import taxServices.role.entity.Role;
import yd.itcast.core.dao.BaseDao;
import yd.itcast.core.utils.PageHelper;
import yd.itcast.core.utils.QueryHelper;

import java.io.Serializable;
import java.util.List;

public interface RoleDao extends BaseDao<Role> {
    void deleteRolePrivilegeByRoleId(String roleId);

}
