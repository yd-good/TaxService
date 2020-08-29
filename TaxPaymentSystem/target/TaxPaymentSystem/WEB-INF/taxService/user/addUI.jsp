<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>用户管理</title>
    <style type="text/css">
        #yes{
            position: relative;
            top: 120px;
            right: -385px;
            display: none;
            width: 20px;
            height: 20px;
        }
        #no{
            position: relative;
            top: 120px;
            right: -385px;
            display: none;
            width: 20px;
            height: 20px;
        }
    </style>
    <script type="text/javascript" src="${basePath}js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript">
        var  fResult=false;
        function addDiVerify() {
            var yes=document.getElementById("yes");
            var no=document.getElementById("no");
            var account=$("#account").val();
             if (account!=""){
                 $.ajax({
                     url:"${basePath}taxService/user_verifyAccount.action",
                     data:{"user.account":account},
                     async:false,
                     type:"POST",
                     success:function (message) {
                         if ("true"==message){
                             no.style.display="block";
                             yes.style.display="none";
                             $("#account").onfocus;
                             fResult=false;
                         }else {
                             no.style.display="none";
                             yes.style.display="block";
                             fResult=true;
                         }
                     }
                 })
             }
        }
       function addSubmit(){
           addDiVerify()
          if (fResult){
              document.forms[0].submit();
          } else {
              alert("账号已经存在，请重新输入")
              return false;
          }
       }
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath}taxService/user_add.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;新增用户</div></div>
    <div class="tableH2">新增用户</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">所属部门：</td>
<%--            s:selectd中的list标签会将list的内容遍历出来 最后将key传到对应属性中--%>
            <td><s:select name="user.dept" list="#{'部门A':'部门A','部门B':'部门B'}"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">头像：</td>
            <td>
                <input type="file" name="headImg"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">用户名：</td>
            <td><s:textfield name="user.name"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
<%--            进行Ajax验证--%>
            <td><s:textfield name="user.account" onchange="addDiVerify()" id="account"/></td><div id="no"><img src="${bastPath}images/common/error.jpg" width="20" height="20"/></div><div id="yes"><img src="${basePath}images/common/yes.jpg" width="20" height="20"/></div>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><s:textfield name="user.password"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">性别：</td>
            <td><s:radio list="#{'true':'男','false':'女'}" name="user.gender"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色：</td>
            <td><s:checkboxlist list="#request.roleList" listKey="roleId" listValue="roleName" name="userRoleIds"/></td>
<%--            其中#request.roleuserList对应后台放入request作用域的roleuserList这个list；--%>
<%--            listKey="roleId"--%>
<%--            这里的roleId对应后台roleuserList中role这个bean的属性roleId，listKey也是将要传入后台的值；--%>
<%--            listValue="roleName" 自然是显示在页面上的内容了；--%>
<%--            value="#request.rolelist.{roleId}"--%>
<%--            这句话的意思是，给s:checkboxlist选取默认值，将对应后台放入request作用域的rolelist这个list中的roleid组合成数组，--%>
<%--            类似{1,2,3}这种，然后标签会自动匹配。--%>
<%--            name="roleIds“ 这个roleIds可以在后台action中使用get set一个roleIds数组属性来取值，或者直接用request作用域取值，都要放数组中--%>
<%--            <td><s:checkboxlist list="#roleList" name="userRoleIds" listKey="roleId" listValue="name"></s:checkboxlist></td>--%>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电子邮箱：</td>
            <td><s:textfield name="user.email"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">手机号：</td>
            <td><s:textfield name="user.mobile"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">生日：</td>
            <td><s:textfield id="birthday" name="user.birthday" readonly="true" onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd'});">
<%--                <s:param name="value"><s:date name="user.birthday" format="yyyy-MM-dd"/></s:param>--%>
            </s:textfield>
            </td>
        </tr>
		<tr>
            <td class="tdBg" width="200px">状态：</td>
            <td><s:radio list="#{'1':'有效','0':'无效'}" name="user.state" value="1"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <td><s:textarea name="user.remark" cols="75" rows="3"/></td>
        </tr>
    </table>
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="addSubmit()"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>