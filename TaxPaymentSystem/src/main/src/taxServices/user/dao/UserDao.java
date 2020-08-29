package taxServices.user.dao;

import taxServices.user.entity.User;
import taxServices.user.entity.UserRole;
import yd.itcast.core.dao.BaseDao;
import yd.itcast.core.utils.QueryHelper;

import java.io.Serializable;
import java.util.List;

public interface UserDao extends BaseDao<User> {
    List<User> findByAccountAndId(String id, String account);

    void saveUserRole(UserRole userRole);

    void deleteUserRoleIds(Serializable id);

    List<UserRole> findUserRoleByUserId(String id);

    List<User> indUserByAccountAndPassword(String account, String password);

    List<User> findByUserDept(QueryHelper queryHelper);
}
