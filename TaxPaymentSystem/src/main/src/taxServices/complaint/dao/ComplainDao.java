/*
信息:
*/
package taxServices.complaint.dao;

import taxServices.complaint.entity.Complain;
import yd.itcast.core.dao.BaseDao;
import yd.itcast.core.utils.QueryHelper;

import java.util.List;
import java.util.Map;

public interface ComplainDao extends BaseDao<Complain> {
     List<Complain> findAll(QueryHelper queryHelper);

    List<Object[]> findAllByYear(int year);
}
