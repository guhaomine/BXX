package daouse;

public class DAOFactory {
	public static UserDAO getUserDAOInstance() throws Exception { //ȡ��DAO�ӿ�ʵ��
	       return new UserDAOProxy(); //ȡ�ô������ʵ��
	    }
}
