package servlet1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import bean.Room_info;
import bean.User;
import daouse.DAOFactory;
import daouse.UserDAO;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet_dologin
 */
@WebServlet("/Servlet_dologin")
public class Servlet_dologin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_dologin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
   /* public User User(int flag,User user) {
    	UserDAO userDAOProxy = null;
    	try {
           userDAOProxy = DAOFactory.getUserDAOInstance();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    	if(flag==1)
    	try {
			userDAOProxy.updata(user);
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
    	if(flag==2)
        try {
            boolean fla = userDAOProxy.isLogin(user);
            if (fla) {
            	String a=userDAOProxy.select(user).getAd_name();
            	String tell=userDAOProxy.select(user).getAd_tell();
            	String email=userDAOProxy.select(user).getEmail();
            	System.out.println(a+","+tell+","+email);
                System.out.println("恭喜你链接成功了啦!");
            } else {
               System.out.println("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
		return user;
    }*/
    
  /* public Room_info[] room(int flag,Room_info room) {
    	Room_info[] roo=null;
    	UserDAO userDAOProxy = null;
    	try {
			userDAOProxy = DAOFactory.getUserDAOInstance();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    	if(flag==1)
			try {
				roo= userDAOProxy.select(room);	
				
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==2)
    		try {
				userDAOProxy.delete(room);	
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==3)
    		try {
				userDAOProxy.updata(room);	
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==4)
    		try {
				userDAOProxy.insert(room);	
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	return roo;
    }*/
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*request.setCharacterEncoding("utf-8");
		String user=request.getParameter("username");
		String password=request.getParameter("password");
		Connection conn=Connect.getConnection();
		try {
			Connect.test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(user+password);*/
		req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        int flag=Integer.parseInt(req.getParameter("flag"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();      
        user.setAd_user_name(username);
        user.setAd_user_pwd(password);
        PrintWriter out=resp.getWriter();
        String json="{\"state\":\"SUC\",\"msg\":\"成功\"}";
		JSONObject jb=JSONObject.fromObject(json);
        UserDAO userDAOProxy = null;
    	try {
           userDAOProxy = DAOFactory.getUserDAOInstance();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    	if(flag==2)
    	try {
			userDAOProxy.updata(user);
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
    	if(flag==5) {
    		System.out.println("123");
    		session.invalidate();
    		out.println(jb);
    	}
    	if(flag==1)
        try {
            boolean fla = userDAOProxy.isLogin(user);
            if(fla) {   	
          session.setAttribute("admin",userDAOProxy.select(user));
            }
            jb.put("flag", fla);
            out.println(jb);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    	out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
