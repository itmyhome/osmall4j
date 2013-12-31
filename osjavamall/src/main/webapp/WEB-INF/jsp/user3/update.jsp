<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>个人信息修改</title>
		<script type="text/javascript" src="js/jquery-1.4.2.js"> </script>
		<script type="text/javascript" src="js/uploadify/jquery.uploadify.min.js"> </script>
		<link rel="stylesheet" type="text/css" href="css/uploadify.css">
		<script>
			var fileName;
			var base_path = "http://192.168.1.199/";
			$(function() {
				$("#uploadImg").uploadify({
					'auto'     : false,						  //是否自动上传
					'buttonText' : '选择文件...',		 //按钮文字
					//'buttonImage' : 'images/uploadify/uploadify-cancel.png',	//按钮图片
					//'checkExisting' : '/uploadify/check-exists.php', //检查文件是否在上传队列 是的话 返回是否替换的确认框
					'fileExt'   : '*.jpg;*.gif;*.jpeg;*.png;*.bmp;', //上传文件的后缀名 *.zip;*.rar;*.7z
					'fileObjName' : 'fileName', //设定从request中获取文件的名字
					'formData' : {  
							'userName' : 'willwind',  
							'content' : 'aaaaaaaaaaaaaaaaaaaaaaaaaa'  
					 },  
					'height'        : 30,
					'multi'    : false, //设置同一时间上传的文件数
					'swf'          : 'js/uploadify/uploadify.swf',
					
					'uploader'      : 'upload',
					'width'         : 120,
					'onUploadStart' : function(file) {  
						 $("#stopUpload").removeAttr("hidden");
						//$("#uploadImg").uploadify("settings", "formData"); 
					},  
					//以下为事件
					//'onCancel' : function(file) {
						//取消文件上传后触发的事件
						//alert('The file ' + file.name + ' was cancelled.');
					 //}
					 'onUploadSuccess' : function(file, data, response) {  
							//alert(file.name + " upload success !");  
							//alert(data);
							$("#showImg").attr("src", base_path+data);
							$("#userImg").attr("value", base_path+data);
							$("#stopUpload").attr("hidden",true);  
					  }  
					
				});
			});

		</script>
	</head>
	<form action="${pageContext.request.contextPath}/user3/update" method="post">
	<input type="hidden" name="userId" value="${user.userId }"/>
	<input type="hidden" id="userImg" name="userImg" value=""/>
用户名：  <input type="text" name="userName" value="${user.userName}"/><br/>
  <table>
        <tr>
            <td><label> 上传照片：<img id="showImg" src="http://192.168.1.199/${user.userImg}"></label></td>
            <td><label><input type="file" id="uploadImg" name="uploadImg" /></label>&nbsp;&nbsp;</td>
			<td><a href="javascript:$('#uploadImg').uploadify('upload','*')">上传</a></td>
			<td><a href="javascript:$('#uploadImg').uploadify('stop','*')" hidden=true id="stopUpload">停止上传</a>  </td>
            <td><div id="fileQueue"></div></td>
        </tr>
   </table>
密码：<input type="password" name="password" value=""/><span style="color:red">密码若不修改请留空</span><br/>
昵称:  <input type="text" name="nickName" value="${user.nickName}"/><br/>
手机号: <input type="text" name="mobilePhone" value="${user.mobilePhone}"/><br/>
真实姓名:<input type="text" name="realName" value="${user.realName}"/><br/>
身份证号:<input type="text" name="postCard" value="${user.postCard}"/><br/>
所在地:<select></select><select></select><select></select><br/>

<input type="submit" name="update" value="修改"/>
</form> 
</html>
