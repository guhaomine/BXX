<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <body>

   <font size="16" color="red">发生错误！</font><br>
    <%=request.getParameter("tx")%><br>
   <%=request.getParameter("xz") %><%=request.getParameter("bt") %><br>
  <%=request.getParameter("cv") %><%=request.getParameter("nr") %><br>
   <%
   String flag=request.getParameter("flag");
   int a=Integer.parseInt(flag);
   if(a==2)
   {
	  out.println(request.getParameter("qr"));out.println(request.getParameter("nc")); %><br>
   <%=request.getParameter("tel")%><%=request.getParameter("nd") %><br>
   <%=request.getParameter("email")%><%=request.getParameter("ne") %><br>
   <%=request.getParameter("jianjie")%><%=request.getParameter("nf") %><br><%   
   }
   
   %>
    <br>
   </body>