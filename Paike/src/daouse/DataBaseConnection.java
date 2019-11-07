package daouse;

import java.sql.*;

public class DataBaseConnection {
 private final String DBDRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
 private final String DBURL="jdbc:sqlserver://localhost:1433;DatabaseName=PK";
 private final String DBUSER="sa";
 private final String DBPASSWORD="111111";
/*	 private final String DBDRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	 private final String DBURL="jdbc:sqlserver://rm-bp13i446vr6les364ko.sqlserver.rds.aliyuncs.com:3433;DatabaseName=PK";
	 private final String DBUSER="gh";
	 private final String DBPASSWORD="Guhao200625";*/
 private Connection conn=null;
 public DataBaseConnection() {
	 try {
		 Class.forName(DBDRIVER).newInstance();
		 conn=DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
	 }catch(Exception e) {
		 
		 System.out.println("加载驱动程序失败!");
	 } 
 }

 public Connection getConnection() {
	 return this.conn;
 }
  
 public void close() {
	 
	 try {
		 conn.close();
	 }
	 catch(Exception e) {
		 System.out.println("数据库关闭连接失败!");
	 }
 }
 
}
