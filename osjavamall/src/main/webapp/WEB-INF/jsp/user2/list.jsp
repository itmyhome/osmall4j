<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>宝宝淘论坛登录</title>
</head>
<a href="${pageContext.request.contextPath}/user2?action=create">用户新增</a>
<br />
<table border="1" width="50%">
	<tr>
		<th>用户名</th>
		<th>密码</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${userList}" var="user">
		<tr>
			<td>${user.userName }</td>
			<td>${user.password }</td>
			<td><a
				href="${pageContext.request.contextPath}/user2?action=update&userId=${user.userId}">更新</a>
				| <a
				href="${pageContext.request.contextPath}/user2?action=delete&userId=${user.userId}">删除</a>
			</td>
		</tr>
	</c:forEach>
</table>
</html>
