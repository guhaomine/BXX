<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.post("Servlet_doteacher",{flag:"1"},function(data){
	});	
	$.post("Servlet_doroom",{flag:"1"},function(data){
	});
	$.post("Servlet_doclass",{flag:"1"},function(data){
	});	
	$.post("Servlet_docourse",{flag:"1"},function(data){
	});	
	$.post("Servlet_doexp",{flag:"1"},function(data){
	});	
	$.post("Servlet_doinsert",{flag:"1"},function(data){
	});
	$(".btn").click(function(){
		$.post("Servlet_dologin",{flag:"1",username:$("#username").val(),password:$("#password").val()},function(data){
			if(data.flag==false){alert("用户名或密码错误");}
			else if(data.flag==true) {
					
				window.location.href="show.jsp?flag=true;";}
		});
		
		
	});
	
});
</script>
<style type="text/css">

.jiemian{
width:65%;
height:1000px;
margin-bottom:0;
float:left;

}
.jiemian > div{
font-size:120px;
color:white;
padding-left:200px;
margin-top:100px;
}
.log{float:left;

height:1000px;
width:35%;

text-align:center;
}
body{
background-image:url("images/1.jpg");
background-repeat:no-repeat;
background-size:100%;
}
	.five{
		float:left;
		margin-left:40px;
		display:block;
		width:50px;
	}
	ul{list-style-type:none;
	margin-top:60px;
	margin-bottom:20px;}
	li{margin-bottom:20px;}
	.btn{
	width:40%;
	background-color:white;
	border:1px black solid;
	margin-top:30px;
	margin-bottom:30px;
	height:30px;
	}
	.i{
	width:200px;
	height:20px;
	}
	span{
	font-size:60px;
	}
	.bder{
	border:1px black solid;
	background-color:white;
	margin-top:60%;
	height:250px;
	width:80%;
	min-width:450px;
	margin-right:10%;
	}
</style>
</head>
<body>
<div>
<div class="jiemian"><div><b>排课</b><br><span>系统登录</span></div></div>
<div class="log"><div class="bder">

<ul>
<li><div class="five"><b>用户名</b></div><input id="username" class="i" type="text" placeholder="请输入用户名" name="username"></li>
<li ><div class="five"><b>密码</b></div><input id="password" class="i" type="text" placeholder="请输入密码" name="password"></li>
</ul> 
<input class="btn" type="button" value="登录"></div>
</div></div>
</body>
</html>