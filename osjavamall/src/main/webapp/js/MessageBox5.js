Ext.onReady(
	function() {
		Ext.MessageBox.show({
			title:"进度条",
			msg:"5秒后自动进入系统",
			progress:true,
			width:400,
			wait:true,
			waitConfig:{
				interval:540,
				duration:5000,
				fn:function() {
					Ext.MessageBox.hide();
				}
			}
		});
	}
);