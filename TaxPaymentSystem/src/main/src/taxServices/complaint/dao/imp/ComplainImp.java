/*
信息:
*/
package taxServices.complaint.dao.imp;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import taxServices.complaint.dao.ComplainDao;
import taxServices.complaint.entity.Complain;
import yd.itcast.core.dao.imp.BaseDaoImp;
import yd.itcast.core.utils.QueryHelper;

import java.util.List;
import java.util.Map;

public class ComplainImp extends BaseDaoImp<Complain> implements ComplainDao{

    @Override
    public List<Complain> findAll(QueryHelper queryHelper) {
        Query query=currentSession().createQuery(queryHelper.getClause());
        if (queryHelper.getParamters()!=null){
            List<Object> objectList=queryHelper.getParamters();
            for (int i=0;i<queryHelper.getParamters().size();i++){
                query.setParameter(i,objectList.get(i));
            }
        }
        return query.list();
    }

    @Override
    public List<Object[]> findAllByYear(int year) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT imonth, COUNT(comp_id)")
                .append(" FROM tmonth LEFT JOIN complain ON imonth=MONTH(comp_time)")
                .append(" AND YEAR(comp_time)=?")
                .append(" GROUP BY imonth ")
                .append(" ORDER BY imonth");
//        SELECT imonth, COUNT(comp_id)
//                FROM tmonth LEFT JOIN complain ON imonth=MONTH(comp_time)
//        AND YEAR(comp_time)=?
//        GROUP BY imonth;
        SQLQuery sqlQuery = currentSession().createSQLQuery(sb.toString());
        sqlQuery.setParameter(0, year);
        return sqlQuery.list();
    }
}
