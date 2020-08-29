/*
信息:
*/
package taxServices.role.action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.ws.encoding.HasEncoding;
import taxServices.info.entity.Info;
import taxServices.role.entity.Role;
import taxServices.role.entity.RolePrivilege;
import taxServices.role.entity.RolePrivilegeId;
import taxServices.role.service.RoleService;
import yd.itcast.core.action.BaseAction;
import yd.itcast.core.constant.Constant;
import yd.itcast.core.exception.ActionException;
import yd.itcast.core.utils.QueryHelper;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

public class RoleAction  extends BaseAction {
    @Resource
    private RoleService roleService;
    private List<Role> roleList;
    private Role role;
    private String[] selectedList;
    private String[] privilegeIds;
    public String listUI() throws ActionException {
        QueryHelper queryHelper=new QueryHelper(Role.class,"role");
        if (role!=null){
            queryHelper.addCondition("role.roleName like ?","%"+role.getRoleName()+"%");
        }
        pageHelper=roleService.findPageHelper(getCurrentPageNo(),getPageSize(),queryHelper);
        ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_Map);
        return "listUI";
    }
    public String addUI(){
        ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_Map);
        return "addUI";
    }
    public String add() throws Exception {
        if (role!=null) {
            if (privilegeIds != null) {
                HashSet<RolePrivilege> privilegeHashSet = new HashSet<RolePrivilege>();
                for (int i = 0; i < privilegeIds.length; i++) {
                    privilegeHashSet.add(new RolePrivilege(new RolePrivilegeId(privilegeIds[i], role)));
                }
                role.setRolePrivileges(privilegeHashSet);
            }
            roleService.save(role);
        }
        role.setRoleName("");
        return "list";
    }
    public String editUI(){
        if (role!=null&&role.getRoleId()!=null)
            role=roleService.findById(role.getRoleId());
        ActionContext.getContext().getContextMap().put("privilegeMap",Constant.PRIVILEGE_Map);
        if (role.getRolePrivileges()!=null){
            privilegeIds=new String[role.getRolePrivileges().size()];
            int i=0;
            for (RolePrivilege rolePrivilege:role.getRolePrivileges()){
             privilegeIds[i++]=rolePrivilege.getId().getCode();
            }
            oldsAttributes=role.getRoleName();
        }
        return "editUI";
    }
    public String edit()throws Exception{
        if (role!=null){
            if (privilegeIds!=null){
                HashSet<RolePrivilege> privilegeHashSet=new HashSet<RolePrivilege>();
                for (int i=0;i<privilegeIds.length;i++)
                privilegeHashSet.add(new RolePrivilege(new RolePrivilegeId(privilegeIds[i],role)));
                role.setRolePrivileges(privilegeHashSet);
            }
            roleService.update(role);
            role.setRoleName(oldsAttributes);
        }
        return "list";
    }
    public String delete(){
        if(role!=null) {
            roleService.delete(role.getRoleId());
        }
        return "list";
    }
    public String deleteSelected(){
        if (selectedList!=null&&selectedList.length>=1){
            for (String select:selectedList){
               roleService.delete(select);
            }
        }
        return "list";
    }

    public String[] getSelectedList() {
        return selectedList;
    }
    public void setSelectedList(String[] selectedList) {
        this.selectedList = selectedList;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role Role) {
        this.role = Role;
    }
    public List<Role> getRoleList() {
        return roleList;
    }
    public void setRoleList(List<Role> RoleList) {
        this.roleList = RoleList;
    }
    public RoleService getRoleService() {
        return roleService;
    }
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    public String[] getPrivilegeIds() {
        return privilegeIds;
    }
    public void setPrivilegeIds(String[] privilegeIds) {
        this.privilegeIds = privilegeIds;
    }
}
