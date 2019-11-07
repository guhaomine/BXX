package servlet1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.App_info;
import bean.Exp_item;
import daouse.DAOFactory;
import daouse.UserDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Servlet_doinsert
 */
@WebServlet("/Servlet_doinsert")
public class Servlet_doinsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_doinsert() {
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
    	App_info app=new App_info();
    	App_info[] apps=null;
    	/*app.setExp_item_hour(req.getParameter("time"));
    	exp.setExp_item_name(req.getParameter("name"));
    	exp.setExp_item_no(req.getParameter("num"));
    	exp.setFk_course_no(req.getParameter("cnum"));
    	exp.setExp_type(req.getParameter("type"));*/
    
    	
    	UserDAO userDAOProxy = null;
    	try {
			userDAOProxy = DAOFactory.getUserDAOInstance();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(flag==1)
			try {
				apps=userDAOProxy.select(app);
				jb.put("len", apps.length);
				session.setAttribute("apps_len", apps.length-1);
				session.setAttribute("apps", apps);
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==3)
    		try {
    			app.setApp_no(Integer.parseInt(req.getParameter("apno")));
				userDAOProxy.delete(app);
				apps=userDAOProxy.select(app);
				session.setAttribute("apps_len", apps.length-1);
				session.setAttribute("apps", apps);
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==2)
    		try {
    			
				userDAOProxy.updata(app);
				apps=userDAOProxy.select(app);
				session.setAttribute("apps_len", apps.length-1);
				session.setAttribute("apps", apps);
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==4)
    		try {
    			app.setApp_term(req.getParameter("term"));
    	    	app.setDay(Integer.parseInt(req.getParameter("day")));
    	    	app.setFk_class_no(Integer.parseInt(req.getParameter("class_num")));
    	    	app.setFk_course_no(Integer.parseInt(req.getParameter("course_num")));
    	    	app.setApp_stu_num(Integer.parseInt(req.getParameter("exp_num")));
    	    	app.setCourse_idx(Integer.parseInt(req.getParameter("room_num")));
    	    	app.setFk_teacher_no(Integer.parseInt(req.getParameter("num")));
    	    	app.setJie(Integer.parseInt(req.getParameter("jie")));
    	    	app.setWeek(Integer.parseInt(req.getParameter("week")));
				userDAOProxy.insert(app);
				apps=userDAOProxy.select(app);
				session.setAttribute("apps_len", apps.length-1);
				session.setAttribute("apps", apps);
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
