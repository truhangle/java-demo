<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/style.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/plugin/sexybuttons/sexybuttons.css"/>
<script type="text/javascript" src="<%=basePath%>/resources/plugin/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery-1.7.2.min.js"></script>
</head>
<body>
<div>
<form action="<%=basePath%>/zimi" method="post">
<input type="hidden" name="action" value="save"/>
  <table class="formTable" style="height:250px;">
    <tr>
       <td>日期:</td>
       <td><input class="Wdate" name="riqi" type="text" onClick="WdatePicker()"/></td>
    </tr>
    <tr>
       <td>第几期:</td>
       <td><input type="text" name="dijiqi"/></td>
    </tr>
    <tr>
       <td>试机号号:</td>
       <td><input type="text" name="shijihao"/></td>
    </tr>
    <tr>
       <td>开奖号:</td>
       <td><input type="text" name="kaijianghao"/></td>
    </tr>
    <tr>
       <td>下注号:</td>
       <td><input type="text" name="xiaZhuHao"/></td>
    </tr>
    <tr>
       <td>太湖钓叟三字诀:</td>
       <td><input type="text" name="content" id="content" style="width:500px;"/>
           <button type="reset" class="sexybutton" id="clearBtn"><span><span><span class="decline">Decline</span></span></span></button>
       </td>
    </tr>
    <tr>
      <td colspan="2">
        <button type="submit" class="sexybutton"><span><span><span class="ok">Submit</span></span></span></button>
        &nbsp;
        <button type="reset" id="backBtn" class="sexybutton"><span><span><span class="undo" >Back</span></span></span></button>
      </td>
    </tr>
  </table>
  </form>
</div>
<script type="text/javascript">
  $(function(){
	  
	  $("#backBtn").click(function(){
		  history.back();
	  });
	  
	  $("#clearBtn").click(function(){
		  $("#content").val("").focus();
	  });
  });
</script>
</body>
</html>