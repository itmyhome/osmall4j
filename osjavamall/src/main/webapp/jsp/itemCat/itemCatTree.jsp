<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
	<base href="<%=basePath%>">
    <title></title>
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui/demo.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/itemCatTree.js"></script>
	<script type="text/javascript" src="js/osUtil.js"></script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
    <div region="west" split="true" title="产品结构树" style="width: 180px;" id="west">
        <ul id="tree">
        </ul>
    </div>
    <div region="center" style="width: 500px; height: 300px; padding: 1px; background: #eee;
        overflow-y: hidden">
        <div id="grid" fit="true">
        </div>
    </div>
    <!-- 添加商品类型信息的表单       -->  
    <div id="addDlg" class="easyui-dialog" style="width: 580px; height: 350px; padding: 10px 20px" closed="true" buttons="#addDlgBtn">  
        <form id="itemForm" method="post">  
            <table>
				<input name="cid" class="easyui-validatebox" hidden=true>
				<input id="parentCid" name="parentCid" class="easyui-validatebox" hidden=true>
                <tr>  
                    <td>类别名称</td>  
                    <td>  
                      <input name="name" class="easyui-validatebox" required="true" missingMessage="类别名称不能为空">  
                    </td>  
                </tr>  
                <!-- <tr>  
                    <td>是否为父级类目</td>  
                    <td>  
                        使用Easyui中的combobox   
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
                </tr>   -->
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
    <div id="search-window" title="查询窗口" style="width: 350px; height: 200px;">
        <div style="padding: 20px 20px 40px 80px;">
            <form method="post">
            <table>
                <tr>
                    <td> 名称：</td>
                    <td>
                        <input name="item_name" id="item_name" style="width: 150px;" />
                    </td>
                </tr>
            </table>
            </form>
        </div>
        <div style="text-align: center; padding: 5px;">
            <a href="javascript:void(0)" onclick="SearchOK()" id="btn-search" icon="icon-ok">确定</a>
            <a href="javascript:void(0)" onclick="closeSearchWindow()" id="btn-search-cancel" icon="icon-cancel">  取消</a>
        </div>
    </div>
</body>