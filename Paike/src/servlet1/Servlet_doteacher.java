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
 * Servlet implementation class Servlet_doteacher
 */
@WebServlet("/Servlet_doteacher")
public class Servlet_doteacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_doteacher() {
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
    	Teacher_info teacher=new Teacher_info();
		Teacher_info[] teachers=null;
    	UserDAO userDAOProxy = null;
    	//bean
    	teacher.setTeacher_name(req.getParameter("name"));
    	teacher.setTeacher_tell(req.getParameter("tel"));
    	teacher.setTeacher_pwd(req.getParameter("pwd"));
    	teacher.setTeacher_email(req.getParameter("email"));
    	
    	System.out.println(req.getParameter("name")+req.getParameter("tel"));
    	try {
			userDAOProxy = DAOFactory.getUserDAOInstance();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    	System.out.println(flag+"");
		if(flag==1)
			try {
				teachers=userDAOProxy.select(teacher);
				jb.put("len", teachers.length);
				session.setAttribute("len", teachers.length-1);
				session.setAttribute("teachers", teachers);
				System.out.println(teachers[0].getTeacher_no());
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==3)
    		try {
    			userDAOProxy.delete(teacher);
    			teachers=userDAOProxy.select(teacher);
				session.setAttribute("len", teachers.length-1);
				session.setAttribute("teachers", teachers);
    			out.println(jb);
    			out.flush();
					
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==2)
    		try {
    			teacher.setTeacher_no(Integer.parseInt(req.getParameter("num")));
				userDAOProxy.updata(teacher);
				teachers=userDAOProxy.select(teacher);
				session.setAttribute("len", teachers.length-1);
				session.setAttribute("teachers", teachers);
				out.println(jb);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==4)
    		try {
				userDAOProxy.insert(teacher);
				teachers=userDAOProxy.select(teacher);
				session.setAttribute("len", teachers.length-1);
				session.setAttribute("teachers", teachers);
				out.println(jb);
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
