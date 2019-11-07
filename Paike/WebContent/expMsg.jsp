<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%  
if( session.getAttribute("admin")==null|| session.getAttribute("admin")=="")
response.sendRedirect("login.jsp"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#show").click(function(){
		$("#hid").animate({right:'40%'});		
	});
	$("#quxiao").click(function(){
		$("#hid").animate({right:'-1000px'});
		
	});
	$("#add").click(function(){
		var a=$("#name").val();
		var b=$("#time").val();
		var c=$("#cnum").val();
		var d=$("#type").val();
		
		if(a==""||b==""||a==null||b==null||c==null||c==""||d==null||d=="")
			alert("实验名称,实验学时,实验类型和实验编号均不能为空");
		else
		if(confirm("确定添加?"))
		$.post("Servlet_doexp",{flag:"4",name:a,time:b,cnum:c,type:d},function(data){alert("添加成功");window.location.href="expMsg.jsp";});
		
	});
});
</script>
<link rel="stylesheet" href="show.css" type="text/css" />
<title>信息管理界面</title>
</head>

<body>
	<div id="main">
    	<div class="header">
        	<div class="tit1">实验管理</div>
            <div class="tit2">
                <div id="adminImg"><img src="images/admin.png" width="32" height="32" /></div>
           	  	<div id="admin"><a href="userinfo.jsp">${sessionScope.admin.ad_name}</a></div>
            </div>
        </div>
        <div class="neck">
        	<a href="show.jsp"><div class="blank">返回</div></a>
            <div class="menu">
            	<ul>
            		<li id="show">添加实验信息</li>
                	
                	
            	</ul>
            </div>
        </div>
        
      <div class="right">
      <table border="1" cellspacing="0"><tr><th>实验编号</th><th>实验学时</th><th>实验类型</th><th>实验名称</th><th>课程编号</th><th>操作</th></tr>
    <c:forEach var="i" begin="0" end="${sessionScope.exps_len}">
       <tr><td>${sessionScope.exps[i].exp_item_no}</td>
       <td>${sessionScope.exps[i].exp_item_hour}</td>
       <td>${sessionScope.exps[i].exp_type}</td>
       <td>${sessionScope.exps[i].exp_item_name}</td>
       <td>${sessionScope.exps[i].fk_course_no}</td>
       <td><a href="expMsgChange.jsp?id=${i}">修改</a></td></tr>  
     </c:forEach>
      </table>
      </div>
      <div  id="hid" style="background-color:white;position:fixed;top:160px;right:-1000px;"><table cellspacing="0" border="1" height="300px" width="400px">
      <tr><td width="60px">实验学时</td><td><input id="time" class="q" type="text"></td></tr>
      
      <tr><td>实验类型</td><td><input id="type" class="q" type="text"></td></tr>
      <tr><td>实验名称</td><td><input id="name" class="q" type="text"></td></tr>
      <tr><td>课程编号</td><td><input id="cnum" class="q" type="text"></td></tr>
      <tr ><td colspan="2"> <input id="add" type="button" style="padding:10px 70px 10px 70px;background-color:white;" value="添加新项目" /><input id="quxiao" style="padding:10px; margin-left:20px;background-color:white;" type="button" value="取消"></td ></tr>
      </table></div>
	</div>
</body>
</html>
