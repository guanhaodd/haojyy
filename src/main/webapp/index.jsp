<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.gh.app.manage.entity.AppConfig"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title><%=AppConfig.APP_TITLE %></title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<script language="javascript">
	 function forward(){
		  window.location="<%=basePath%>/login/to_login";
	 }
	</script>
  </head>
<body onload="forward();">
<div id="box">
	正在加载,请等候...   <img src="<%=basePath%>/resource/images/wait.gif"/>
</div>
</body>
</html>
