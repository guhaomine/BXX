package daouse;

import bean.*;

public interface UserDAO {
	//User
	public boolean isLogin (User user) throws Exception ;
    public void insert(User user) throws Exception;
    public void updata(User user) throws Exception;
    public void delete(User user) throws Exception;
    public User select(User user) throws Exception;
    //������Ϣ����
    public void insert(Room_info room) throws Exception;
    public void updata(Room_info room) throws Exception;
    public void delete(Room_info room) throws Exception;
    public Room_info[] select(Room_info room) throws Exception;
    //�γ̹���
    public void insert(Course_info course) throws Exception;
    public void updata(Course_info course) throws Exception;
    public void delete(Course_info course) throws Exception;
    public Course_info[] select(Course_info course) throws Exception;
    //��ʦ����
    public void insert(Teacher_info teacher) throws Exception;
    public void updata(Teacher_info teacher) throws Exception;
    public void delete(Teacher_info teacher) throws Exception;
    public Teacher_info[] select(Teacher_info teacher) throws Exception;
    //��ѧ�����
    public void insert(Class_info cla) throws Exception;
    public void updata(Class_info cla) throws Exception;
    public void delete(Class_info cla) throws Exception;
    public Class_info[] select(Class_info cla) throws Exception;
    //ʵ����Ŀ����
    public void insert(Exp_item exp) throws Exception;
    public void updata(Exp_item exp) throws Exception;
    public void delete(Exp_item exp) throws Exception;
    public Exp_item[] select(Exp_item exp) throws Exception;
    //�ſ�����
    public void insert(App_info app) throws Exception;
    public void updata(App_info app) throws Exception;
    public void delete(App_info app) throws Exception;
    public App_info[] select(App_info app) throws Exception;
    //�ſβ�ѯ
    public void insert(Arrange_info arr) throws Exception;
    public void updata(Arrange_info arr) throws Exception;
    public void delete(Arrange_info arr) throws Exception;
    public Arrange_info[] select(Arrange_info arr) throws Exception;
}
