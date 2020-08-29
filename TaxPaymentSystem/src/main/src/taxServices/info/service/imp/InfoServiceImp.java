/*
信息:
*/
package taxServices.info.service.imp;

import org.springframework.stereotype.Service;
import taxServices.info.dao.InfoDao;
import taxServices.info.entity.Info;
import taxServices.info.service.InfoService;
import yd.itcast.core.service.imp.BaseServiceImp;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service("infoService")
public class InfoServiceImp extends BaseServiceImp<Info> implements InfoService{
    private InfoDao infoDao;
    @Resource
    public void setInfoDao(InfoDao infoDao) {
        super.setBaseDao(infoDao);
        this.infoDao = infoDao;
    }
}
