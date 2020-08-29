/*
信息:
*/
package taxServices.user.service.imp;

import org.springframework.stereotype.Service;
import taxServices.role.entity.Role;
import taxServices.user.dao.UserDao;
import taxServices.user.entity.User;
import taxServices.user.entity.UserRole;
import taxServices.user.entity.UserRoleId;
import taxServices.user.service.UserService;
import taxServices.user.utils.ExcelUtils;
import yd.itcast.core.exception.ServiceException;
import yd.itcast.core.service.imp.BaseServiceImp;
import yd.itcast.core.utils.PageHelper;
import yd.itcast.core.utils.QueryHelper;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.List;
@Service("userService")
public class UserServiceImp extends BaseServiceImp<User> implements UserService {
    private UserDao userDao;
    @Resource
    public void setUserDao(UserDao userDao) {
        super.setBaseDao(userDao);
        this.userDao = userDao;
    }

    @Override
    public void exportExcel(List<User> userList, ServletOutputStream outputStream)throws Exception{
        ExcelUtils.exportExcelUtils(userList,outputStream);
    }

    @Override
    public void importExcel(File userExcel, String userExcelFileName) {
     List<User> userList= ExcelUtils.importExcel(userExcel,userExcelFileName);
     if (userList!=null&&userList.size()>0){
         for (int i=0;i<userList.size();i++){
             userDao.save(userList.get(i));
         }
     }
    }

    @Override
    public List<User> findByAccountAndId(String id, String account){
        return userDao.findByAccountAndId(id,account);
    }

    @Override
    public void saveUserAndRole(User user, String[] userRoleIds) {
        userDao.save(user);
        if (userRoleIds!=null){
            for (String userRoleId:userRoleIds){
                userDao.saveUserRole(new UserRole(new UserRoleId(user.getId(),new Role(userRoleId))));
            }
        }
    }

    @Override
    public void updateUserAndRoleIds(User user, String... userRoleIds) {
           userDao.deleteUserRoleIds(user.getId());
           userDao.update(user);
           if (userRoleIds!=null){
               for (String userRoleId:userRoleIds){
                   userDao.saveUserRole(new UserRole(new UserRoleId(user.getId(),new Role(userRoleId))));
               }
           }
    }

    @Override
    public List<UserRole> findUserRoleByUserId(String id) {
        return userDao.findUserRoleByUserId(id);
    }

    @Override
    public List<User> findUserByAccountAndPassword(String account, String password) {
        return userDao.indUserByAccountAndPassword(account,password);
    }

    @Override
    public List<User> findbyUserDept(QueryHelper queryHelper) {
        return userDao.findByUserDept(queryHelper);
    }


}
