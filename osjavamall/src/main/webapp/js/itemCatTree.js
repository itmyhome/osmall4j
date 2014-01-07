$(function () {

   $('#btn-save,#btn-cancel').linkbutton();
    win = $('#eidt-window').window({
        closed: true,
        modal: true,
        shadow: false
    });
    form = win.find('form');

    $('#btn-search,#btn-search-cancel').linkbutton();
    searchWin = $('#search-window').window({
        closed: true,
        modal: true
    });
    searchForm = searchWin.find('form');

   tree = $('#tree').tree({
        checkbox: false,
        url: 'itemCat/getRootItem',
        onBeforeExpand: function (node, param) {
        	//$('#tree').tree('options').url = "itemCat/getRootItem"; // change the url                       
        },
        onClick: function (node) {
        	//alert(node.id);
        	$('#tree').tree('options').url = "itemCat/getRootItem";
            clickTree(node.id);
        }
    });
    
   /*$.ajax({
	   type:'get',
	   url:'itemCat/getRootItem',
	   success:function(result){
		   $('#tree').tree({
	             data: result,
	             onBeforeExpand: function (node, param) {
	             	$('#tree').tree('options').url = "itemCat/getRootItem"; // change the url                       
	             },
	       });
	   }
   })*/

    grid = $('#grid').datagrid({
        title: '产品类别管理',
        iconCls: 'icon-save',
        url: '',
        sortName: 'ID',
        sortOrder: 'desc',
        idField: 'cid',
        pageSize:5,
        frozenColumns: [[
                    { field: 'ck', checkbox: true }
                    ,{ title: 'cid', field: 'cid', width: 80, hidden:true }
                ]],
        columns: [[
                    { field: 'name', title: '名称', width: 150 },
                    { field: 'modifiedTime', title: '添加日期', width: 150, sortable: true ,formatter:function(value,rowObj){
                    	return dateFormat(value);
					}},
					{field:'isParent',title:'是否为父类目',width:220,formatter:function(value,rowObj){
						if(rowObj.parent==true){
							return "是";
						}else{
							return "否";
						}
					  },
					},
					{ field: 'sortOrder', title: '排序', width: 150 },
                ]],
        pagination: true,
        rownumbers: true,
        singleSelect: false,
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function(){
            	if($('#tree').tree('getSelected')==null){
            		$.messager.alert("提示", "请选择一个产品类别", "info");
            		return;
            	}
            	$("#parentCid").val($('#tree').tree('getSelected').id);
            	$('#addDlg').dialog('open').dialog('setTitle', '添加产品类别信息');//打开对话框
            }
        }, '-', {
            text: '修改',
            iconCls: 'icon-edit',
            handler:function(){
				updaterow();
			}
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: del
        }, '-', {
            text: '查找',
            iconCls: 'icon-search',
            handler: OpensearchWin
        }, '-', {
            text: '所有',
            iconCls: 'icon-search',
            handler: showAll
        }]
    });
    $('body').layout();
});

var tree;
var grid;
var win;
var form;
var searchWin;
var searchForm;

function clickTree(nodeid) {
    grid.datagrid({ url: 'itemCat/queryList?cid='+nodeid+"&parentCid="+nodeid});
    grid.datagrid('clearSelections');
}

function getSelectedArr() {
    var ids = [];
    var rows = $('#grid').datagrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
        ids.push(rows[i].cid);
    }
    return ids;
}
function getSelectedID() {
    var ids = getSelectedArr();
    return ids.join(',');
}
function arr2str(arr) {
    return arr.join(',');
}

//更新
function updaterow() {
	var row = $('#grid').datagrid('getSelected');
	if (row) {
		$('#addDlg').dialog('open');
		$('#itemForm').form('load', row);
	}else{
		$.messager.alert("提示", "请选择一条数据", "info");
	}
}
function del() {
    var arr = getSelectedArr();
    if (arr.length>0) {
        $.messager.confirm('提示信息', '您确认要删除吗?', function (data) {
            if (data) {
                $.ajax({
                    url: 'itemCat/deleteItemCat?ids=' + arr2str(arr),
                    type: 'GET',
                    timeout: 1000,
                    error: function () {
                        $.messager.alert('错误', "删除失败", 'error');
                    },
                    success: function (data) {
                       if (data.success) {
                    	   $.messager.alert('提示', data.success, 'info');
                        	$('#tree').tree('reload'); // reload页面grid
                            grid.datagrid('reload');
                            grid.datagrid('clearSelections');
                        } else {
                            $.messager.alert('错误', data.fail, 'error');
                        }
                    }
                });
            }
        });
    } else {
        $.messager.show({
            title: '警告',
            msg: '请先选择要删除的记录。'
        });
    }
}

function showAll() {
    grid.datagrid({ url: 'itemCat/queryList' });
}
function OpensearchWin() {
    searchWin.window('open');
    searchForm.form('clear');
}

function closeWindow() {
    win.window('close');
}

function SearchOK() {
    var item_name = $("#item_name").val();
    searchWin.window('close');
    grid.datagrid({ url: 'itemCat/queryList', queryParams: {name: item_name} });
}
function closeSearchWindow() {
    searchWin.window('close');
}

var url='itemCat/operateItemCat';
//新增
function add_ok() {
	$('#itemForm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(operInfo) {
			$('#itemForm').form('clear');
			clearSelections("grid");
			$('#tree').tree('reload'); // reload页面grid
			$('#grid').datagrid('reload');
			var result = eval('(' + operInfo + ')');
			if (result.message) {
				$.messager.alert("提示", result.message, "info");
				$('#addDlg').dialog('close'); //关闭对话框
			} else {
				$.messager.alert("提示", result.error, "error");
			}
		}
	});
}
//更新
function updaterow() {
	var row = $('#grid').datagrid('getSelected');
	if (row) {
		$('#addDlg').dialog('open');
		$('#itemForm').form('load', row);
	}else{
		$.messager.alert("提示", "请选择一条数据", "info");
	}
}