<%@page import="javax.swing.text.DefaultEditorKit.InsertBreakAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.regex.Pattern" import ="java.util.regex.*" %>
    <%@include file="conn.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>注册页面</title>
	<script type="text/javascript">
		function check_regist(){
			var t=0;
			var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
            var reg=/^[1-9]\d*$|^0$/;
			var c1=document.getElementById("t").value;//user
			var c2=document.getElementById("y").value;//pwd
			var c3=document.getElementById("u").value;//querenmima
			var c4=document.getElementById("i").value;//shoujihao
			var c5=document.getElementById("o").value;//youxiang
			var c6=document.getElementById("p").value;//gerenjianjie
			if(c1==""){alert("用户名不能为空！");}
			else if(c1.length<2||c1.length>20){alert("用户名应在2到20字之间！");}
			else if(c2==""){alert("密码不能为空！");}
			else if(c2.length<5||c2.length>20){alert("密码应在5到20字之间！");}
			else if(c3!=c2){alert("两次密码输入不一致！");}
			else if(c4!=""&&(reg.test(c4)==false||c4.length!=11))
				{alert("手机号应为11位的纯数字！");}
			else if (c5!=""&&myReg.test(c5)==false) 
			  {alert("邮箱格式错误！");}

			 else if (c6!=""&&c6.length>200) {
			  alert("个人简介应小于200字");}	
		else 
			{alert("注册成功！");
		  }
		}
	</script>
</head>
<body>
	
		<br><br><br><br>
		<form action="regist.jsp" method="post">
		<table align="center" border="1"  cellspacing="0"  style="background-color: #F5F5F5; ">
			<tr>
				<td width="80"  height="30" align="center">项目</td><td width="350"  align="center">内容</td>
			</tr>
			<tr><td colspan="2"><input  type="hidden" name="hid" value="1"></td></tr>
			<tr>
				<td height="30" align="right">用户名</td><td><input id="t" type="text" size=55 name="yonghuming"></td>
			</tr>
			<tr>
				<td height="30" align="right">密码</td><td><input id="y" size=55 type="text" name="mima"></td>
			</tr>
			<tr>
				<td height="30" align="right">确认密码</td><td><input id="u" size=55 type="text" name="querenmima"></td>
			</tr>
			<tr>
				<td height="30" align="right">手机号</td><td><input id="i" size=55 type="number" name="shoujihao" ></td>
			</tr>
			
			<tr>
				<td height="30" align="right">邮箱地址</td><td><input id="o" size=55 type="email" name="youxiangdizhi"></td>
			</tr>
			<tr>
				<td height="150" align="right">个人简介</td><td><textarea id="p" name="a" style="width:350px;height:150px;"></textarea></td>
			</tr>
			<tr align="center" height="35"><td colspan="2"><input type="submit" name="" onclick="check_regist()" value="提交注册"></td></tr>
		
		</table>
		</form>
	

</body>
</html>
	<%
	request.setCharacterEncoding("UTF-8");
	String hid=request.getParameter("hid");
String na=request.getParameter("yonghuming");
String nb=request.getParameter("mima");
String nc=request.getParameter("querenmima");
String nd=request.getParameter("shoujihao");
String ne=request.getParameter("youxiangdizhi");
String nf=request.getParameter("a");
String c="用户名不能为空 并应多于2字 应少于20字<br>密码和确认密码不能为空并且应相同应多于5字 少于20字<br>手机号可为空 并应为纯数字 长度为11<br>邮箱地址可为空 否则输入应符合邮箱格式如：xxx@xxx.xxx 或 xxx@xxx.xxx.xxx<br>个人简介可为空 否则长度应小于200";

int flag=0;
if(na==""||na==null){
	flag=1;
}
else if(na.length()<2||na.length()>20)
{
	flag=1;}
if(nb==""||nb==null){
	flag=1;
}
else if(nb.length()<5||nb.length()>20)
{
	flag=1;}
else if(!nb.equals(nc)){
	flag=1;
}
if(nd==""||nd==null){}
else if(nd!=""&&nd.length()!=11)
{flag=1;}
if(ne==""||ne==null){}
else{
Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
Matcher matcher = emailPattern.matcher(ne);
if(!matcher.find()){
	flag=1;
}
}
if(nf==null||nf==""){
}
else if(nf!=""&&nf.length()>200)
{flag=1;}

if(flag==0&&hid!=null){
	ResultSet rs=sql.executeQuery("select * FROM user where username='"+na+"'");
	if(rs.next())
	{
		%>
		<jsp:forward page="errorjsp.jsp">
		<jsp:param value="该用户名已存在，请更换用户名！" name="tx"/>
		<jsp:param value="1" name="flag"/>
		</jsp:forward>
		<%
		}
	else{
		String insert="insert into user values('"+na+"','"+nb+"','"+nd+"','"+ne+"','"+nf+"')";
		Statement at=con.createStatement();
		int in=at.executeUpdate(insert);
		con.close();
	}
	response.sendRedirect("login.jsp");}
else if(flag==1&&hid!=null) 
	{
	%><jsp:forward page="errorjsp.jsp">
	<jsp:param value="2" name="flag"/>
	<jsp:param value="你的用户名：" name="xz"/>
	<jsp:param value="你的密码：" name="cv"/>
	<jsp:param value="你的确认密码：" name="qr"/>
	<jsp:param value="你的手机号：" name="tel" />
	<jsp:param value="你的邮箱地址：" name="email"/>
	<jsp:param value="你的个人简介：" name="jianjie"/>
	<jsp:param value="<%=c %>" name="tx"/>
	<jsp:param value="<%=na%>" name="bt"/>
	<jsp:param value="<%=nb %>" name="nr"/>
	<jsp:param value="<%=nc %>" name="nc"/>
	<jsp:param value="<%=nd %>" name="nd"/>
	<jsp:param value="<%=ne %>" name="ne"/>
	<jsp:param value="<%=nf %>" name="nf"/>
	</jsp:forward><%
	}
%>