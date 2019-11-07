package daouse;

public class DAOFactory {
	public static UserDAO getUserDAOInstance() throws Exception { //取得DAO接口实例
	       return new UserDAOProxy(); //取得代理类的实例
	    }
}
