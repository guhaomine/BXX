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
	private DataBaseConnection dbc = null; //�������ݿ�������
    private UserDAO userDAOImpl = null;    //����DAO
 
    public UserDAOProxy() throws Exception {  //���췽����ʵ����������ʵ����DAO����
        this.dbc = new DataBaseConnection();  //�������ݿ�
        this.userDAOImpl = new UserDAOImpl(this.dbc.getConnection());//ʵ������ʵ��
     }
    
    
    public boolean isLogin(User user) throws Exception {
        boolean flag = false;  //����Ҫ����Ҫ���صı���
        try {
            flag = userDAOImpl.isLogin(user); //������ʵ�������   
        } catch (Exception e) {
            throw e;
        } finally {
            dbc.close(); //�ر����ݿ�
        }
        return flag;
    }


	@Override
	public void insert(User user) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.insert(user);
		
	}


	@Override
	public void updata(User user) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.updata(user);
		
	}


	@Override
	public void delete(User user) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.delete(user);
		
	}


	@Override
	public User select(User user) throws Exception {
		// TODO �Զ����ɵķ������
		return userDAOImpl.select(user);
	}


	@Override
	public void insert(Room_info room) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.insert(room);
		
	}


	@Override
	public void updata(Room_info room) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.updata(room);
	}


	@Override
	public void delete(Room_info room) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.delete(room);
	}


	@Override
	public Room_info[] select(Room_info room) throws Exception {
		// TODO �Զ����ɵķ������
		return userDAOImpl.select(room);
	}

//�����γ�
	@Override
	public void insert(Course_info course) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.insert(course);
		
	}


	@Override
	public void updata(Course_info course) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.updata(course);
	}


	@Override
	public void delete(Course_info course) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.delete(course);
	}


	@Override
	public Course_info[] select(Course_info course) throws Exception {
		// TODO �Զ����ɵķ������
		return userDAOImpl.select(course);
	}

//��ʦ����
	@Override
	public void insert(Teacher_info teacher) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.insert(teacher);
	}


	@Override
	public void updata(Teacher_info teacher) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.updata(teacher);
	}


	@Override
	public void delete(Teacher_info teacher) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.delete(teacher);
	}


	@Override
	public Teacher_info[] select(Teacher_info teacher) throws Exception {
		// TODO �Զ����ɵķ������
		return userDAOImpl.select(teacher);
	}


	@Override
	public void insert(Class_info cla) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.insert(cla);
	}


	@Override
	public void updata(Class_info cla) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.updata(cla);
	}


	@Override
	public void delete(Class_info cla) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.delete(cla);
	}


	@Override
	public Class_info[] select(Class_info cla) throws Exception {
		// TODO �Զ����ɵķ������
		return userDAOImpl.select(cla);
	}


	@Override
	public void insert(Exp_item exp) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.insert(exp);
	}


	@Override
	public void updata(Exp_item exp) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.updata(exp);
	}


	@Override
	public void delete(Exp_item exp) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.delete(exp);
	}


	@Override
	public Exp_item[] select(Exp_item exp) throws Exception {
		// TODO �Զ����ɵķ������
		return userDAOImpl.select(exp);
	}


	@Override
	public void insert(App_info app) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.insert(app);
	}


	@Override
	public void updata(App_info app) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.updata(app);
	}


	@Override
	public void delete(App_info app) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.delete(app);
	}


	@Override
	public App_info[] select(App_info app) throws Exception {
		// TODO �Զ����ɵķ������
		return userDAOImpl.select(app);
	}


	@Override
	public void insert(Arrange_info arr) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.insert(arr);
	}


	@Override
	public void updata(Arrange_info arr) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.updata(arr);
	}


	@Override
	public void delete(Arrange_info arr) throws Exception {
		// TODO �Զ����ɵķ������
		userDAOImpl.delete(arr);
	}


	@Override
	public Arrange_info[] select(Arrange_info arr) throws Exception {
		// TODO �Զ����ɵķ������
		return userDAOImpl.select(arr);
	}
}
