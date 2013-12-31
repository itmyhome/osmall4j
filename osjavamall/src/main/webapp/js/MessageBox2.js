Ext.onReady(
	function helloword() {
		Ext.MessageBox.confirm("保存文件","是否保存文件",function(e) {
			if(e == "yes") {
				Ext.MessageBox.alert("成功","保存文件成功");
			} else if(e == "no") {
				Ext.MessageBox.alert("失败","不保存文件");
			}
		}
		);
	}
);