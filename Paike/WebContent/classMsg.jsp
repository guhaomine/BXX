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
		var b=$("#name").val();
		var c=$("#cname").val();
		if(b==""||b==null||c==null||c=="")
			alert("班级号,专业名称和班级名均不能为空");
		else
		if(confirm("确定添加?"))
		$.post("Servlet_doclass",{flag:"4",name:b,cname:c},function(data){alert("添加成功");window.location.href="classMsg.jsp";});
		
	});
});
</script>
<link rel="stylesheet" href="show.css" type="text/css" />
<title>信息管理界面</title>
</head>

<body>
	<div id="main">
    	<div class="header">
        	<div class="tit1">班级管理</div>
            <div class="tit2">
                <div id="adminImg"><img src="images/admin.png" width="32" height="32" /></div>
           	  	<div id="admin"><a href="userinfo.jsp">${sessionScope.admin.ad_name}</a></div>
            </div>
        </div>
        <div class="neck">
        	<a href="show.jsp"><div class="blank">返回</div></a>
            <div class="menu">
            	<ul>
            		<li id="show">添加班级信息</li>
                	
                	
            	</ul>
            </div>
        </div>
        
      <div class="right">
      <table border="1" cellspacing="0"><tr><th>班级号</th><th>专业名称</th><th>班级名</th><th>操作</th></tr>
    <c:forEach var="i" begin="0" end="${sessionScope.clas_len}">
       <tr><td>${sessionScope.clas[i].class_no}</td>
       <td>${sessionScope.clas[i].majior_name}</td>
       <td>${sessionScope.clas[i].class_name}</td>
       <td><a href="classMsgChange.jsp?id=${i}">修改</a></td></tr>  
     </c:forEach>
      </table>
      </div>
         <div  id="hid" style="background-color:white;position:fixed;top:160px;right:-1000px;"><table cellspacing="0" border="1" height="200px" width="400px">
      <tr><td width="60px">专业名称</td><td><input id="name" class="q" type="text"></td></tr>
      
      <tr><td>班级名</td><td><input id="cname" class="q" type="text"></td></tr>
      <tr ><td colspan="2"> <input id="add" type="button" style="padding:10px 70px 10px 70px;background-color:white;" value="添加新班级" /><input id="quxiao" style="padding:10px; margin-left:20px;background-color:white;" type="button" value="取消"></td ></tr>
      </table></div>
	</div>
</body>
</html>
