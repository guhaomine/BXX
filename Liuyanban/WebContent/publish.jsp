<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="java.util.Date,java.text.*" %>
 <%@include file="conn.jsp" %>
  <%-- <%
    int i=1;
    while(rs2.next()){
    	i++;
    }
    %> --%>
 <%
 Date nowday=new Date();
 int hour=nowday.getHours();
 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	String time=format.format(nowday);
 %>
<%
request.setCharacterEncoding("UTF-8");
String bt=request.getParameter("biaoti");
String nr=request.getParameter("content");
String name=String.valueOf(session.getAttribute("username"));
String tx="标题：不能为空 并且应多于2字 少于50字<br>正文内容：不能为空 并且多于5字 少于500字";
if(session.getAttribute("username")==null)
{%>
<script type="text/javascript">
		alert("请先登录！");
		window.location.href='list.jsp';
		</script>
<% 
}
else {
	int t=0;
	if (bt=="")
	{
		t=1;
	}	
	 else if (bt.length()>50||bt.length()<2) 
	 	{t=1;}
	else if(nr=="")
			{t=1;}
		else if(nr.length()<5||nr.length()>500)
			{t=1;}
		else t=0;
  if(t==0){
	
		String insert="insert into article(title,person,shijian,content) values('"+bt+"','"+name+"','"+time+"','"+nr+"')";
		Statement at=con.createStatement();
		int as=at.executeUpdate(insert);
		con.close();
	response.sendRedirect("list.jsp");
	}

else if(t==1){
		%>
		<jsp:forward page="errorjsp.jsp">
		<jsp:param value="1" name="flag"/>
		<jsp:param value="你的标题：" name="xz"/>
		<jsp:param value="你的内容：" name="cv"/>
		<jsp:param value="<%=bt %>" name="bt"/>
		<jsp:param value="<%=nr %>" name="nr"/>
		<jsp:param value="<%=tx %>" name="tx"/>
		</jsp:forward>
		<%
    } }
    %> 