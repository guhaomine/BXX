<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
    <%!Connection con;
    Statement sql;
    ResultSet rs1,rs2;
    String find_user="select * FROM user where 1=1";
    String find_article="select * FROM article where 1=1";
    int i=1;
    %>
<%
try{
	Class.forName("com.mysql.jdbc.Driver");
}
catch(Exception e){}


try{
	String url="jdbc:mysql://127.0.0.1/liuyanban";
	String a="root";
	String b="200625";
	con=DriverManager.getConnection(url,a,b);
	sql=con.createStatement();
	rs1=sql.executeQuery(find_user);
	rs2=sql.executeQuery(find_article);	
}
catch(SQLException e)
{out.println(e);}


%>
