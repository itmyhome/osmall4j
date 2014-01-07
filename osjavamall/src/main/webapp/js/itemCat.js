
var url='itemCat/operateItemCat';
//新增
function add_ok() {
	
	$('#itemForm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(operInfo) {
			clearSelections();
			var result = eval('(' + operInfo + ')');
			if (result.message) {
				$.messager.alert("提示", result.message, "info");
				$('#addDlg').dialog('close'); //关闭对话框
				$('#itemCatList').datagrid('reload'); // reload页面grid
			} else {
				$.messager.alert("提示", result.error, "error");
			}
		}
	});
}
// 更新
function updaterow() {
	var row = $('#itemCatList').datagrid('getSelected');
	if (row) {
		$('#addDlg').dialog('open');
		$('#itemForm').form('load', row);
	}else{
		$.messager.alert("提示", "请选择一条数据", "info");
	}
}

// 删除
function deleterow() {
	$.messager.confirm('提示', '确定要删除吗?', function(result) {
		if (result) {
			var rows = $('#userTable').datagrid('getSelections');
			var ps = "";
			$.each(rows, function(i, n) {
				if (i == 0)
					ps += "?uid=" + n.uid;
				else
					ps += "&uid=" + n.uid;
			});
			$.post('user/delete' + ps, function(data) {
				$('#userTable').datagrid('reload');
				$.messager.alert('提示', data.mes, 'info');
			});
		}
	});
}