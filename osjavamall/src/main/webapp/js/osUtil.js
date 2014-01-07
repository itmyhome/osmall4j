/*
**时间格式化方法，将毫秒渲染成yyyy-MM-dd HH-mm-ss格式的时间格式
**/
function dateFormat(d){
		var time;
		var year;var month;var day;
		var hour;var minute;var second;
		if(d != '' && d != null && d != undefined){
			time = new Date(d);
			year = time.getFullYear();
			month = time.getMonth()+1;
			day = time.getDate();
			hour = time.getHours();
		    minute = time.getMinutes();
			second = time.getSeconds();

			if( month != '' && month < 10 ){
				month = "0" + month;
			}
			if( day != '' && day < 10){
				day = "0" + day;
			}
			if( hour != '' && hour < 10){
				hour = "0" + hour;
			}
			if( minute != '' && minute < 10){
				minute = "0" + minute;
			}
			if( second < 10){
				second = "0" + second;
			}

			return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		}else{
			return '';
		}
		
}

function clearSelections(gridId){
	$('#'+gridId).datagrid('clearSelections');
}