/*
信息:
*/
package taxServices.user.dao.imp;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import taxServices.user.dao.UserDao;
import taxServices.user.entity.User;
import taxServices.user.entity.UserRole;
import yd.itcast.core.dao.imp.BaseDaoImp;
import yd.itcast.core.utils.QueryHelper;

import java.io.Serializable;
import java.util.List;

public class UserDaoImp extends BaseDaoImp<User> implements UserDao {

    @Override
    public List<User> findByAccountAndId(String id, String account) {
       String sql="FROM User WHERE account=? ";
        if (StringUtils.isNotBlank(id)){
            sql=sql+"and id!= ?";
        }
        Query query=currentSession().createQuery(sql);
        query.setParameter(0,account);
        if (StringUtils.isNotBlank(id))
            query.setParameter(1,id);
        return query.list();
    }

    @Override
    public void saveUserRole(UserRole userRole) {
      getHibernateTemplate().save(userRole);
    }

    @Override
    public void deleteUserRoleIds(Serializable id) {
     Query query= currentSession().createQuery("DELETE FROM UserRole where id.userId= ?");
     query.setParameter(0,id);
     query.executeUpdate();
    }

    @Override
    public List<UserRole> findUserRoleByUserId(String id) {
       Query query= currentSession().createQuery("FROM UserRole WHERE id.userId= ?");
       query.setParameter(0,id);
        return query.list();
    }

    @Override
    public List<User> indUserByAccountAndPassword(String account, String password) {
      Query query=currentSession().createQuery("FROM User WHERE account=? and password=?");
      query.setParameter(0,account);
      query.setParameter(1,password);
        return query.list();
    }

    @Override
    public List<User> findByUserDept(QueryHelper queryHelper) {
       Query query=currentSession().createQuery(queryHelper.getClause());
       if (queryHelper.getParamters()!=null){
           List<Object> list=queryHelper.getParamters();
          for (int i=0;i<list.size();i++){
              query.setParameter(i,list.get(i));
          }
       }
        return query.list();
    }


}
