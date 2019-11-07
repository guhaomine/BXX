<%@page import="java.util.jar.Attributes.Name"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="conn.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>登录页面</title>
	<style type="text/css">
	#mine{
		border-radius: 50px;
		
	}
	#gh{
	height:60px;
	width:400px;
		border:2px #00BFFF solid;
		
		background-color: #87CEFA; 
	}
		h3{ text-indent:-13em;}
	body{
		background-color: #E6E6FA;
		
		background-size: cover;
		
	}

	</style>
<script type="text/javascript">
		function check_user() {
			var c=document.getElementById("r").value;
			var d=document.getElementById("e").value;
			if(c==""){alert("用户名不能为空！");}
			else if(c.length<2||c.length>20){alert("用户名应在2到20字之间！");}
			else if(d==""){alert("密码不能为空！");}
			else if(d.length<5||d.length>20){alert("密码应在5到20字之间！");}
			
		}
</script>
</head>
<body >
	<br><br><br><br><br><br>
	<h3 align="center">用户登录:</h3>
	<center>
		<form method="post" action="login.jsp">
<table   id="gh"> 
	<tr height="30"></tr>
	<tr ><td width="20"></td><td rowspan="2" width="50" >
	<img id="mine"  src="img/dog.jpg" height="60"></td>
	<td align="right">用户名:</td><td><input name="username" id="r" type="text" ></td><td width="20">
	</td></tr>
	<tr><td></td><td align="right"> 密码:</td><td><input id="e" name="password" size="21" type="password" ></td><td></td></tr>
	<tr><td><input type="hidden" name="hid" value="1"></td></tr>
	<tr><td></td><td colspan="3" align="center"><input  type="submit" onclick="check_user()" value="登录"></td><td></td></tr>
	<tr height="20"></tr>
</table>
</form>
</center>

</body>

</html>
	<%
	
	request.setCharacterEncoding("UTF-8");
String na=request.getParameter("username");
String pa=request.getParameter("password");
String hid=request.getParameter("hid");

String c="用户名不能为空 并且 应该多于2字少于20字<br>密码不能为空并且应该多于5字少于20字";
int flag=0;
if(na==""||na==null){
	flag=1;
}
else if(na.length()<2||na.length()>20)
{
	flag=1;}
if(pa==""||pa==null){
	flag=1;
}
else if(pa.length()<5||pa.length()>20)
{
	flag=1;}
if(flag==0&&hid!=null){
	ResultSet rs=sql.executeQuery("select * FROM user where username='"+na+"'");
	if(!rs.next())
	{%>
	<script type="text/javascript">
		alert("该用户不存在!");
		/* window.location.href='list.jsp'; */
		</script>
		<%-- <jsp:forward page="errorjsp.jsp">
		<jsp:param value="该用户不存在！" name="tx"/>
		<jsp:param value="该用户不存在！" name="xz"/>
		<jsp:param value="该用户不存在！" name="bt"/>
		<jsp:param value="该用户不存在！" name="cv"/>
		<jsp:param value="该用户不存在！" name="nr"/>
		<jsp:param value="1" name="flag"/>
		</jsp:forward> --%>
		
		<%}
	else{
		if(!(rs.getString("password")).equals(pa))
		{%>
		<script type="text/javascript">
		alert("密码错误!");
		/* window.location.href='list.jsp'; */
		</script>
			<%-- <jsp:forward page="errorjsp.jsp">
			<jsp:param value="密码错误！" name="tx"/>
			<jsp:param value="密码错误！" name="xz"/>
			<jsp:param value="密码错误！" name="cv"/>
			<jsp:param value="密码错误！" name="nr"/>
			<jsp:param value="密码错误！" name="bt"/>
			<jsp:param value="1" name="flag"/>
			</jsp:forward> --%>
			
		<%}
		else
		{

			session.setAttribute("username",na);
			session.setAttribute("password",pa);
			con.close();
			response.sendRedirect("list.jsp");	
		}
	}
	}
else if(flag==1&&hid!=null) 
	{
	%><jsp:forward page="errorjsp.jsp">
	<jsp:param value="1" name="flag"/>
	<jsp:param value="你的用户名：" name="xz"/>
	<jsp:param value="你的密码：" name="cv"/>
	<jsp:param value="<%=na %>" name="bt"/>
	<jsp:param value="<%=pa%>" name="nr" />
	<jsp:param value="<%=c %>" name="tx"/>
	</jsp:forward><%
	}
%>