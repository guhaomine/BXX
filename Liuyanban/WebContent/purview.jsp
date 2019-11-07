<%@page import="org.apache.catalina.startup.SetAllPropertiesRule"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言板</title>
</head>
<body>

</body>
</html>
<%


if(session.getAttribute("username")==null)
{
	session.setAttribute("user", null);
	
	}
else
{
	session.setAttribute("user", session.getAttribute("username"));
	
	if(session.getAttribute("user").equals("admin"))
	{
		session.setAttribute("quanxian", "true");
	}
	else
		session.setAttribute("quanxian", "false");
}
%>