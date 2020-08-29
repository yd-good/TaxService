/*
信息:
*/
package yd.itcast.core.service.imp;

import org.apache.poi.hssf.record.formula.functions.T;
import taxServices.info.entity.Info;
import yd.itcast.core.dao.BaseDao;
import yd.itcast.core.service.BaseService;
import yd.itcast.core.utils.PageHelper;
import yd.itcast.core.utils.QueryHelper;

import javax.management.Query;
import java.io.Serializable;
import java.util.List;

public class BaseServiceImp<T> implements BaseService<T> {
   private BaseDao<T> baseDao;
    @Override
    public void update(T entity) {
        baseDao.update(entity);
    }

    @Override
     public void delete(Serializable id) {
      baseDao.delete(id);
    }

    @Override
    public T findById(Serializable id) {
        return baseDao.findById(id);
    }

    @Override
    public List<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    public void save(T entity) {
       baseDao.save(entity);
    }

    @Override
    public PageHelper findPageHelper(int currentPageNo, int pageSize, QueryHelper queryHelper) {
        return baseDao.findPageHelper(currentPageNo,pageSize,queryHelper);
    }

    public void setBaseDao(BaseDao<T> baseDao) {
  this.baseDao = baseDao;
 }
}
