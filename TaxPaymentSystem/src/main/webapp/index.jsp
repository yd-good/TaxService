<%--<%@page contentType="text/html;charset=UTF-8"  language="java.*" import="java" %>--%>
<%
String path=request.getContextPath();
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.sendRedirect(basePath+"sys/login_toLoginUI.action");
%>