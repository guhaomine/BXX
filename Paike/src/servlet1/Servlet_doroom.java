package servlet1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Room_info;
import bean.Teacher_info;
import daouse.DAOFactory;
import daouse.UserDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Servlet_doroom
 */
@WebServlet("/Servlet_doroom")
public class Servlet_doroom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_doroom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        PrintWriter out=resp.getWriter();
        String json="{\"state\":\"SUC\",\"msg\":\"成功\"}";
		JSONObject jb=JSONObject.fromObject(json);
		int flag=Integer.parseInt(req.getParameter("flag"));
    	Room_info room=new Room_info();
    	Room_info[] rooms=null;
    	UserDAO userDAOProxy = null;
    	//bean
    	room.setDevice_capacity(req.getParameter("renshu"));
    	room.setDevice_num(req.getParameter("shuliang"));
    	room.setName(req.getParameter("name"));
    	
    	try {
			userDAOProxy = DAOFactory.getUserDAOInstance();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    	System.out.println(flag+"");
		if(flag==1)
			try {
				rooms=userDAOProxy.select(room);
				jb.put("len", rooms.length);
				session.setAttribute("rooms_len", rooms.length-1);
				session.setAttribute("rooms", rooms);
				System.out.println(rooms.length+"");
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==3)
    		try {
				userDAOProxy.delete(room);	
				rooms=userDAOProxy.select(room);
				session.setAttribute("rooms_len", rooms.length-1);
				session.setAttribute("rooms", rooms);
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==2)
    		try {
    			room.setRoom_no(Integer.parseInt(req.getParameter("num")));
				userDAOProxy.updata(room);
				rooms=userDAOProxy.select(room);
				session.setAttribute("rooms_len", rooms.length-1);
				session.setAttribute("rooms", rooms);
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==4)
    		try {
				userDAOProxy.insert(room);
				rooms=userDAOProxy.select(room);
				session.setAttribute("rooms_len", rooms.length-1);
				session.setAttribute("rooms", rooms);
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
