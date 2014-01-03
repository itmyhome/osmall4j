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
<base href="<%=basePath%>">
<script type="text/javascript" src="js/jquery-1.4.2.js"> </script>
<link rel="stylesheet" type="text/css" href="css/table.css">
<title>osjavamall</title>
</head>
<a href="${pageContext.request.contextPath}/user3/create">注册用户</a>
<br />
<form  name="queryForm" action="${pageContext.request.contextPath}/user3/list/1/5" method="post">

用户名:<input type="text" name="userName" value="${pageInfo.search.userName}"/>
<input type="button" value="查询"  onclick="query()"/>
</form>
<table border="1" width="50%">
	<tr>
		<th>用户Id</th>
		<th>用户名</th>
		<th>用户照片</th>
		<th>积分</th>
		<th>最后登陆时间</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${userList}" var="user">
		<tr>
			<td>${user.userId }</td>
			<td>${user.userName }</td>
			<td><%-- <img src="http://192.168.1.199/${user.userImg }"> --%></td>
			<td>${user.credits }</td>
			<td>${user.lastVisit}</td>
			<td><a
				href="${pageContext.request.contextPath}/user3/update?userId=${user.userId}">完善用户信息</a>
				| <a
				href="${pageContext.request.contextPath}/user3/delete?userId=${user.userId}">删除</a>
			</td>
		</tr>
	</c:forEach>
</table>
<!--总${pageInfo.page.totalRowNum}条/共${pageInfo.page.totalPageNum}页
<a href="${pageContext.request.contextPath}/user3/list/1/5">上一页</a> |
<a href="${pageContext.request.contextPath}/user3/list/2/5">下一页</a>-->

<%@ include file="pagenation.jsp"%> 
</body>
</html>
