<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="conn.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
ResultSet rs=sql.executeQuery("select * FROM article where id="+request.getParameter("id"));
if(rs.next()){
	if(!(rs.getString("person")).equals((String)session.getAttribute("username")))
	{%>
		<script type="text/javascript">
		alert("不能修改其他用户的数据!");
		window.location.href='list.jsp';
		</script>
	<%}else{
%>
<br><br><br><br>
<form action="modify.jsp" method="get">
<table align="center">
<tr>
<td>标题：</td><td><input type="text" name="biaoti"  style="width: 570px; font-size:26px;"  value="<%=rs.getString("title") %>"></td>
</tr>
<tr >
<td >内容：<br><br><br><br><br><br><br><br><br><br><br></td>
<td>
<textarea name="content" style="width: 570px; font-size:26px; height:200px;"><%=rs.getString("content")%></textarea>
</td>
</tr>
<tr><td><input type="hidden" name="hidden"></td><td><input type="hidden" value="<%=request.getParameter("id") %>" name="mi"></td></tr>
<tr><td align="center" colspan="2" ><input type="submit" onclick="return confirm('确认修改？');" value="修改" name="">&nbsp;&nbsp;&nbsp;<input type="button" value="返回" onclick="return window.location.href='list.jsp';"></td></tr>
</table>
</form>
<%}}
%>
</body>
</html>
<%
String id=request.getParameter("mi");
String hid=request.getParameter("hidden");
if(hid!=null){
String up="update article set title=?,content=? where id=?";
PreparedStatement p=con.prepareStatement(up);
p.setString(1,request.getParameter("biaoti"));
p.setString(2,request.getParameter("content"));
p.setString(3,id);
int rt=p.executeUpdate(); 
/* out.println(request.getParameter("biaoti")); */
con.close();
response.sendRedirect("list.jsp");
}

%>