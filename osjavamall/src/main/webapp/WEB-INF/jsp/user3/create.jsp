<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>osjavamall</title>
	</head>
	<form action="${pageContext.request.contextPath}/user3/create" method="post">

用户名：  <input type="text" name="userName" value="${user.userName}"/><br/>
密码：<input type="password" name="password" value="${user.password}"/><br/>
初始积分：<input type="text" name="credits" value="${user.credits}"/><br/>
<input type="submit" name="create" value="新增"/>
</form>

<!--<form:form commandName="user"> 
用户名：<form:input path="userName"/>
<form:errors path="userName" cssStyle="color:red"></form:errors>
<br/>
密码：<form:password path="password"/>
<form:errors path="password" cssStyle="color:red"></form:errors>
<br/>
初始学分：<form:input path="credits"/>
<form:errors path="credits" cssStyle="color:red"></form:errors>
<br/>
<input type="file" name="photo">
<input type="submit" value="新增"/>
</form:form>-->
</html>
