<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.gh.app.manage.entity.AppConfig"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
String ctx = request.getContextPath();
%>
<title><%=AppConfig.APP_TITLE %></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport" />
<script>
var ctx ='<%=ctx%>';
</script>
