Ext.onReady(
	function helloword() {
		Ext.MessageBox.alert("欢迎","很高兴能够和大家一起来学习ExtJS!",function(e) {
			if(e == "ok") {
				document.write("你单击了OK按钮");
			} else if(e == "cancel") {
				document.write("你单击了cancel按钮");
			}
		});
	}
);