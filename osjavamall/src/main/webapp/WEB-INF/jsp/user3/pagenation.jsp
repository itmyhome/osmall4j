<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="pageInfo" class="com.os.pageutil.PageInfoGrid" scope="request"></jsp:useBean>
<script type="text/javascript">
//跳转
function Jumping(){
	var pageSize =  document.PageForm.selectPageSize.value;
	document.PageForm.pageNo.value = document.PageForm.jumpPage.value;
	document.PageForm.action =  document.PageForm.action + '/'+ document.PageForm.jumpPage.value + '/' + pageSize;
    document.PageForm.submit();
    return ;
}
//上一页 下一页
function gotoPage(pagenum){
	if(pagenum<1){
		alert("已经是第一页了！");
		return;
	}
	if(pagenum><%=pageInfo.getPage().getTotalPageNum()%>){
		alert("已经是最后一页了！");
		return;
	}
	var pageSize =  document.PageForm.selectPageSize.value;
   document.PageForm.action =  document.PageForm.action + '/'+ pagenum + '/' + pageSize;
   document.PageForm.submit();
   return ;
}
//下拉框切换
function changePageSize(){
	var pageSize =  document.PageForm.selectPageSize.value;
	document.PageForm.pageSize.value = pageSize;
	document.PageForm.pageNo.value = document.PageForm.jumpPage.value;
	document.PageForm.action =  document.PageForm.action + '/'+ document.PageForm.jumpPage.value + '/' + pageSize;
	document.PageForm.submit();
}
//查询框提交
function query(){
	var pageSize =  document.PageForm.selectPageSize.value;
	document.queryForm.action =  document.PageForm.action + '/'+ document.PageForm.jumpPage.value + '/' + pageSize;
	document.queryForm.submit();
}
$(function(){
	var pageNo = <%=pageInfo.getPage().getPageNum() %>;
	document.PageForm.jumpPage.value = pageNo;
	var pageSize = <%=pageInfo.getPage().getPageSize() %>;
	document.PageForm.selectPageSize.value = pageSize;
})
	
</script>
<form name="PageForm"  action="${pageContext.request.contextPath}/user3/list" method="post">
<input type="hidden" name="userName" value="${pageInfo.search.userName}"/>
<input type="hidden" name="pageNo"/>
<input type="hidden" name="pageSize" value="5"/>
每页<%=pageInfo.getPage().getPageSize()%>条/
共<%=pageInfo.getPage().getTotalRowNum()%>条/
第<%=pageInfo.getPage().getPageNum() %>页/
共<%=pageInfo.getPage().getTotalPageNum()%>页
<br/>
 
<a href="javascript:gotoPage(1)">首页</a>
<a href="javascript:gotoPage(<%=pageInfo.getPage().getPageNum()-1%>)">上一页</a>
<a href="javascript:gotoPage(<%=pageInfo.getPage().getPageNum()+1%>)">下一页</a>
<a href="javascript:gotoPage(<%=pageInfo.getPage().getTotalPageNum()%>)">尾页</a>
转到第<input type="text" name="jumpPage"><input type="button" onclick="Jumping()" value="跳转">页

	 每页<SELECT id="selectPageSize" name="selectPageSize" onchange="changePageSize()">
	 <OPTION value="5">5</OPTION>
     <OPTION value="15">15</OPTION>
	 <OPTION value="45">45</OPTION>
	 <OPTION value="90">90</OPTION>
	 <OPTION value="200">200</OPTION>
     </SELECT>条
</form>

