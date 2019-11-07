<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
    <%@include file="conn.jsp" %>
    <%
    String id=request.getParameter("id");
    if(id==null||id=="")
    {%><jsp:forward page="errorjsp.jsp">
    <jsp:param value="无留言id!" name="tx"/>
    </jsp:forward>              <%}
     
   ResultSet rs=sql.executeQuery("select * FROM article where id="+request.getParameter("id")); 
    %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>详情页面</title>
	<style type="text/css">
		.gg{
			text-indent: 2em;
		}
		div{
			width: 900px;
			height: 900px;
			background-color: #D1EEEE;
			margin-left: 10%;
		}
	</style>
</head>
<body style="background-color:#F0F8FF; ">
<% if(rs.next()){%>
	<div>
	<br>
	&nbsp;&nbsp;&nbsp;<a href="list.jsp"><input type="button" value="返回"></a>
	<br>
<h2 align="center"><%=rs.getString("title") %></h2>
<h3 align="center">发布人:<%=rs.getString("person") %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布时间:<%=rs.getDate("shijian") %></h3>
<h4>内容:</h4>
<p class="gg" style="font-size:21px;letter-spacing:1.6;line-height:1.6;"><%=rs.getString("content")%></p>
<%} 
con.close();
%>
</body>
</html>