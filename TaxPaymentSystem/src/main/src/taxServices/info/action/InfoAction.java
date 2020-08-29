/*
信息:
*/
package taxServices.info.action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.deploy.net.HttpResponse;
import org.apache.struts2.ServletActionContext;
import taxServices.info.entity.Info;
import taxServices.info.service.InfoService;
import yd.itcast.core.action.BaseAction;
import yd.itcast.core.exception.ActionException;
import yd.itcast.core.utils.PageHelper;
import yd.itcast.core.utils.QueryHelper;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class InfoAction extends BaseAction {
    @Resource
    private InfoService infoService;
    private List<Info> infoList;
    private Info info;
    private String[] selectedList;
    public String listUI() throws ActionException {
        QueryHelper queryHelper=new QueryHelper(Info.class,"info");
        if (info!=null){
            queryHelper.addCondition("info.title like ?","%"+info.getTitle()+"%");
            queryHelper.addOrderBy("info.createTime",QueryHelper.ORDER_BY_DESC);
        }
        pageHelper=infoService.findPageHelper(getCurrentPageNo(),getPageSize(),queryHelper);
        ActionContext.getContext().getContextMap().put("infoTypeMap",Info.INFO_TYPE_MAP);
        return "listUI";
    }
    public String addUI(){
        ActionContext.getContext().getContextMap().put("infoTypeMap",Info.INFO_TYPE_MAP);
        info=new Info();
        info.setCreateTime(new Timestamp(new Date().getTime()));
        return "addUI";
    }
    public String add() throws Exception {
       infoService.save(info);
       info.setTitle("");
        return "list";
    }
    public String editUI(){
        oldsAttributes=info.getTitle();
        ActionContext.getContext().getContextMap().put("infoTypeMap",Info.INFO_TYPE_MAP);
        info=infoService.findById(info.getInfoId());
        return "editUI";
    }
    public String edit()throws Exception{
        if (info!=null){
            infoService.update(info);
            info.setTitle(oldsAttributes);
        }
        return "list";
    }
    public String delete(){
        if(info!=null) {
            infoService.delete(info.getInfoId());
        }
        return "list";
    }
    public String deleteSelected(){
        if (selectedList!=null&&selectedList.length>=1){
            for (String select:selectedList){
                infoService.delete(select);
            }
        }
        return "list";
    }
    public void publicInfo() throws IOException {
        if (info!=null) {
            String state=info.getState();
            info = infoService.findById(info.getInfoId());
            info.setState(state);
            infoService.update(info);
            HttpServletResponse response= ServletActionContext.getResponse();
            response.setContentType("text/html");
            ServletOutputStream outputStream=response.getOutputStream();
            outputStream.write("success".getBytes("utf-8"));
            outputStream.close();
        }
    }
    public String[] getSelectedList() {
        return selectedList;
    }
    public void setSelectedList(String[] selectedList) {
        this.selectedList = selectedList;
    }
    public Info getInfo() {
        return info;
    }
    public void setInfo(Info Info) {
        this.info = Info;
    }
    public List<Info> getInfoList() {
        return infoList;
    }
    public void setInfoList(List<Info> InfoList) {
        this.infoList = InfoList;
    }
    public InfoService getInfoService() {
        return infoService;
    }
    public void setInfoService(InfoService infoService) {
        this.infoService = infoService;
    }
}
