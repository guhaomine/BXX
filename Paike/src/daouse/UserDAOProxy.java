package daouse;

import bean.App_info;
import bean.Arrange_info;
import bean.Class_info;
import bean.Course_info;
import bean.Exp_item;
import bean.Room_info;
import bean.Teacher_info;
import bean.User;

public class UserDAOProxy implements UserDAO{
	private DataBaseConnection dbc = null; //定义数据库连接类
    private UserDAO userDAOImpl = null;    //声明DAO
 
    public UserDAOProxy() throws Exception {  //构造方法中实例化连接与实例化DAO对象
        this.dbc = new DataBaseConnection();  //连接数据库
        this.userDAOImpl = new UserDAOImpl(this.dbc.getConnection());//实例化真实类
     }
    
    
    public boolean isLogin(User user) throws Exception {
        boolean flag = false;  //首先要定义要返回的变量
        try {
            flag = userDAOImpl.isLogin(user); //调用真实主题操作   
        } catch (Exception e) {
            throw e;
        } finally {
            dbc.close(); //关闭数据库
        }
        return flag;
    }


	@Override
	public void insert(User user) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.insert(user);
		
	}


	@Override
	public void updata(User user) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.updata(user);
		
	}


	@Override
	public void delete(User user) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.delete(user);
		
	}


	@Override
	public User select(User user) throws Exception {
		// TODO 自动生成的方法存根
		return userDAOImpl.select(user);
	}


	@Override
	public void insert(Room_info room) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.insert(room);
		
	}


	@Override
	public void updata(Room_info room) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.updata(room);
	}


	@Override
	public void delete(Room_info room) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.delete(room);
	}


	@Override
	public Room_info[] select(Room_info room) throws Exception {
		// TODO 自动生成的方法存根
		return userDAOImpl.select(room);
	}

//操作课程
	@Override
	public void insert(Course_info course) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.insert(course);
		
	}


	@Override
	public void updata(Course_info course) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.updata(course);
	}


	@Override
	public void delete(Course_info course) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.delete(course);
	}


	@Override
	public Course_info[] select(Course_info course) throws Exception {
		// TODO 自动生成的方法存根
		return userDAOImpl.select(course);
	}

//教师管理
	@Override
	public void insert(Teacher_info teacher) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.insert(teacher);
	}


	@Override
	public void updata(Teacher_info teacher) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.updata(teacher);
	}


	@Override
	public void delete(Teacher_info teacher) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.delete(teacher);
	}


	@Override
	public Teacher_info[] select(Teacher_info teacher) throws Exception {
		// TODO 自动生成的方法存根
		return userDAOImpl.select(teacher);
	}


	@Override
	public void insert(Class_info cla) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.insert(cla);
	}


	@Override
	public void updata(Class_info cla) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.updata(cla);
	}


	@Override
	public void delete(Class_info cla) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.delete(cla);
	}


	@Override
	public Class_info[] select(Class_info cla) throws Exception {
		// TODO 自动生成的方法存根
		return userDAOImpl.select(cla);
	}


	@Override
	public void insert(Exp_item exp) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.insert(exp);
	}


	@Override
	public void updata(Exp_item exp) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.updata(exp);
	}


	@Override
	public void delete(Exp_item exp) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.delete(exp);
	}


	@Override
	public Exp_item[] select(Exp_item exp) throws Exception {
		// TODO 自动生成的方法存根
		return userDAOImpl.select(exp);
	}


	@Override
	public void insert(App_info app) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.insert(app);
	}


	@Override
	public void updata(App_info app) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.updata(app);
	}


	@Override
	public void delete(App_info app) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.delete(app);
	}


	@Override
	public App_info[] select(App_info app) throws Exception {
		// TODO 自动生成的方法存根
		return userDAOImpl.select(app);
	}


	@Override
	public void insert(Arrange_info arr) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.insert(arr);
	}


	@Override
	public void updata(Arrange_info arr) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.updata(arr);
	}


	@Override
	public void delete(Arrange_info arr) throws Exception {
		// TODO 自动生成的方法存根
		userDAOImpl.delete(arr);
	}


	@Override
	public Arrange_info[] select(Arrange_info arr) throws Exception {
		// TODO 自动生成的方法存根
		return userDAOImpl.select(arr);
	}
}
