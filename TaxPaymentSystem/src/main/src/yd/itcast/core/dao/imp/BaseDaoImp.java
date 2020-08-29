/*
信息:
*/
package yd.itcast.core.dao.imp;
import org.hibernate.Query;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import taxServices.info.entity.Info;
import yd.itcast.core.dao.BaseDao;
import yd.itcast.core.utils.PageHelper;
import yd.itcast.core.utils.QueryHelper;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public  abstract class  BaseDaoImp<T> extends HibernateDaoSupport implements BaseDao<T>{

    private  Class<T> clazz;
    public BaseDaoImp() {
        ParameterizedType type =(ParameterizedType)this.getClass().getGenericSuperclass();//获取通用超类，BaseDaoImp<T>
        clazz=(Class<T>)type.getActualTypeArguments()[0];
    }


    @Override
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public void delete(Serializable id) {
      getHibernateTemplate().delete(findById(id));
    }

    @Override
    public T findById(Serializable id) {
        return getHibernateTemplate().get(clazz,id);
    }

    @Override
    public List<T> findAll() {
     Query query = getSessionFactory().getCurrentSession().createQuery("FROM "+clazz.getSimpleName());
        return query.list();
    }

    @Override
    public void save(T entity) {
       getHibernateTemplate().save(entity);
    }

    @Override
    public PageHelper findPageHelper(int currentPageNo, int pageSize, QueryHelper queryHelper) {
         Query query=currentSession().createQuery(queryHelper.getClause());
         if (queryHelper.getParamters()!=null){
             List<Object> parametersList=queryHelper.getParamters();
             for (int i=0;i<queryHelper.getParamters().size();i++){
                 query.setParameter(i,parametersList.get(i));
             }
         }
        int totalCount=query.list().size();
        if (currentPageNo<1)currentPageNo=1;
        query.setFirstResult((currentPageNo-1)*pageSize);
        query.setMaxResults(pageSize);
        return new PageHelper(totalCount,currentPageNo,pageSize,query.list());
    }
}
