<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>宝宝淘论坛登录</title>
	</head>
	<a href="${pageContext.request.contextPath}/user2">用户列表</a>
	<form action="${pageContext.request.contextPath}/user2" method="post">
		<input type="hidden" name="userId" value="${user.userId }"/>
		<input type="hidden" name="action" value="update"/>
用户名：  <input type="text" name="userName" value="${user.userName}"/><br/>
密码：<input type="password" name="password" value=""/><br/>
<input type="submit"  value="修改"/>
</form> 
</html>
