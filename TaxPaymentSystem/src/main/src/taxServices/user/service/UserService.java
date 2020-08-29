package taxServices.user.service;
import taxServices.user.entity.User;
import taxServices.user.entity.UserRole;
import yd.itcast.core.exception.ServiceException;
import yd.itcast.core.service.BaseService;
import yd.itcast.core.utils.QueryHelper;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.List;
public interface UserService extends BaseService<User> {
    void exportExcel(List<User> userList, ServletOutputStream outputStream)throws Exception;

    void importExcel(File userExcel, String userExcelFileName);

    List<User> findByAccountAndId(String id, String account);

    void saveUserAndRole(User user,String... userRoleIds);

    void updateUserAndRoleIds(User user,String... userRoleIds);

    List<UserRole> findUserRoleByUserId(String id);

    List<User> findUserByAccountAndPassword(String account, String password);

    List<User> findbyUserDept(QueryHelper queryHelper);
}
