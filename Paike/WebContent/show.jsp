<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%  
if( session.getAttribute("admin")==null|| session.getAttribute("admin")=="")
response.sendRedirect("login.jsp"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="jquery-3.1.1.min.js"></script>
<script type="text/javascript">
var len;

$(document).ready(function(){
	
	 var p=0;
	    for(var q=1;q<=6;q++)
	  	 {
	  	 for(var w=1;w<=7;w++)
	  		 {
	  		 $('#qwe').find('td').eq(p).attr("temp2",q).attr("temp1",w);
	  		 p++;
	  		 }
	  	 } 
	    
	$(".teacher").click(function(){
		post();
		window.location.href="TeacherInfo.jsp";
	});
	$(".room").click(function(){		
		post();
		window.location.href="roomMsg.jsp";
	});
	$(".cla").click(function(){	
		post();
		window.location.href="classMsg.jsp";
	});
	$(".course").click(function(){
		post();
		window.location.href="courseMsg.jsp";
	});
	$(".exp").click(function(){
		post();
		window.location.href="expMsg.jsp";
	});
	$(".pk").click(function(){
		post();
		window.location.href="appMsg.jsp";
	});
	$(".kebiao").click(function(){
		post();
		window.location.href="kebiaojsp.jsp";
	});
	$("#clear").click(function(){
		$.post("Servlet_dologin",{flag:"5"},function(){window.location.href="login.jsp";});
	
	});
	
	 show();
	
     $("#sel").change(function(){
  	  $('#qwe').find('td').text("");
  	  show();
     }); 
     $("#room_num").change(function(){
     	  $('#qwe').find('td').text("");
     	  show();
        }); 
	
});

function post(){
	$.post("Servlet_doteacher",{flag:"1"},function(data){
	});	
	$.post("Servlet_doroom",{flag:"1"},function(data){
	});
	$.post("Servlet_doclass",{flag:"1"},function(data){
	});	
	$.post("Servlet_docourse",{flag:"1"},function(data){
	});	
	$.post("Servlet_doexp",{flag:"1"},function(data){
	});	
	$.post("Servlet_doinsert",{flag:"1"},function(data){
	});
}
 function show(){
    var s=0;    
      for(var z=1;z<=6;z++)
  	   {
				   	 for(var x=1;x<=7;x++)
				         {
							   		 <c:forEach var="i" begin="0" end="${sessionScope.apps_len}">
							   		 var g=${sessionScope.apps[i].day};
									 var j=${sessionScope.apps[i].jie};
									var week=${sessionScope.apps[i].week}; 
									var jifang=${sessionScope.apps[i].course_idx};
									 if($('#qwe').find('td').eq(s).attr("temp2")==j&&$('#qwe').find('td').eq(s).attr("temp1")==parseInt(g)&&week==$("#sel option:selected").val()&&jifang==$("#room_num option:selected").attr("value"))  		 
							   		     { 
							   	 	     var f=${sessionScope.courses_len};
							   	 	     var msg="";
											   	 	 <c:forEach var="l" begin="0" end="${sessionScope.courses_len}">
											   				var bc=${sessionScope.courses[l].course_no};				   				  
											   				var ac=${sessionScope.apps[i].fk_course_no};				   				
											   				if(bc==ac){					   					
											   					var ab="${sessionScope.courses[l].course_name}";
											   					msg+=ab+",";
											   					
											   				}   			   				
											   		 </c:forEach>	
							   		 
											   		<c:forEach var="l" begin="0" end="${sessionScope.rooms_len}">
										   				var bc=${sessionScope.rooms[l].room_no};				   				  
										   				var ac=${sessionScope.apps[i].course_idx};				   				
										   				if(bc==ac){					   					
										   					var ab="${sessionScope.rooms[l].name}";
										   					msg+=ab+",";
										   					
										   				}   			   				
										   		 	</c:forEach>
						   		 
											   		<c:forEach var="l" begin="0" end="${sessionScope.clas_len}">
									   				var bc=${sessionScope.clas[l].class_no};				   				  
									   				var ac=${sessionScope.apps[i].fk_class_no};				   				
									   				if(bc==ac){					   					
									   					var ab="${sessionScope.clas[l].majior_name}";
									   					msg+=ab+",";	
									   				}   			   				
									   		         </c:forEach>
							   		 
							   		 
											   		<c:forEach var="l" begin="0" end="${sessionScope.len}">
									   				var bc=${sessionScope.teachers[l].teacher_no};				   				  
									   				var ac=${sessionScope.apps[i].fk_teacher_no};				   				
									   				if(bc==ac){					   					
									   					var ab="${sessionScope.teachers[l].teacher_name}";
									   					msg+=ab+"老师";	
									   					$('#qwe').find('td').eq(s).text(msg);
									   					$('#qwe').find('td').eq(s).css("color","red");
									   				}   			   				
									   		        </c:forEach>	
							   		
							   		 
				
							              }   
									 </c:forEach> 
						 s++; 
				   	     }
  	          }  
      } 

</script>
<title>信息管理界面</title>
<style type="text/css">
*{
	padding:0;
	margin:0;
}
body{background-color:#69F;}
#main{
	background: linear-gradient(to top left,#09F,#FFF);
}
#main .header{
	background-color: #69F;
	height:70px;
}
#main .header .tit1{
	float:left;
	font-size:28px;
	line-height:70px;
	width:300px;
	text-align:center;
}
#main .header .tit2{
	float:right;
	width:150px;
	margin-right:0;
}
#main .header .tit2:hover{
	background-color:#FFF;
}
#main .header .tit2 #adminImg{
	float:left;
	padding-top:19px;
} 
#main .header .tit2 #admin{
	font-size:18px;
	line-height:70px;
	width:100px;
	text-align:center;
}
/***************************/
#main .left{
	background-color: #9FF;
	border:solid #CCC 1px;
	font-size:20px;
	line-height:50px;
	width:230px;
	height:1000px;
	text-align:left;
	margin-right:20px;
	position:absolute;
	left:0;
}
#main .left li{
	border:solid #CCC 1px;
	list-style-type:none;
	
}
#main .left li:hover{
	font-weight:bold;
	background-color: #0CF;
	border:solid #CCC 1px;
	cursor:pointer;
}
#main .left .picture{
	float:left;
	display:block;
	height:41px;
	padding:9px 10px 0 30px;
}
a{
	text-decoration:none;
	color:black;
}
/****color1**********************************************************************************/
.right{
	/*background-color:#FC9;
	background: linear-gradient(to top left,#00CDCD,#FFF);*/
	background-image:url(images/4.jpg);
	background-repeat:no-repeat;
	opacity:0.9;
	height:964px;
	position:absolute;
	left:230px;
	width:1990px;
	min-width:800px;
}
#input_title{
	color:red;
	font-size:26px;
	margin:10px auto 2px 500px;
}
.drop_box{
	font-size:20px;
	min-width: 70px;
	margin-right:50px;
}



table{
	border:2px #1C86EE solid;
	margin-left:50px;
}
th{
	font-size:20px;
	line-height:50px;
	border:1px #1C86EE solid;
	width:100px;
}
#qwe td{
	line-height:26px;
	text-align:center;
	width:150px;
	height:80px;
	border:1px #1C86EE solid;
	cursor:default;
}

#clear:hover{
color:red;
cursor:pointer;
}
</style>
</head>

<body>
	<div id="main">
    	<div class="header">
        	<div class="tit1">机房排课系统</div><div id="clear" style="float:right;margin-top:50px;">注销</div>
            <div class="tit2">
                <div id="adminImg"><img src="images/admin.png" width="32" height="32" /></div>
                <div id="admin"><a href="userinfo.jsp">${sessionScope.admin.ad_name}</a></div>
                
           	  	<!-- <a href="userinfo.jsp"><div id="admin">${sessionScope.admin.ad_name}</div></a> -->
            </div>
        </div>
        <div class="left">
        	<ul>
			  <li>
					<span class="picture"><img src="images/photoshop.png" width="32" height="32" /></span>
					<div class="teacher">教师管理</div>
				</li>
                <li>
                	<span class="picture"><img src="images/computer.png" width="32" height="32" /></span>
                    <div class="room">实验室管理</div>
              	</li>
                <li>
                	<span class="picture"><img src="images/teamspeak.png" width="32" height="32" /></span>
                    <div class="cla">班级管理</div>
              	</li>
                <li>
                	<span class="picture"><img src="images/rosetta_stone.png" width="32" height="32" /></span>
                    <div class="course">课程管理</div>
              	</li>
                <li>
                	<span class="picture"><img src="images/computer.png" width="32" height="32" /></span>
                    <div class="exp">实验项目管理</div>
              	</li>
                <li>
                	<span class="picture"><img src="images/settings.png" width="32" height="32" /></span>
                    <div class="pk">在线排课</div>
              	</li>
                <li>
                	<span class="picture"><img src="images/control_panel.png" width="32" height="32" /></span>
                    <div class="kebiao">课表查询</div>
              </li>
            </ul>
             
        </div>
            		
		<div class="right" >
			<div id="input_title">第几周
				<select id="sel" class="drop_box">
					<c:forEach var="i" begin="1" end="21">
            		<option >${i}</option>
            		</c:forEach>
            	</select>机房
            	<select id="room_num"  class="drop_box">
            	    <c:forEach var="i" begin="0" end="${rooms_len}">
            		<option value="${sessionScope.rooms[i].room_no}">${sessionScope.rooms[i].name}</option>
            		</c:forEach>
				</select>
			</div>
         
        <table id="qwe"  cellspacing="0" border="1">
        <tr height="30px"><th></th><th>周一</th><th>周二</th><th>周三</th><th>周四</th><th>周五</th><th>周六</th><th>周日</th></tr>
        <tr><th>1-2节</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
        <tr><th>3-4节</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
        <tr><th>5-6节</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
        <tr><th>7-8节</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
        <tr><th>9-10节</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
        <tr><th>11-12节</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
        </table>
        
        </div>
	</div>
</body>
</html>