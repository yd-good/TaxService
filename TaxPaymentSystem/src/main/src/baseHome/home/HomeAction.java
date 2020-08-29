/*
信息:
*/
package baseHome.home;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import taxServices.complaint.entity.Complain;
import taxServices.complaint.service.ComplainService;
import taxServices.info.entity.Info;
import taxServices.info.service.InfoService;
import taxServices.user.entity.User;
import taxServices.user.service.UserService;
import yd.itcast.core.constant.Constant;
import yd.itcast.core.utils.QueryHelper;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeAction extends ActionSupport {
    @Resource
    private UserService userService;
    @Resource
    private ComplainService complainService;
    private Complain complain;
    private List<User> userList;
    @Resource
    private InfoService infoService;
    private Info info;
    @Test
    public String execute(){
        ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
        QueryHelper queryHelper=new QueryHelper(Info.class,"i");
        queryHelper.addOrderBy("i.createTime",QueryHelper.ORDER_BY_DESC);
        List<Info> infoList=infoService.findPageHelper(1,5,queryHelper).getItems();
        ActionContext.getContext().getContextMap().put("infoList",infoList);

         ActionContext.getContext().getContextMap().put("complainStateMap",Complain.COMPLAIN_STATE_MAP);
        QueryHelper queryHelper2=new QueryHelper(Complain.class,"c");
        queryHelper2.addCondition("c.compName = ?",((User)ServletActionContext.getRequest().getSession().getAttribute(Constant.USER)).getName());
        queryHelper2.addOrderBy("c.compTime",QueryHelper.ORDER_BY_DESC);
        List<Complain> complains=complainService.findPageHelper(1,5,queryHelper2).getItems();
        ActionContext.getContext().getContextMap().put("complainList",complains);

        return "home";
    }
    public String complainAddUI(){
        return "complainAddUI";
    }
    public String infoViewUI(){
        ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
        if (info!=null){
            info=infoService.findById(info.getInfoId());
        }
        return "infoViewUI";
    }

    public String complainViewUI(){
        ActionContext.getContext().getContextMap().put("complainStateMap",Complain.COMPLAIN_STATE_MAP);
        if (complain!=null){
            complain=complainService.findById(complain.getCompId());
        }
        return "complainViewUI";
    }
    public void deSearchDept() throws Exception {
        String dept=ServletActionContext.getRequest().getParameter("dept");
        String resultString="false";
        JSONObject jsonObject=new JSONObject();
        if (StringUtils.isNotBlank(dept)) {
            QueryHelper queryHelper = new QueryHelper(User.class, "u");
            queryHelper.addCondition("u.dept = ?",dept);
            userList = userService.findbyUserDept(queryHelper);
            if (userList!=null&&userList.size()>0) {
                resultString="success";
            }
        }
//        将数据转为json类型以至于前端就接收
        jsonObject.put("msg",resultString);
        jsonObject.accumulate("userList",userList);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(jsonObject.toString().getBytes("utf-8"));
        outputStream.close();
    }
    public void complainAdd() throws Exception {
     if (complain!=null){
         complain.setCompTime(new Timestamp(new Date().getTime()));
         complain.setState(Complain.COMPLAIN_STATE_UNDONE);
         complainService.save(complain);
         HttpServletResponse response=ServletActionContext.getResponse();
         response.setContentType("text/html");
         ServletOutputStream outputStream=response.getOutputStream();
         outputStream.write("success".getBytes("utf-8"));
         outputStream.close();
     }
    }
    public Complain getComplain() {
        return complain;
    }
    public void setComplain(Complain complain) {
        this.complain = complain;
    }
    public Info getInfo() {
        return info;
    }
    public void setInfo(Info info) {
        this.info = info;
    }
}
