<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="conn.jsp" %>
    <jsp:include page="purview.jsp"></jsp:include>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>列表页面</title>
</head>
<style type="text/css">
	a{
		text-decoration: none;
		color: black;
	}
	body{
	/* background-color: rgb(0,245,255); */
	background-image:url("img/timg1.jpg") ;
	background-repeat: no-repeat;
	background-size: 100%;
	}
</style>
<body>
<h2 align="center">我的留言板</h2>
<%if(session.getAttribute("username")==null)
	{%><h3 align="center" style="text-indent: -13em"><a href="login.jsp"><input type="button" value="登录" ></a> | <a href="regist.jsp"><input type="button" value="注册"></a></h3><%}
	else
	{%>
		<h3 align="center" style="text-indent: -13em">欢迎您：<%=session.getAttribute("username") %> | <input id="zhuxiao" type="button" value="注销" onclick="zhuxiao()"></h3>
	<%}%>
<%
if(session.getAttribute("user")==null)
{}
else
{
	
	if(session.getAttribute("quanxian").equals("true"))
	{int j=1;
		%>
		<table border="1" cellspacing ="0" align="center" width="500">
		
		<tr height="30">
		<th></th><th width="200" align="center">标题</th><th>发布时间</th><th>发布人</th><th>管理</th>
		</tr>
		<%while(rs2.next()){
			%>
			<tr>
			<td><%=j%></td>
			<td align="center"><a href="detail.jsp?id=<%=rs2.getInt("id") %>"><%=rs2.getString("title") %></a></td>
			<td><%=rs2.getDate("shijian") %></td><td><%=rs2.getString("person") %></td>
			<td><a href="modify.jsp?id=<%=rs2.getInt("id") %>">修改</a>&nbsp;|&nbsp;<a onclick="return confirm('确认删除');" href="delete.jsp?id=<%=rs2.getInt("id") %>">删除</a></td>
			</tr>
			<% 
			
		j++;}%>
		</table><%
	}
	else
{ int i=1;%>

<table border="1" cellspacing ="0" align="center" width="500">
		<tr height="30">
		<th></th><th width="200" align="center">标题</th><th>发布时间</th><th>发布人</th><th>管理</th>
		</tr>
		<%while(rs2.next()){
			%><tr>
			<td><%=i%></td>
			<td align="center"><a href="detail.jsp?id=<%=rs2.getInt("id") %>"><%=rs2.getString("title") %></a></td>
			<td><%=rs2.getDate("shijian") %></td>
			<td><%=rs2.getString("person") %></td>
			<td><a href="modify.jsp?id=<%=rs2.getInt("id") %>">修改</a></td>
			</tr><% 
			i++;
			}%>
		
		</table><% }}
con.close();
%>
<br>
<form  action="publish.jsp" method="post">
<table align="center">
	<tr>
	<th>标题：</th><th><input  type="text" name="biaoti" id="q" style=" BACKGROUND-COLOR: transparent; width: 570px;font-size:26px;"></th>
</tr>
<tr >
	<th align="top">内容：<br><br><br><br><br><br><br><br><br><br></th><td>
	<textarea  id="w" name="content" style="BACKGROUND-COLOR: transparent; width: 570px;font-size:26px;  height:200px;"></textarea>
	</td>
</tr>
<tr>
<td align="center" colspan="2"><input type="submit" onclick="check()" value="发表" name=""></td>
</tr>
</table>
</form>
<script type="text/javascript">
function zhuxiao(){
	window.location.href="logout.jsp";
}
	function check() {
		var b=document.getElementById("w").value;
		var a=document.getElementById("q").value;
		if (a=="") {alert("标题不能为空！");}
		 else if (a.length>50||a.length<2) 
		 	{alert("标题应该在2到50字之间！");}
			else if(b=="")
				{alert("正文内容不能为空！");}
			else if(b.length<5||b.length>500)
				{alert("正文字数应在5到500字之间！");}

	}
</script>
</body>
</html>
