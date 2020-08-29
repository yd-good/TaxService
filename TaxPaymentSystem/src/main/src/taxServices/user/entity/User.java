/*
信息:
*/
package taxServices.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {

    private String id;
    private String dept;
    private String account;
    private String name;
    private String password;
    private String headImg;
    private boolean gender;
    private String state;
    private String mobile;
    private String email;
    private Date birthday;
    private String remark;
    private List<UserRole> userRoles;
    //用户状态
    public static String USER_SATAE_VALID="1";
    public static String USER_SATAE_INVALID="0";

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", dept='" + dept + '\'' +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", headImg='" + headImg + '\'' +
                ", gender=" + gender +
                ", state='" + state + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", remark='" + remark + '\'' +
                '}';
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static String getUserSataeValid() {
        return USER_SATAE_VALID;
    }

    public static void setUserSataeValid(String userSataeValid) {
        USER_SATAE_VALID = userSataeValid;
    }

    public static String getUserSataeInvalid() {
        return USER_SATAE_INVALID;
    }

    public static void setUserSataeInvalid(String userSataeInvalid) {
        USER_SATAE_INVALID = userSataeInvalid;
    }
}
