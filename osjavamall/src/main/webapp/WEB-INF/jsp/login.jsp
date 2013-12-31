<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" type="text/css" href="css/ext-all.css" /> 
<script type="text/javascript" src="js/ext-all.js"> </script> 
<!-- <script type="text/javascript" src="js/MessageBox5.js"> </script> -->
<script type="text/javascript"> 
Ext.onReady(function(){
    var bd = Ext.getBody();
     bd.createChild({tag: 'h2', html: 'form'});//在body中创建一个标签

    var simple = new Ext.FormPanel({
        labelWidth: 75,
        frame:true,
        title: 'Simple Form',
        bodyStyle:'padding:5px 5px 0',//设置容器样式
        width: 350,
        defaults: {width: 230},
        defaultType: 'textfield',
        items: [{
                fieldLabel: '用户名',
                name: 'userName',
                id:'fristId',
                allowBlank:false
            },{
                fieldLabel: '密码',
                name: 'password',
				 inputType: 'password'
            }
        ],
        buttons: [{
            text: '提交',
            handler:function(){
            	var bacform=simple.getForm();
            	
           	    //alert(bacform.findField('userName').getValue());//1
            	
        Ext.Ajax.request({
            		url:'/osjavamall/loginCheck',  
            		params:{'userName':bacform.findField('userName').getValue(),'password':bacform.findField('password').getValue()},
            		method:'POST',  
					waitMsg:'正在登陆...',
            		success:function(result,action){
            		 	var isSuc = eval('('+result.responseText+')'); 
						if(isSuc.success) {  
                        //提示用户登陆成功  
                        	Ext.Msg.alert('消息', '登陆成功..');  
							window.location.href="/osjavamall/main"
                    	}  else{
							Ext.Msg.alert('消息', '错误..');  
						}
            		}
            	});
            	/*bacform.doAction('submit', {  
	                url:'/baobaotao/loginCheck.html',  
	                params:{'userName':bacform.getValues().first,'password':bacform.getValues().second},
	                method:'POST',                        
	                waitMsg:'正在登陆...',
	                success:function(form,action){
	                	var isSuc = action.result.success;
	                	if(isSuc) {  
                        //提示用户登陆成功  
                        	Ext.Msg.alert('消息', '登陆成功..');  
                    	}  
	                }
                });*/
            }
        },{
            text: '重置',
            handler:function(){
            	simple.getForm().reset();
            }
        }]
    });
//simple.addButton([{text:'save'},{text:'cancel'}]);
    simple.render(document.body);
});

var person = new Person("lingo");
person.on('walk',function(){
	Ext.Msg.alert('event',person.name+' go go ');
});
</script> 
<html>
	<head>
		<title>宝宝淘论坛登录</title>
	</head>
	<body>
		<!--<c:if test="${!empty error}">
	        <font color="red"><c:out value="${error}" /></font>
		</c:if>        
		<form action="<c:url value="loginCheck.html"/>" method="post">
			用户名：
			<input type="text" name="userName">
			<br>
			密 码：
			<input type="password" name="password">
			<br>
			<input type="submit" value="登录" />
			<input type="reset" value="重置" />
		</form>-->
	</body>
</html>
