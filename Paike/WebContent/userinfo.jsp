<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%  
if( session.getAttribute("admin")==null|| session.getAttribute("admin")=="")
response.sendRedirect("login.jsp"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>教师信息录入</title>
<script type="text/javascript" src="jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#fh").click(function(){window.location.href="show.jsp";});
	
});
</script>

<style type="text/css">
*{
	padding:0;
	margin:0;
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
.body .change .btn{
	font-size:18px;
	width:80px;
	height:30px;
	margin-left:10px;
	margin-right:10px;
}
</style>
</head>

<body>
<div id="main">
	<div class="header">用户信息</div>
    <div class="body">
    	<ul>
        	<li>
            	<div class="title">用户编号</div>
                <div class="content">
                	${sessionScope.admin.ad_no}
                </div>
            </li>
            <li>
            	<div class="title">姓名</div>
                <div class="content">
                	${sessionScope.admin.ad_name}
                </div>
            </li>
            <li>
            	<div class="title">用户名</div>
                <div class="content">
                	${sessionScope.admin.ad_user_name}
                </div>
            </li>
            <li>
            	<div class="title">联系电话</div>
                <div class="content">
                	${sessionScope.admin.ad_tell}
                </div>
            </li>
            <li>
            	<div class="title">E-mall</div>
                <div class="content">
                	${sessionScope.admin.email}
                </div>
            </li>
        </ul>
        <div class="change"><input class="btn" id="fh" type="button" value="返回"/></div>
    </div>
</div>
</body>
</html>