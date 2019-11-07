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
	$(".del").click(function(){
		var a=$(this).attr("id");
		if(confirm("确定删除?"))
		$.post("Servlet_doinsert",{flag:"3",apno:a},function(data){alert("删除成功");window.location.href="show.jsp";});
		
	});
	
	
});
</script>
<link rel="stylesheet" href="kebiao.css" type="text/css" />
<title>信息管理界面</title>
</head>

<body>
	<div id="main">
    	<div class="header">
        	<div class="tit1">课表管理</div>
            <div class="tit2">
                <div id="adminImg"><img src="images/admin.png" width="32" height="32" /></div>
           	  	<div id="admin"><a href="userinfo.jsp">${sessionScope.admin.ad_name}</a></div>
            </div>
        </div>
        <div class="neck">
        	<a href="show.jsp"><div class="blank">返回</div></a>
            <div class="menu">
            
            </div>
        </div>
        
      <div class="right">
      <table border="1" cellspacing="0"><tr><th>课序号</th><th>课程号</th><th>实验室编号</th><th>实验编号</th><th>班级编号</th><th>教师编号</th><th>学期</th><th>周</th><th>操作</th></tr>
    <c:forEach var="i" begin="0" end="${sessionScope.apps_len}">
       <tr><td>${sessionScope.apps[i].app_no}</td>
       <td>${sessionScope.apps[i].fk_course_no}</td>
       <td>${sessionScope.apps[i].course_idx}</td>
       <td>${sessionScope.apps[i].app_stu_num}</td>
       <td>${sessionScope.apps[i].fk_class_no}</td>
       <td>${sessionScope.apps[i].fk_teacher_no}</td>
       <td>${sessionScope.apps[i].app_term}</td>
        <td>第${sessionScope.apps[i].week}周</td>
       <td style="cursor:pointer;" class="del" id="${sessionScope.apps[i].app_no}">删除</td></tr>  
     </c:forEach>
      </table>
      </div>
      <div  id="hid" style="background-color:white;position:fixed;top:160px;right:-1000px;"><table cellspacing="0" border="1" height="300px" width="400px">
      <tr><td width="60px">课序号</td><td><input id="time" class="q" type="text"></td></tr>
      
      <tr><td>课程号</td><td><input id="type" class="q" type="text"></td></tr>
      <tr><td>实验室编号</td><td><input id="name" class="q" type="text"></td></tr>
      <tr><td>班级编号</td><td><input id="cnum" class="q" type="text"></td></tr>
      <tr><td>教师编号</td><td><input id="cnum" class="q" type="text"></td></tr>
      <tr><td>学期</td><td><input id="cnum" class="q" type="text"></td></tr>
      <tr ><td colspan="2"> <input id="add" type="button" style="padding:10px 70px 10px 70px;background-color:white;" value="添加新项目" /><input id="quxiao" style="padding:10px; margin-left:20px;background-color:white;" type="button" value="取消"></td ></tr>
      </table></div>
	</div>
</body>
</html>