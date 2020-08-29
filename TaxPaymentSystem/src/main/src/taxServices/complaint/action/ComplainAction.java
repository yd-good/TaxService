/*
信息:
*/
package taxServices.complaint.action;

import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import taxServices.complaint.entity.Complain;
import taxServices.complaint.entity.ComplainReply;
import taxServices.complaint.service.ComplainService;
import yd.itcast.core.action.BaseAction;
import yd.itcast.core.utils.QueryHelper;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class ComplainAction extends BaseAction {
    @Resource
    private ComplainService complainService;
    private Complain complain;
    private String startTime;
    private String endTime;
    private ComplainReply reply;
    public String listUI() throws ParseException {
        ActionContext.getContext().getContextMap().put("complainStateMap",Complain.COMPLAIN_STATE_MAP);
        QueryHelper queryHelper=new QueryHelper(Complain.class,"c");
        if (StringUtils.isNotBlank(startTime)){
            queryHelper.addCondition("c.compTime >= ? ", DateUtils.parseDate(startTime+":00","yyyy-MM-dd HH:mm:ss"));
        }
        if (StringUtils.isNotBlank(endTime)){
            queryHelper.addCondition("c.compTime <= ? ",DateUtils.parseDate(endTime+":59","yyyy-MM-dd HH:mm:ss"));
        }
        if (complain!=null) {
            if (StringUtils.isNotBlank(complain.getCompTitle())) {
                queryHelper.addCondition("c.compTitle like ? ", "%"+complain.getCompTitle()+"%");
            }
            if (StringUtils.isNotBlank(complain.getState())) {
                queryHelper.addCondition("c.state= ? ", complain.getState());
            }
        }
        queryHelper.addOrderBy("c.compTime",QueryHelper.ORDER_BY_DESC);
        pageHelper=complainService.findPageHelper(getCurrentPageNo(),getPageSize(),queryHelper);
        return "listUI";
    }
    public String dealUI(){
        ActionContext.getContext().getContextMap().put("complainStateMap",Complain.COMPLAIN_STATE_MAP);
        if (complain!=null){
            complain=complainService.findById(complain.getCompId());
        }
        return "dealUI";
    }
    public String deal(){
        complain=complainService.findById(complain.getCompId());
     if (!Complain.COMPLAIN_STATE_DONE.equals(complain.getState())){
         complain.setState(Complain.COMPLAIN_STATE_DONE);
     }
     if (reply!=null) {
         reply.setComplain(complain);
         reply.setReplyTime(new Timestamp(new Date().getTime()));
         complain.getComplainReplies().add(reply);

     }
     complainService.update(complain);
        return "list";
    }
    public String newAnnualStatisticChartUI(){
        return "newAnnualStatisticChartUI";
    }
    public void getAnnualStatisticData() throws IOException {
        int year=0;
       if ( ServletActionContext.getRequest().getParameter("year")!=null){
         year=Integer.valueOf(ServletActionContext.getRequest().getParameter("year"));
        }else {
        year= Calendar.getInstance().get(Calendar.YEAR);
       }
         JSONObject jsonObject=new JSONObject();
         jsonObject.accumulate("charData",complainService.findAllByYear(year));
        HttpServletResponse response= ServletActionContext.getResponse();
        response.setContentType("json/html");
        ServletOutputStream outputStream=response.getOutputStream();
        outputStream.write(jsonObject.toString().getBytes("utf-8"));
        outputStream.close();

    }
    public ComplainService getComplainService() {
        return complainService;
    }
    public void setComplainService(ComplainService complainService) {
        this.complainService = complainService;
    }
    public Complain getComplain() {
        return complain;
    }
    public void setComplain(Complain complain) {
        this.complain = complain;
    }
    public ComplainReply getReply() {
        return reply;
    }
    public void setReply(ComplainReply reply) {
        this.reply = reply;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
