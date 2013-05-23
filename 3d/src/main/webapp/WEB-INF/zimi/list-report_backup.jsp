<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/style.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/plugin/sexybuttons/sexybuttons.css"/>
<style type="text/css">
 body{
  background-image:none; 
 }
 .list{
   
 }
</style>
</head>
<body>
<div>
<div style="margin:0 auto;width:100%;">
 <div style="position:fixed;top:0px;background-color:#F5F5F5;left:2px;">
  <p style="margin:0px auto;margin-top:10px;width:98%;"><input type="text" style="line-height:20px;height:20px;vertical-align:middle;"/>&nbsp;
     <button type="reset" class="sexybutton"><span><span><span class="search">Search</span></span></span></button>
  </p>
  <table class="list" style="margin:0px auto;margin-top:6px;">
    <tr>
       <th width="25%" orderBy="">期数</th>
       <th width="25%" orderBy="">试机号</th>
       <th width="25%" oderBy="">开奖号</th>
       <th orderBy="">笔画数字</th>
    </tr>
   </table>
   </div>
  </div>
  <table class="list" style="margin-top:65px;">
  <c:forEach var="zm" items="${list}">
     <tr>
       <td width="25%">${zm.serialNumber}</td>
       <td width="25%">${zm.shiJiHao}</td>
       <td width="25%">${zm.kaiJiangHao}</td>
       <td>${zm.npis}</td>
     </tr>
     </c:forEach>
  </table>
</div>
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
   $(function(){
	   $("#orderNpis").click(function(){
		   if("true" == "${order}")
			   location.href = "<%=basePath%>/zimi?action=report";
		   else
			   location.href = "<%=basePath%>/zimi?action=report&orderBy=Npis";
	   });
   });
</script>
</body>
</html>