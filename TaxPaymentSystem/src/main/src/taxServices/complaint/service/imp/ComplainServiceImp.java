/*
信息:
*/
package taxServices.complaint.service.imp;

import org.springframework.stereotype.Service;
import taxServices.complaint.dao.ComplainDao;
import taxServices.complaint.entity.Complain;
import taxServices.complaint.service.ComplainService;
import yd.itcast.core.service.imp.BaseServiceImp;
import yd.itcast.core.utils.QueryHelper;
import javax.annotation.Resource;
import java.util.*;

@Service("complainService")
public class ComplainServiceImp extends BaseServiceImp<Complain> implements ComplainService {
    private ComplainDao complainDao;

    @Resource
    public void setComplainDao(ComplainDao complainDao) {
        super.setBaseDao(complainDao);
        this.complainDao = complainDao;
    }

    @Override
    public void autoDealComplain() {
        Calendar calendar = Calendar.getInstance();
//    设置当前日期为当月1号0分0秒
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.HOUR_OF_DAY, 0);
        calendar.add(Calendar.MINUTE, 0);
        calendar.add(Calendar.SECOND, 0);
        QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
        queryHelper.addCondition("c.state = ?", Complain.COMPLAIN_STATE_UNDONE);
        queryHelper.addCondition("c.compTime < ?", calendar.getTime());
        List<Complain> complains = complainDao.findAll(queryHelper);
        if (complains != null && complains.size() > 0) {
            for (Complain complain : complains) {
                complain.setState(Complain.COMPLAIN_STATE_INVALID);
                this.update(complain);
            }
        }
    }

    @Override
    public List<Map> findAllByYear(int year) {
        List<Map> resList = new ArrayList<Map>();
        List<Object[]> list = complainDao.findAllByYear(year);
        if(list != null && list.size()>0){
            Calendar cal = Calendar.getInstance();
            boolean isCurYear = (cal.get(Calendar.YEAR) == year);
            int curMonth = cal.get(Calendar.MONTH)+1;
            int temMonth = 0;
            Map<String, Object> map = null;
            for(Object[] obj: list){
                temMonth = Integer.valueOf((obj[0])+"");
                map = new HashMap<String, Object>();
                map.put("label", temMonth+ " 月");
                if(isCurYear){
                    if(temMonth > curMonth){
                        map.put("value", "");
                    } else {
                        map.put("value", obj[1]==null?"0":obj[1]);
                    }
                } else {
                    map.put("value", obj[1]==null?"0":obj[1]);
                }
                resList.add(map);
            }
        }
        return resList;
    }
}
