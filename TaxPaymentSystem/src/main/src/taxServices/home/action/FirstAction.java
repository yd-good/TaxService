/*
信息:
*/
package taxServices.home.action;

import com.opensymphony.xwork2.ActionSupport;

public class FirstAction extends ActionSupport{
//    首页
    public String frame(){
        return "frame";
    }
    //    顶部
    public String top(){
        return "top";
    }
    //    菜单
    public String left(){
        return "left";
    }
}
