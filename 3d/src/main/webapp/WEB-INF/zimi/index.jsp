<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/style.css"/>
</head>
<body>
  <div>
     <div id="leftContent">
       <iframe style="border:none;" src="<%=basePath%>/zimi?action=metadata" height="632px" width="100%"></iframe>
     </div>
     <div id="rightContent">
       <iframe style="border:none;" src="<%=basePath%>/zimi?action=report" height="632px" width="100%"></iframe>
     </div>
  </div>
</body>
</html>