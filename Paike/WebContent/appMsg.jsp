<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%  
if( session.getAttribute("admin")==null|| session.getAttribute("admin")=="")
response.sendRedirect("login.jsp"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="jquery-3.1.1.min.js"></script>

<style type="text/css">
*{
	padding:0;
	margin:0;
}
body{background-color: #69F;}
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
	margin-right:20px;
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
#main .neck{
	background-color:#9FC;
	height:52px;
	
}
#main .neck .blank{
	float:left;
	border:solid #CCC 1px;
	border-bottom:0;
	width:150px;
	line-height:50px;

	margin-left:20px;
	margin-right:10px;
	font-size:18px;
	text-align:center;
	
}
#main .neck .menu{
	border:solid #CCC 1px;
	font-size:18px;
	line-height:50px;
	text-align:left;
	padding-left:30px;
	
}
#main .neck .menu li{
	border:solid #FFF 1px;
	float:left;
	list-style-type:none;
	width:150px;
	text-align:center;
	margin-left:20px;
	margin-right:10px;
}
#main .neck .menu li:hover{
	background-color:#FFF;
	font-weight:bold;
	border:solid #000 1px;
	line-height:44px;
}

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
#main .neck .blank:hover{
background-color:#FFF;
	font-weight:bold;
	border:solid #000 1px;
	line-height:44px;

}
#main .left li{
	border:solid #CCC 1px;
	list-style-type:none;
	
}
#main .left li:hover{
	font-weight:bold;
	background-color: #0CF;
	border:solid #CCC 1px;
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

.right{
	background-color:#FC9;
	height:1000px;
	position:absolute;
	
	width:1990px;
	min-width:800px;
}

#qwe td{text-align:center;
width:200px;
height:100px;
color:green;
cursor:pointer;
}
.q{
	width:250px;
	height:30px;
	font-size:28px;
}

</style>
<script type="text/javascript">
var n;
var m;
$(document).ready(function(){
	 
	var nu=${sessionScope.apps_len};

	   var p=0;
       for(var q=1;q<=6;q++)
     	 {
     	 for(var w=1;w<=7;w++)
     		 {
     		 $('#qwe').find('td').eq(p).attr("temp2",q).attr("temp1",w);
     		 p++;
     		 }
     	 } 
        show();
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
   	 }   }
   
      $("#sel").change(function(){
    	  $('#qwe').find('td').text("申请排课");
    	  $('#qwe').find('td').css("color","green");
    	  show();
    	
  
       });
      $("#room_num").change(function(){
    	  $('#qwe').find('td').text("申请排课");
    	  $('#qwe').find('td').css("color","green");
    	  show();
    	
  
       });
	

       
	$("#qwe td").click(function(){
		m=$(this).attr('temp1');
		n=$(this).attr('temp2');
		//alert($(this).attr('temp1')+","+$(this).attr('temp2'))
		$("#hid").animate({right:'40%'});		
	});
	$("#quxiao").click(function(){
		$("#hid").animate({right:'-1000px'});
		
	});
		$("#add").click(function(){
			var a=$("#course_num option:selected").attr('value');
			var b=$("#exp_num option:selected").attr('value');
			var c=$("#room_num option:selected").attr('value');
			var d=$("#class_num option:selected").attr('value');
			var e=$("#num option:selected").attr('value');
			var f=$("#term option:selected").val();
			var o=$("#sel option:selected").val();
			if(confirm("确定添加?"))
			$.post("Servlet_doinsert",{flag:"4",course_num:a,exp_num:b,room_num:c,class_num:d,num:e,term:f,week:o,day:m,jie:n},function(data){alert("添加成功");window.location.href="show.jsp";});
			
		});
});
</script>
<title>信息管理界面</title>
</head>

<body>
	<div id="main">
    	<div class="header">
        	<div class="tit1">排课申请</div>
            <div class="tit2">
                <div id="adminImg"><img src="images/admin.png" width="32" height="32" /></div>
           	  	<div id="admin"><a href="userinfo.jsp">${sessionScope.admin.ad_name}</a></div>
            </div>
        </div>
        <div class="neck">
        	<a href="show.jsp"><div class="blank">返回</div></a>
            <div class="menu">
     
            	<div  style="float:left;">第几周<select id="sel" style="widtn:100px;font-size:30px;">
            	<c:forEach var="i" begin="1" end="21">
            		<option >${i}</option>
            		</c:forEach>
            		</select>
            		</div>
            		<div>机房<select id="room_num" style="widtn:100px;font-size:30px;">
            	    <c:forEach var="i" begin="0" end="${rooms_len}">
            		<option value="${sessionScope.rooms[i].room_no}">${sessionScope.rooms[i].name}</option>
            		</c:forEach>
            		</select>
            		</div>
            </div>
        </div>
        <div style="left:-50px;">
        <table id="qwe"  cellspacing="0" border="1">
        <tr height="30px"><th width="100px"><th>周一</th><th>周二</th><th>周三</th><th>周四</th><th>周五</th><th>周六</th><th>周日</th></tr>
        <tr><th>1-2节</th><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td></tr>
        <tr><th>3-4节</th><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td></tr>
        <tr><th>5-6节</th><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td></tr>
        <tr><th>7-8节</th><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td></tr>
        <tr><th>9-10节</th><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td></tr>
        <tr><th>11-12节</th><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td><td>申请排课</td></tr>
        </table>
        
        </div>
        <div  id="hid" style="width:400px;top:250px;border:1px black solid;right:-1000px;position:fixed;background-color:white;padding:30px;">
        <table border="0">       
        <tr><th width="100px;">课程名称</th><td><select id="course_num" style="text-align:center;width:200px;font-size:20px;display:block;">
            	<c:forEach var="i" begin="0" end="${courses_len}">
            		<option value="${sessionScope.courses[i].course_no}">${sessionScope.courses[i].course_name}</option>
            		</c:forEach>
            		</select></td></tr>
            		
            		<tr><th>实验名称</th><td><select id="exp_num" style="text-align:center;width:200px;font-size:20px;display:block;">
            	<c:forEach var="i" begin="0" end="${exps_len}">
            		<option value="${sessionScope.exps[i].exp_item_no}">${sessionScope.exps[i].exp_item_name}</option>
            		</c:forEach>
            		</select></td></tr>
            		
            		<tr><th>专业</th><td><select id="class_num" style="text-align:center;width:200px;font-size:20px;display:block;">
            	<c:forEach var="i" begin="0" end="${clas_len }">
            		<option  value="${sessionScope.clas[i].class_no}">${sessionScope.clas[i].majior_name}</option>
            		</c:forEach>
            		</select></td></tr>
            		
        <tr><th>教师</th><td><select id="num" style="text-align:center;width:200px;font-size:20px;display:block;">
            	<c:forEach var="i" begin="0" end="${len }">
            		<option value="${sessionScope.teachers[i].teacher_no}">${sessionScope.teachers[i].teacher_name}</option>
            		</c:forEach>
            		</select></td></tr>
            		
            		<tr><th>学期</th><td><select id="term" style="text-align:center;width:200px;font-size:20px;display:block;">
            	
            		<option >2017-2018春季</option>
            		<option >2017-2018秋季</option>
            		<option >2018-2019春季</option>
            		<option >2018-2019秋季</option>
            		</select></td></tr>
        <tr ><td colspan="2"> <input id="add" type="button" style="padding:10px 70px 10px 70px;background-color:white;" value="添加" /><input id="quxiao" style="padding:10px; margin-left:20px;background-color:white;" type="button" value="取消"></td ></tr>
      
        </table>
        			
            	
        
        
        </div>
        
	</div>
</body>
</html>