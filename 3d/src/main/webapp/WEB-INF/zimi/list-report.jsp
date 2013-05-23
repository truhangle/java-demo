<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title></title>
<style type="text/css" title="currentStyle">
	@import "<%=basePath%>/resources/plugin/DataTables/css/demo_page.css";
	@import "<%=basePath%>/resources/plugin/DataTables/css/demo_table.css";
	div.table_Wrapper {
		border: 10px solid blue;
	}
	.highlighted{
	  background-color:#FDE275 !important;
	}
</style>
<script type="text/javascript" language="javascript" src="<%=basePath%>/resources/plugin/DataTables/js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath%>/resources/plugin/DataTables/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#example').dataTable({
			"sScrollY" : "490px",
			"bPaginate" : false,
			"bScrollCollapse" : true
		});
		$("#example tbody tr").live("click",function(){
			$(this).addClass("highlighted").siblings().removeClass("highlighted");
		});
	});
</script>
</head>
<body id="dt_example">
	<div id="container">
		<div id="demo">
			<table cellpadding="0" cellspacing="0" border="0" class="display"
				id="example" style="width: 100%;">
				<thead>
					<tr>
						<th>期数</th>
						<th>试机号</th>
						<th>开机号</th>
						<th>笔画数字</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>期数</th>
						<th>试机号</th>
						<th>开机号</th>
						<th>笔画数字</th>
					</tr>
				</tfoot>
				<tbody>
				   <c:forEach var="zm" items="${list}" varStatus="statu">
				    <c:choose>
				      <c:when test="${statu.index%2==0}">
				      	<tr class="odd gradeC">
				      </c:when>
				      <c:when test="${statu.index%3==0}">
				      	<tr class="gradeX odd">
				      </c:when>
				      <c:when test="${statu.index%5==0}">
				      	<tr class="gradeA odd">
				      </c:when>
				      <c:when test="${statu.index%7==0}">
				      	<tr class="gradeU odd">
				      </c:when>
				      <c:otherwise>
				      	<tr class="even gradeC">
				      </c:otherwise>
				    </c:choose>
				       <td>${zm.serialNumber}</td>
				       <td>${zm.shiJiHao}</td>
				       <td class="center">${zm.kaiJiangHao}</td>
				       <td>${zm.npis}</td>
				     </tr>
				     </c:forEach>
				</tbody>
			</table>
		</div>
		<div class="spacer"></div>
	</div>
</body>
</html>