/*
信息:
*/
package taxServices.user.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import taxServices.info.entity.Info;
import taxServices.role.service.RoleService;
import taxServices.user.entity.User;
import taxServices.user.entity.UserRole;
import taxServices.user.service.UserService;
import yd.itcast.core.action.BaseAction;
import yd.itcast.core.exception.ActionException;
import yd.itcast.core.exception.MyException;
import yd.itcast.core.exception.ServiceException;
import yd.itcast.core.utils.QueryHelper;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class UserAction extends BaseAction {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    private User user;
    private String[] selectedList;
    private File headImg;
    private String headImgContentType;
    private String headImgFileName;
    private File userExcel;
    private String userExcelContentType;
    private String userExcelFileName;
    private String[] userRoleIds;
    public String listUI() throws ActionException{
        QueryHelper queryHelper=new QueryHelper(User.class,"user");
        if (user!=null){
            queryHelper.addCondition("user.name like ?","%"+user.getName()+"%");
        }
         pageHelper=userService.findPageHelper(getCurrentPageNo(),getPageSize(),queryHelper);
          return "listUI";
    }
    public String addUI(){
        ActionContext.getContext().getContextMap().put("roleList",roleService.findAll());
        return "addUI";
    }
    public String add() throws Exception {
        if (user!=null) {
            if (headImg!=null){
             image_processing();
            }
            userService.saveUserAndRole(user,userRoleIds);
            user.setName("");
        }
        return "list";
    }
    public String editUI(){
        if (user!=null&&user.getId()!=null)
        user=userService.findById(user.getId());
        oldsAttributes=user.getName();
        ActionContext.getContext().getContextMap().put("roleList",roleService.findAll());
        List<UserRole> userRoleList=userService.findUserRoleByUserId(user.getId());
        if (userRoleList!=null&&userRoleList.size()>0){
            userRoleIds=new String[userRoleList.size()];
            for (int i=0;i<userRoleList.size();i++){
                userRoleIds[i]=userRoleList.get(i).getId().getRole().getRoleId();
            }
        }
        return "editUI";
    }
    public String edit()throws Exception{
        if (user!=null) {
            if (headImg!=null){
                image_processing();
            }
            userService.updateUserAndRoleIds(user,userRoleIds);
            user.setName(oldsAttributes);
        }
        return "list";
    }
    public String delete(){
        if(user!=null) {
            userService.delete(user.getId());
        }
        return "list";
    }
    public String deleteSelected(){
      if (selectedList!=null&&selectedList.length>=1){
          for (String select:selectedList){
              userService.delete(select);
          }
      }
        return "list";
    }
    private void image_processing() throws IOException {
        String filePath= ServletActionContext.getServletContext().getRealPath("upload/userHeadImg");
        String fileName= UUID.randomUUID().toString().replace("-","")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
        FileUtil.copyFile(headImg,new File(filePath,fileName));
        user.setHeadImg("userHeadImg/"+fileName);
    }
    public void exportExcel()throws MyException {
        HttpServletResponse response=ServletActionContext.getResponse();
//        响应内容是excel文件
        response.setContentType("application/x-excel");
        try {
            //content-disposition控制用户请求所得的内容存为一个文件的时候提供一个默认的文件名，文件直接在浏览器上显示或者在访问时弹出文件下载对话框。
            //attachment是采用附件的方式下载
            response.setHeader("Content-Disposition","attachment;fileName="+new String("用户列表.xls".getBytes(),"ISO-8859-1"));
            ServletOutputStream outputStream=response.getOutputStream();
            userService.exportExcel(userService.findAll(),outputStream);
            if (outputStream!=null){
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String importExcel(){
      if (userExcel!=null&&userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
               userService.importExcel(userExcel,userExcelFileName);
      }
        return "list";
    }
    public void verifyAccount() throws IOException {
        if (user!=null&&StringUtils.isNotBlank(user.getAccount())){
             String result="flase";
             List<User> list=userService.findByAccountAndId(user.getId(),user.getAccount());
             if (list.size()>0&&list!=null){
                 result="true";
             }
             HttpServletResponse response=ServletActionContext.getResponse();
             response.setContentType("text/html");
             ServletOutputStream outputStream=response.getOutputStream();
             outputStream.write(result.getBytes());
             outputStream.close();
        }
    }

    public String[] getSelectedList() {
        return selectedList;
    }
    public void setSelectedList(String[] selectedList) {
        this.selectedList = selectedList;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public File getHeadImg() {
        return headImg;
    }
    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }
    public String getHeadImgContentType() {
        return headImgContentType;
    }
    public void setHeadImgContentType(String headImgContenType) {
        this.headImgContentType = headImgContenType;
    }
    public String getHeadImgFileName() {
        return headImgFileName;
    }
    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }
    public File getUserExcel() {
        return userExcel;
    }
    public void setUserExcel(File userExcel) {
        this.userExcel = userExcel;
    }
    public String getUserExcelContentType() {
        return userExcelContentType;
    }
    public void setUserExcelContentType(String userExcelContentType) {
        this.userExcelContentType = userExcelContentType;
    }
    public String getUserExcelFileName() {
        return userExcelFileName;
    }
    public void setUserExcelFileName(String userExcelFileName) {
        this.userExcelFileName = userExcelFileName;
    }
    public String[] getUserRoleIds() {
        return userRoleIds;
    }
    public void setUserRoleIds(String[] userRoleIds) {
        this.userRoleIds = userRoleIds;
    }
}
