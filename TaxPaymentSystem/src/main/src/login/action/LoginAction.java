/*
信息:
*/
package login.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import taxServices.user.entity.User;
import taxServices.user.service.UserService;
import yd.itcast.core.constant.Constant;

import javax.annotation.Resource;
import java.util.List;

public class LoginAction extends ActionSupport{
    private User user;
    private String resultString;
    @Resource
    private UserService userService;
    public String toLoginUI(){
        return "loginUI";
    }
    public String login(){
        if (user!=null){
            if (StringUtils.isNotBlank(user.getAccount())&&StringUtils.isNotBlank(user.getPassword())){
                List<User> userList=userService.findUserByAccountAndPassword(user.getAccount(),user.getPassword());
                if (userList.size()>0&&userList!=null){
                    User user=userList.get(0);
                    user.setUserRoles(userService.findUserRoleByUserId(user.getId()));
                    ServletActionContext.getRequest().getSession().setAttribute(Constant.USER,user);
                    Log log=LogFactory.getLog(getClass());
                    log.info("用户"+user.getName()+"登录了系统");
                    return "home";
                }
            }else {
                resultString="账号或密码不能为空！";
            }
        }
        return toLoginUI();
    }
    public String loginOut(){
        ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
        return toLoginUI();
    }
    public String noPermissionUI(){
        return "noPermissionUI";
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
