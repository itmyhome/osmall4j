<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>">
	<title>商品类别管理</title>
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui/demo.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/itemCat.js"></script>
	<script>
		$(function(){
			$('#itemCatList').datagrid({
				//method:'get', //  请求远程数据的 method 类型。  默认值 post
				title:'商品类别',
				iconCls:'icon-save',
				width:750,
				height:350,
				nowrap: true,
				autoRowHeight: false,
				striped: true,
				collapsible:true,
				url:'itemCat/queryList',
				sortName: 'code',
				sortOrder: 'desc',
				remoteSort: true,
				singleSelect:true,
				idField:'code',
				frozenColumns:[[
	                {field:'ck',checkbox:true},
	                {title:'cid',field:'cid',width:80,sortable:true}
				]],
				columns:[[
			      
					{field:'name',title:'Name',width:120},
					
					{field:'isParent',title:'是否为父类目',width:220,formatter:function(value,rowObj){
								if(rowObj.isParent=="true"){
									return "是";
								}else{
									return "否";
								}
							}
					},
					{field:'parentCid',title:'父级id',width:120},
					{field:'status',title:'状态',width:150,formatter:function(value,rowObj){
								if(rowObj.status=="1"){
									return "正常";
								}else{
									return "废除";
								}
							}}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'商品类别新增',
					iconCls:'icon-add',
					handler:function(){
						//$('#btnsave').linkbutton('enable');
						$('#addDlg').dialog('open').dialog('setTitle', '添加商品类别信息');//打开对话框  
					}
				},'-',{
					id:'btncut',
					text:'商品类别修改',
					iconCls:'icon-edit',
					handler:function(){
						//$('#btnsave').linkbutton('enable');
						updaterow();
					}
				},'-',{
					id:'btncut',
					text:'商品类别删除',
					iconCls:'icon-cut',
					handler:function(){
						//$('#btnsave').linkbutton('enable');
						alert("删除。。暂未实现")
					}
				},'-',{
					id:'btnsave',
					text:'保存',
					disabled:true,
					iconCls:'icon-save',
					handler:function(){
						$('#btnsave').linkbutton('disable');
						alert('save')
					}
				}]
			});
			var p = $('#itemCatList').datagrid('getPager');
			$(p).pagination({
				onBeforeRefresh:function(){
					//alert('before refresh');
				}
			});
		});
		function resize(){
			$('#itemCatList').datagrid('resize', {
				width:700,
				height:400
			});
		}
		function getSelected(){
			var selected = $('#itemCatList').datagrid('getSelected');
			if (selected){
				alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
			}
		}
		function getSelections(){
			var ids = [];
			var rows = $('#itemCatList').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].code);
			}
			alert(ids.join(':'));
		}
		function clearSelections(){
			$('#itemCatList').datagrid('clearSelections');
		}
		function selectRow(){
			$('#itemCatList').datagrid('selectRow',2);
		}
		function selectRecord(){
			$('#itemCatList').datagrid('selectRecord','002');
		}
		function unselectRow(){
			$('#itemCatList').datagrid('unselectRow',2);
		}
		function mergeCells(){
			$('#itemCatList').datagrid('mergeCells',{
				index:2,
				field:'addr',
				rowspan:2,
				colspan:2
			});
		}
	</script>
</head>
<body>
	<h2>商品类别管理页面</h2>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>您可以对商品类别进行维护</div>
	</div>
	
	<div style="margin:10px 0;">
		
	</div>
	
	<table id="itemCatList"></table>
	<!-- 添加商品类型信息的表单       -->  
    <div id="addDlg" class="easyui-dialog" style="width: 580px; height: 350px; padding: 10px 20px" closed="true" buttons="#addDlgBtn">  
        <form id="itemForm" method="post">  
            <table>
				<input name="cid" class="easyui-validatebox" hidden=true>
                <tr>  
                    <td>类别名称</td>  
                    <td>  
                      <input name="name" class="easyui-validatebox" required="true" missingMessage="类别名称不能为空">  
                    </td>  
                </tr>  
                <tr>  
                    <td>是否为父级类目</td>  
                    <td>  
                        <!-- 使用Easyui中的combobox -->   
                        <select class="easyui-combobox" style="width: 155px;" name="isParent"  data-options="panelHeight:'auto'">  
                            <option value="1">是</option>  
                            <option value="0">否</option>  
                       </select>  
                    </td>  
                </tr>  
                <tr>  
                    <td>状态</td>  
                    <td>  
                        <select class="easyui-combobox" style="width: 155px;" name="status" data-options="panelHeight:'auto'">  
                            <option value="1">正常</option>  
                            <option value="0">删除</option>  
                       </select>
                    </td>  
                </tr>  
				<tr>  
                    <td>排列序号</td>  
                    <td>  
                      <input name="sortOrder" class="easyui-validatebox">  
                    </td>  
                </tr>  
            </table>  
        </form>  
    </div>  
    <!-- 保存信息的按钮，被Jquery设置，当没被调用的时候不显示     -->  
    <div id="addDlgBtn">  
        <a href="javascript:void(0)" id="addSaveBooktimecode" class="easyui-linkbutton" iconCls="icon-ok" onclick="add_ok()">确认</a>   
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addDlg').dialog('close')">取消</a>  
    </div> 
</body>
</html>
