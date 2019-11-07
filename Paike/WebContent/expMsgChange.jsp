<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%  
if( session.getAttribute("admin")==null|| session.getAttribute("admin")=="")
response.sendRedirect("login.jsp"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>教师信息修改</title>
<script type="text/javascript" src="jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#btn").click(function(){
		var a=$("#name").val();
		var b=$("#time").val();
		var c=$("#cnum").val();
		var d=$("#type").val();
		var e=$("#num").val();
		if(confirm("确定修改?"))
		$.post("Servlet_doexp",{flag:"2",name:a,time:b,cnum:c,type:d,num:e},function(data){alert("修改成功");window.location.href="expMsg.jsp";});
		
	});
	$("#del").click(function(){
		var a=$("#name").val();
		var b=$("#time").val();
		var c=$("#cnum").val();
		var d=$("#type").val();
		var e=$("#num").val();
		if(confirm("确定删除?"))
		$.post("Servlet_doexp",{flag:"3",name:a,time:b,cnum:c,type:d,num:e},function(data){alert("删除改成功");window.location.href="expMsg.jsp";});
		
	});
	
});
</script>

<style type="text/css">
*{
	padding:0;
	margin:0;
}
body{
	background-image:url(images/2.jpg);
	background-repeat:no-repeat;
	opacity:0.7;
}
#main{
	border:solid #666 2px;
	width:360px;
	height:450px;
	position:absolute;
	left:50%;
	margin-left:-180px;
	margin-top:100px;
}
.header{
	font-size:28px;
	line-height:50px;
	height:50px;
	background-color:#69F;
	text-align:center;
	margin-bottom:40px;
}
.body li{
	list-style-type:none;
	height:20px;
	padding-left:30px;
	margin-bottom:30px;
}
.body .title{
	float:left;
	font-size:20px;
	line-height:20px;
	width:110px;
}
.body .content select{
	width:150px;
	height:20px;
}
.body .change{
	text-align:center;
}
.body .change #btn,#ret{
	font-size:18px;
	width:80px;
	height:30px;
}
#del{font-size:18px;
	width:150px;
	height:30px;}
#num{
background-color:white;
}
</style>
</head>

<body>
<div id="main">
	<div class="header">修改教师信息</div>
    <div class="body">
    	<ul>
        	<li>
            	<div class="title">实验编号</div>
                <div class="content">
                	<input id="num" type="text" disabled="disabled" value="${sessionScope.exps[param.id].exp_item_no}" />
                </div>
            </li>
            <li>
            	<div class="title">实验学时</div>
                <div class="content">
               <input id="time" type="text" value="${sessionScope.exps[param.id].exp_item_hour}" />
                </div>
            </li>
            <li>
            	<div class="title">实验类型</div>
                <div class="content">
                	<input id="type" type="text" value="${sessionScope.exps[param.id].exp_type}" />
                </div>
            </li>
            <li>
            	<div class="title">实验名称</div>
                <div class="content">
                	<input id="name" type="text" value="${sessionScope.exps[param.id].exp_item_name}" />
                </div>
            </li>
            <li>
            	<div class="title">课程编号</div>
                <div class="content">
                	<input id="cnum" type="text" value="${sessionScope.exps[param.id].fk_course_no}" />
                </div>
            </li>
        </ul>
        <div class="change"><input id="btn" type="button" value="修改"/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="expMsg.jsp"><input id="ret" type="button" value="返回"/></a><br/><br/><input id="del" type="button" value="删除该实验信息"/></div>
    </div>
</div>
</body>
</html>