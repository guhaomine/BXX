package daouse;
import java.sql.*;

import bean.App_info;
import bean.Arrange_info;
import bean.Class_info;
import bean.Course_info;
import bean.Exp_item;
import bean.Room_info;
import bean.Teacher_info;
import bean.User;

public class UserDAOImpl implements UserDAO{
    private Connection conn=null;
    private PreparedStatement pstmt=null;
    public UserDAOImpl(Connection conn) {
    	this.conn=conn;
    }
 
  
     // 用户名，密码登录操作
     public boolean isLogin(User user) throws Exception {
            boolean flag = false;
            String sql = "SELECT * FROM user_admin" ;  
            this.pstmt = this.conn.prepareStatement(sql);   //预编译
            ResultSet rs = pstmt.executeQuery() ;  
            String username = user.getAd_user_name();
            String password = user.getAd_user_pwd();
            while (rs.next()) {
                // 查询出内容，分别与用户输入的用户名和密码进行比较
                if (username.equals(rs.getString("ad_user_name")) && password.equals(rs.getString("ad_user_pwd"))) {
                    flag = true;
                }
            }
            this.pstmt.close();  //关闭打开的操作
            return flag;
     }


	@Override
	public void insert(User user) throws Exception {
		// TODO 自动生成的方法存根
		  String sql = "INSERT INTO user_admin(ad_no,ad_name,ad_user_name,ad_user_pwd,ad_tell,ad_email) VALUES(?,?,?,?,?,?)" ;  
	        PreparedStatement pstmt = null ;  
	        DataBaseConnection dbc = null ;  
	        // 下面是针对数据库的具体操作  
	        try{  
	            // 连接数据库  
	            dbc = new DataBaseConnection() ;  
	            pstmt = dbc.getConnection().prepareStatement(sql) ;  
	            pstmt.setString(1, user.getAd_user_name());  
	            pstmt.setString(2, user.getAd_user_pwd());  
	            // 进行数据库更新操作  
	            pstmt.executeUpdate() ;  
	            pstmt.close() ;  
	        }catch (Exception e){  
	            throw new Exception("操作出现异常") ;  
	        }  
	        finally{  
	            // 关闭数据库连接  
	            dbc.close() ;  
	        } 
		
	}


	@Override
	public void updata(User user) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "UPDATE user_admin SET ad_name=?,ad_user_pwd=?,ad_tell=?,ad_email=? WHERE ad_user_name=?" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql) ;           
            pstmt.setString(1, user.getAd_name());  
            pstmt.setString(2, user.getAd_user_pwd());
            pstmt.setString(3, user.getAd_tell());
            pstmt.setString(4,user.getEmail());
            pstmt.setString(5,user.getAd_user_name());
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }  
        catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
		
	}


	@Override
	public void delete(User user) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM teacher WHERE ad_user_name=?" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql) ;           
            pstmt.setString(1,user.getAd_user_name()) ;  
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
		
	}


	@Override
	public User select(User user) throws Exception {
		// TODO 自动生成的方法存根
		   User use = null ;  
	        String sql = "SELECT * FROM user_admin WHERE ad_user_name=?" ;  
	        PreparedStatement pstmt = null ;  
	        DataBaseConnection dbc = null ;  
	        // 下面是针对数据库的具体操作  
	        try{  
	            // 连接数据库  
	            dbc = new DataBaseConnection() ;  
	            pstmt = dbc.getConnection().prepareStatement(sql) ;           
	            pstmt.setString(1, user.getAd_user_name());  
	            // 进行数据库查询操作  
	            ResultSet rs = pstmt.executeQuery() ;  
	            if(rs.next())  
	            {  
	                // 查询出内容，之后将查询出的内容赋值给user对象  
	                use = new User() ;  
	                use.setAd_no(rs.getString(1));  
	                use.setAd_user_name(rs.getString(3));  
	                use.setAd_name(rs.getString(2));
	                use.setAd_user_pwd(rs.getString(4));
	                use.setAd_tell(rs.getString(5));
	                use.setEmail(rs.getString(6));
	            }  
	            rs.close() ;  
	            pstmt.close() ;  
	        }catch (Exception e){  
	            throw new Exception("操作出现异常") ;  
	        }  
	        finally{  
	            // 关闭数据库连接  
	            dbc.close() ;  
	        }  
	        return use ; 
		
	}

//机房操作
	@Override
	public void insert(Room_info room) throws Exception {
		// TODO 自动生成的方法存根
		  String sql = "INSERT INTO room_info(name,device_num,device_capacity) VALUES(?,?,?)" ;  
	        PreparedStatement pstmt = null ;  
	        DataBaseConnection dbc = null ;  
	        // 下面是针对数据库的具体操作  
	        try{  
	            // 连接数据库  
	            dbc = new DataBaseConnection() ;  
	            pstmt = dbc.getConnection().prepareStatement(sql) ;  
	            
	            pstmt.setString(1, room.getName()); 
	            pstmt.setString(2, room.getDevice_num());
	            pstmt.setString(3, room.getDevice_capacity());
	            // 进行数据库更新操作  
	            pstmt.executeUpdate() ;  
	            pstmt.close() ;  
	        }catch (Exception e){  
	            throw new Exception("操作出现异常") ;  
	        }  
	        finally{  
	            // 关闭数据库连接  
	            dbc.close() ;  
	        } 
		
		
		
	}


	@Override
	public void updata(Room_info room) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "UPDATE room_info SET name=?,device_num=?,device_capacity=? WHERE room_no=?" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql);           
            pstmt.setString(1, room.getName());
            pstmt.setString(2, room.getDevice_num());
            pstmt.setString(3, room.getDevice_capacity());
            pstmt.setInt(4, room.getRoom_no());
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }  
        catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
	}


	@Override
	public void delete(Room_info room) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM room_info WHERE room_no=?" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql) ;           
            pstmt.setInt(1,room.getRoom_no()) ;  
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
		
	}


	@Override
	public Room_info[] select(Room_info room) throws Exception {
		// TODO 自动生成的方法存根
		System.out.println("d22");
		int i=0;
		Room_info[] roo= null;
        String sql = "SELECT * FROM room_info" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE) ; 
            
           /* pstmt.setString(1, user.getAd_user_name());*/  
            // 进行数据库查询操作  
            ResultSet rs = pstmt.executeQuery();
            rs.last();
            int x=rs.getRow();
            roo= new Room_info[x];
            System.out.println("结果集长度："+rs.getRow());
            rs.beforeFirst();
            while(rs.next())  
            {  
                // 查询出内容，之后将查询出的内容赋值给user对象  
                roo[i]=new  Room_info();
                roo[i].setRoom_no(rs.getInt(1));  
                roo[i].setName(rs.getString(2));
                roo[i].setDevice_num(rs.getString(3));
                roo[i].setDevice_capacity(rs.getString(4));
                roo[i].setDevice_describe(rs.getString(5));
                i++;
                
            }  
            rs.close() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
        return roo ; 
	}

//课程管理
	@Override
	public void insert(Course_info course) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "INSERT INTO course_info(course_name,course_hour,course_exp_hour,course_gredit) VALUES(?,?,?,?)" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql) ;   
            pstmt.setString(1, course.getCourse_name()); 
            pstmt.setString(2, course.getCourse_hour());
            pstmt.setString(3, course.getCourse_exp_hour());
            pstmt.setString(4, course.getCourse_gredit());
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        } 
		
	}


	@Override
	public void updata(Course_info course) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "UPDATE course_info SET course_name=?,course_hour=?,course_exp_hour=?,course_gredit=? WHERE course_no=?" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql);           
            pstmt.setString(1, course.getCourse_name());
            pstmt.setString(2, course.getCourse_hour());
            pstmt.setString(3, course.getCourse_exp_hour());
            pstmt.setString(4, course.getCourse_gredit());
            pstmt.setInt(5, course.getCourse_no());
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }  
        catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
		
	}


	@Override
	public void delete(Course_info course) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM course_info WHERE course_no=?" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql) ;           
            pstmt.setInt(1,course.getCourse_no()) ;  
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
		
	}


	@Override
	public Course_info[] select(Course_info course) throws Exception {
		// TODO 自动生成的方法存根
		int i=0;
		Course_info[] cour= null;
        String sql = "SELECT * FROM course_info" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE) ; 
            
           /* pstmt.setString(1, user.getAd_user_name());*/  
            // 进行数据库查询操作  
            ResultSet rs = pstmt.executeQuery();
            rs.last();
            int x=rs.getRow();
            cour= new Course_info[x];
            System.out.println("结果集长度："+rs.getRow());
            rs.beforeFirst();
            while(rs.next())  
            {  
                // 查询出内容，之后将查询出的内容赋值给user对象  
                cour[i]=new  Course_info();
                cour[i].setCourse_no(rs.getInt(1));  
                cour[i].setCourse_name(rs.getString(2));
                cour[i].setCourse_hour(rs.getString(3));
                cour[i].setCourse_exp_hour(rs.getString(4));
                cour[i].setCourse_gredit(rs.getString(5));
                i++;
                
            }  
            rs.close() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
        return cour ; 
	}

//教师管理
	@Override
	public void insert(Teacher_info teacher) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "INSERT INTO teacher_info(teacher_name,teacher_tell,teacher_pwd,teacher_email) VALUES(?,?,?,?)" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql) ;    
            pstmt.setString(1, teacher.getTeacher_name()); 
            pstmt.setString(2, teacher.getTeacher_tell());
            pstmt.setString(3, teacher.getTeacher_pwd());
            pstmt.setString(4, teacher.getTeacher_email());
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        } 
		
	}


	@Override
	public void updata(Teacher_info teacher) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "UPDATE teacher_info SET teacher_name=?,teacher_tell=?,teacher_pwd=?,teacher_email=? WHERE teacher_no=?" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql);           
            pstmt.setString(1, teacher.getTeacher_name());
            pstmt.setString(2, teacher.getTeacher_tell());
            pstmt.setString(3, teacher.getTeacher_pwd());
            pstmt.setString(4, teacher.getTeacher_email());
            pstmt.setInt(5, teacher.getTeacher_no());
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }  
        catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
		
	}


	@Override
	public void delete(Teacher_info teacher) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM teacher_info WHERE teacher_no=?" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql) ;           
            pstmt.setInt(1,teacher.getTeacher_no()) ;  
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
	}


	@Override
	public Teacher_info[] select(Teacher_info teacher) throws Exception {
		// TODO 自动生成的方法存根
		int i=0;
		Teacher_info[] teach= null;
        String sql = "SELECT * FROM teacher_info" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE) ; 
            
           /* pstmt.setString(1, user.getAd_user_name());*/  
            // 进行数据库查询操作  
            ResultSet rs = pstmt.executeQuery();
            rs.last();
            int x=rs.getRow();
            teach= new Teacher_info[x];
            System.out.println("结果集长度："+rs.getRow());
            rs.beforeFirst();
            while(rs.next())  
            {  
                // 查询出内容，之后将查询出的内容赋值给user对象  
                teach[i]=new  Teacher_info();
                teach[i].setTeacher_no(rs.getInt("teacher_no"));  
                teach[i].setTeacher_name(rs.getString(2));
                teach[i].setTeacher_tell(rs.getString(3));
                teach[i].setTeacher_pwd(rs.getString(4));
                teach[i].setTeacher_email(rs.getString(5));
                i++;
                System.out.println(rs.getInt(1)+"zheshiflag");
                
            }  
            rs.close() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
        return teach ; 
	}

//班级管理
	@Override
	public void insert(Class_info cla) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "INSERT INTO class_info(majior_name,class_name) VALUES(?,?)" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql) ;   
            pstmt.setString(1, cla.getMajior_name()); 
            pstmt.setString(2, cla.getClass_name());
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        } 
	
	}


	@Override
	public void updata(Class_info cla) throws Exception {
		// TODO 自动生成的方法存根
		
		String sql = "UPDATE class_info SET majior_name=?,class_name=? WHERE class_no=?" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql);           
            pstmt.setString(1, cla.getMajior_name());
            pstmt.setString(2, cla.getClass_name());
            pstmt.setInt(3, cla.getClass_no());
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }  
        catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
	}


	@Override
	public void delete(Class_info cla) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM class_info WHERE class_no=?" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql) ;           
            pstmt.setInt(1,cla.getClass_no()) ;  
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
	}


	@Override
	public Class_info[] select(Class_info cla) throws Exception {
		// TODO 自动生成的方法存根
		int i=0;
		Class_info[] clas= null;
        String sql = "SELECT * FROM class_info" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE) ; 
            
           /* pstmt.setString(1, user.getAd_user_name());*/  
            // 进行数据库查询操作  
            ResultSet rs = pstmt.executeQuery();
            rs.last();
            int x=rs.getRow();
            clas= new Class_info[x];
            System.out.println("结果集长度："+rs.getRow());
            rs.beforeFirst();
            while(rs.next())  
            {  
                // 查询出内容，之后将查询出的内容赋值给user对象  
                clas[i]=new  Class_info();
                clas[i].setClass_no(rs.getInt(1));  
                clas[i].setMajior_name(rs.getString(2));
                clas[i].setClass_name(rs.getString(3));
                
                i++;
                
            }  
            rs.close() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
        return clas ; 
	}

//实验项目管理
	@Override
	public void insert(Exp_item exp) throws Exception {
		// TODO 自动生成的方法存根
		 String sql = "INSERT INTO exp_item(exp_item_hour,exp_type,exp_item_name,fk_course_no) VALUES(?,?,?,?)" ;  
	        PreparedStatement pstmt = null ;  
	        DataBaseConnection dbc = null ;  
	        // 下面是针对数据库的具体操作  
	        try{  
	            // 连接数据库  
	            dbc = new DataBaseConnection() ;  
	            pstmt = dbc.getConnection().prepareStatement(sql) ;  	              
	            pstmt.setString(1, exp.getExp_item_hour()); 
	            pstmt.setString(2, exp.getExp_type());
	            pstmt.setString(3, exp.getExp_item_name());
	            pstmt.setString(4, exp.getFk_course_no());
	            // 进行数据库更新操作  
	            pstmt.executeUpdate() ;  
	            pstmt.close() ;  
	        }catch (Exception e){  
	            throw new Exception("操作出现异常") ;  
	        }  
	        finally{  
	            // 关闭数据库连接  
	            dbc.close() ;  
	        } 
		
	}


	@Override
	public void updata(Exp_item exp) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "UPDATE exp_item SET exp_item_hour=?,exp_type=?,exp_item_name=?,fk_course_no=? WHERE exp_item_no=?" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql);           
            pstmt.setString(1, exp.getExp_item_hour());
            pstmt.setString(2, exp.getExp_type());
            pstmt.setString(3, exp.getExp_item_name());
            pstmt.setString(4, exp.getFk_course_no());
            pstmt.setInt(5, exp.getExp_item_no());
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }  
        catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        } 
		
	}


	@Override
	public void delete(Exp_item exp) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM exp_item WHERE exp_item_no=?" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql) ;           
            pstmt.setInt(1,exp.getExp_item_no()) ;  
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
	}


	@Override
	public Exp_item[] select(Exp_item exp) throws Exception {
		// TODO 自动生成的方法存根
		int i=0;
		Exp_item[] exps= null;
        String sql = "SELECT * FROM exp_item" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE) ; 
            
           /* pstmt.setString(1, user.getAd_user_name());*/  
            // 进行数据库查询操作  
            ResultSet rs = pstmt.executeQuery();
            rs.last();
            int x=rs.getRow();
            exps= new Exp_item[x];
            System.out.println("结果集长度："+rs.getRow());
            rs.beforeFirst();
            while(rs.next())  
            {  
                // 查询出内容，之后将查询出的内容赋值给user对象  
                exps[i]=new  Exp_item();
                exps[i].setExp_item_no(rs.getInt(1)); 
                exps[i].setExp_item_hour(rs.getString(2));
                exps[i].setExp_type(rs.getString(3));
                exps[i].setExp_item_name(rs.getString(4));
                exps[i].setFk_course_no(rs.getString(5));
                i++;
                
            }  
            rs.close() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
        return exps ; 
	}


	@Override
	public void insert(App_info app) throws Exception {
		// TODO 自动生成的方法存根
		 String sql = "INSERT INTO app_info(fk_course_no,course_idx,app_stu_num,fk_class_no,fk_teacher_no,app_term,week,day,jie) VALUES(?,?,?,?,?,?,?,?,?)" ;  
	        PreparedStatement pstmt = null ;  
	        DataBaseConnection dbc = null ;  
	        // 下面是针对数据库的具体操作  
	        try{  
	            // 连接数据库  
	            dbc = new DataBaseConnection() ;  
	            pstmt = dbc.getConnection().prepareStatement(sql) ;  
	            pstmt.setInt(1, app.getFk_course_no());  
	            pstmt.setInt(2, app.getCourse_idx()); 
	            pstmt.setInt(3, app.getApp_stu_num());
	            pstmt.setInt(4, app.getFk_class_no());
	            pstmt.setInt(5, app.getFk_teacher_no());
	            pstmt.setString(6, app.getApp_term());
	            pstmt.setInt(7, app.getWeek()); 
	            pstmt.setInt(8, app.getDay());
	            pstmt.setInt(9, app.getJie());
	            // 进行数据库更新操作  
	            pstmt.executeUpdate() ;  
	            pstmt.close() ;  
	        }catch (Exception e){  
	            throw new Exception("操作出现异常") ;  
	        }  
	        finally{  
	            // 关闭数据库连接  
	            dbc.close() ;  
	        } 
		
	}


	@Override
	public void updata(App_info app) throws Exception {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public void delete(App_info app) throws Exception {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM app_info WHERE app_no=?" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql) ;           
            pstmt.setInt(1,app.getApp_no()) ;  
            // 进行数据库更新操作  
            pstmt.executeUpdate() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        } 
	}


	@Override
	public App_info[] select(App_info app) throws Exception {
		// TODO 自动生成的方法存根
		int i=0;
		App_info[] apps= null;
        String sql = "SELECT * FROM app_info" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE) ; 
            
           /* pstmt.setString(1, user.getAd_user_name());*/  
            // 进行数据库查询操作  
            ResultSet rs = pstmt.executeQuery();
            rs.last();
            int x=rs.getRow();
            apps= new App_info[x];
            System.out.println("结果集长度："+rs.getRow());
            rs.beforeFirst();
            while(rs.next())  
            {  
                // 查询出内容，之后将查询出的内容赋值给user对象  
                apps[i]=new  App_info();
                apps[i].setApp_no(rs.getInt(1)); 
                apps[i].setFk_course_no(rs.getInt(2));
                apps[i].setCourse_idx(rs.getInt(3));
                apps[i].setApp_stu_num(rs.getInt(4));
               apps[i].setFk_class_no(rs.getInt(5));
               apps[i].setFk_teacher_no(rs.getInt(6));
               apps[i].setApp_term(rs.getString(7));
               apps[i].setWeek(rs.getInt(8));
               apps[i].setDay(rs.getInt(9));
               apps[i].setJie(rs.getInt(10));
                i++;
                
            }  
            rs.close() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
        return apps ; 
	}


	@Override
	public void insert(Arrange_info arr) throws Exception {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public void updata(Arrange_info arr) throws Exception {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public void delete(Arrange_info arr) throws Exception {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public Arrange_info[] select(Arrange_info arr) throws Exception {
		// TODO 自动生成的方法存根
		int i=0;
		Arrange_info[] arrs= null;
        String sql = "SELECT * FROM arrange_info" ;  
        PreparedStatement pstmt = null ;  
        DataBaseConnection dbc = null ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            dbc = new DataBaseConnection() ;  
            pstmt = dbc.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE) ; 
            
           /* pstmt.setString(1, user.getAd_user_name());*/  
            // 进行数据库查询操作  
            ResultSet rs = pstmt.executeQuery();
            rs.last();
            int x=rs.getRow();
            arrs= new Arrange_info[x];
            System.out.println("结果集长度："+rs.getRow());
            rs.beforeFirst();
            while(rs.next())  
            {  
                // 查询出内容，之后将查询出的内容赋值给user对象  
                arrs[i]=new  Arrange_info();
                arrs[i].setArrange_no(rs.getInt(1)); 
                arrs[i].setFk_app_no(rs.getInt(2)); 
                arrs[i].setFk_exp_item_no(rs.getInt(3));
                arrs[i].setStu_num(rs.getInt(4)); 
                arrs[i].setFk_room_no(rs.getInt(5)); 
                arrs[i].setArrange_week(rs.getInt(6));
                arrs[i].setArrange_day(rs.getInt(7)); 
                arrs[i].setArrange_interval(rs.getInt(8)); 
                i++;
                
            }  
            rs.close() ;  
            pstmt.close() ;  
        }catch (Exception e){  
            throw new Exception("操作出现异常") ;  
        }  
        finally{  
            // 关闭数据库连接  
            dbc.close() ;  
        }  
        return arrs ; 
	} 
	
	
	
	
    
}
