/*
信息:
*/
package yd.itcast.core.dao;

import taxServices.info.entity.Info;
import yd.itcast.core.utils.PageHelper;
import yd.itcast.core.utils.QueryHelper;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
    public void update(T entity);
    public void delete(Serializable id);
    public T findById(Serializable id);
    public List<T> findAll();
    public void save(T entity);
    PageHelper findPageHelper(int currentPageNo, int pageSize, QueryHelper queryHelper);
}
