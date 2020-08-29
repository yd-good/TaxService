/*
信息:
*/
package taxServices.role.service.imp;

import org.springframework.stereotype.Service;
import taxServices.role.dao.RoleDao;
import taxServices.role.entity.Role;
import taxServices.role.service.RoleService;
import yd.itcast.core.service.imp.BaseServiceImp;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
@Service("roleService")
public class RoleServiceImp extends BaseServiceImp<Role> implements RoleService {
    private RoleDao roleDao;
    @Resource
    public void setRoleDao(RoleDao roleDao) {
        super.setBaseDao(roleDao);
        this.roleDao = roleDao;
    }


    @Override
    public void update(Role role) {
        roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
        roleDao.update(role);
    }

}
