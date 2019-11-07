package servlet1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Course_info;
import bean.Room_info;
import daouse.DAOFactory;
import daouse.UserDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Servlet_docourse
 */
@WebServlet("/Servlet_docourse")
public class Servlet_docourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_docourse() {
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
    	Course_info course=new Course_info();
    	Course_info[] courses=null;
    	course.setCourse_exp_hour(req.getParameter("etime"));
    	course.setCourse_gredit(req.getParameter("gredit"));
    	course.setCourse_hour(req.getParameter("time"));
    	
    	course.setCourse_name(req.getParameter("name"));
    	UserDAO userDAOProxy = null;
    	try {
			userDAOProxy = DAOFactory.getUserDAOInstance();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    	System.out.println(flag+"");
		if(flag==1)
			try {
				courses=userDAOProxy.select(course);
				jb.put("len", courses.length);
				session.setAttribute("courses_len", courses.length-1);
				session.setAttribute("courses", courses);
				System.out.println(courses.length+"");
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==3)
    		try {
				userDAOProxy.delete(course);
				courses=userDAOProxy.select(course);
				session.setAttribute("courses_len", courses.length-1);
				session.setAttribute("courses", courses);
				out.println(jb);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==2)
    		try {
    			course.setCourse_no(Integer.parseInt(req.getParameter("num")));
				userDAOProxy.updata(course);
				courses=userDAOProxy.select(course);
				session.setAttribute("courses_len", courses.length-1);
				session.setAttribute("courses", courses);
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==4)
    		try {
				userDAOProxy.insert(course);
				courses=userDAOProxy.select(course);
				session.setAttribute("courses_len", courses.length-1);
				session.setAttribute("courses", courses);
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
