Ext.onReady(
	function() {
		Ext.MessageBox.prompt("姓名","请输入您的姓名",function(e,text) {
			if(e == "ok") {
				document.write(text);
			}
		}
		,null,200);
	}
);