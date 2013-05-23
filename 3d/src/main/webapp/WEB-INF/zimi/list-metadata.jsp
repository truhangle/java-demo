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
	background-color: #EBEBEB;
  }
</style>
</head>
<body>
 <div>
    <div style="position:fixed;top:0px;background-color:#F5F5F5;left:2px;width:100%;border-bottom:#ccc groove 1px;">
       <p style="margin:10px auto;width:98%;">
       <!-- <label>Start:</label><input class="Wdate" type="text" onClick="WdatePicker()"/> ~ 
       <label>End:</label><input class="Wdate" type="text" onClick="WdatePicker()"/> -->
       <input type="text" style="line-height:20px;height:20px;vertical-align:middle;"/>&nbsp;
     <button type="reset" class="sexybutton"><span><span><span class="search">Search</span></span></span></button>
     &nbsp;
     <button type="reset" class="sexybutton" id="addBtn"><span><span><span class="add">Add</span></span></span></button>
     &nbsp;
     <button type="reset" class="sexybutton"><span><span><span class="delete">Delete</span></span></span></button>
     &nbsp;
     <button type="reset" class="sexybutton" id="wand"><span><span><span class="wand" id="wandSpan">Hide lucky No</span></span></span></button>
  </p>
    </div>
    <div style="margin-top:45px;">
    <c:forEach var="zm" items="${list}">
    <table class="zimi">
      <tr style="background-color:#CAE5E8;">
        <td colspan="4">第 ${zm.serialNumber} 期</td>
      </tr>
      <tr>
        <td>${zm.z1}(<span class="bihua">${zm.b1}</span>)</td>
        <td>${zm.z2}(<span class="bihua">${zm.b2}</span>)</td>
        <td>${zm.z3}(<span class="bihua">${zm.b3}</span>)</td>
        <td style="background-color:#A0A0A0;color:#fff;">&nbsp;${zm.b1+zm.b2+zm.b3}</td>
      </tr>
      <tr>
        <td>${zm.z4}(<span class="bihua">${zm.b4}</span>)</td>
        <td>${zm.z5}(<span class="bihua">${zm.b5}</span>)</td>
        <td>${zm.z6}(<span class="bihua">${zm.b6}</span>)</td>
        <td style="background-color:#A0A0A0;color:#fff;">&nbsp;${zm.b4+zm.b5+zm.b6}</td>
      </tr>
      <tr>
        <td>${zm.z7}(<span class="bihua">${zm.b7}</span>)</td>
        <td>${zm.z8}(<span class="bihua">${zm.b8}</span>)</td>
        <td>${zm.z9}(<span class="bihua">${zm.b9}</span>)</td>
        <td style="background-color:#A0A0A0;color:#fff;">&nbsp;${zm.b7+zm.b8+zm.b9}</td>
      </tr>
      <tr>
        <td>${zm.z10}(<span class="bihua">${zm.b10}</span>)</td>
        <td>${zm.z11}(<span class="bihua">${zm.b11}</span>)</td>
        <td>${zm.z12}(<span class="bihua">${zm.b12}</span>)</td>
        <td style="background-color:#A0A0A0;color:#fff;">&nbsp;${zm.b10+zm.b11+zm.b12}</td>
      </tr>
      <tr style="background-color:#00A6AD;color:#fff;">
        <td>${zm.b1+zm.b4+zm.b7+zm.b10}</td>
        <td>${zm.b2+zm.b5+zm.b8+zm.b11}</td>
        <td>${zm.b3+zm.b6+zm.b9+zm.b12}</td>
        <td style="background-color:#DF0029;color:#fff;">${zm.bTotal}</td>
      </tr>
        <tr>
          <td colspan="4" style="background-color:#000;color:#fff;">数字:${zm.npis}</td>
        </tr>
        <tr>
          <td colspan="4">试机号:${zm.shiJiHao} 开奖号:<span class="kjh" meta="${zm.kaiJiangHao}">${zm.kaiJiangHao}</span></td>
        </tr>
    </table>
    </c:forEach>
    </div>
 </div>
 <script type="text/javascript" src="<%=basePath%>/resources/plugin/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
  $(function(){
	  $("#addBtn").click(function(){
		  parent.location.href = "<%=basePath%>/zimi?action=add";
	  });
	  $("#wand").toggle(function(){
		  $(".kjh").text("***");
		  $("#wandSpan").text("Show lucky No");
	  },function(){
		  $(".kjh").each(function(){
			  var meta = $(this).attr("meta");
			  $(this).text(meta);
		  });
		  $("#wandSpan").text("Hide lucky No");
	  });
	  
	  $(".zimi").click(function(){
		  $(this).addClass("hotzimi").siblings().removeClass("hotzimi");
	  });
  });
</script>
</body>
</html>