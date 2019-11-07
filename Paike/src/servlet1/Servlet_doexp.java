package servlet1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Exp_item;
import bean.Room_info;
import daouse.DAOFactory;
import daouse.UserDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Servlet_doexp
 */
@WebServlet("/Servlet_doexp")
public class Servlet_doexp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_doexp() {
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
    	Exp_item exp=new Exp_item();
    	Exp_item[] exps=null;
    	exp.setExp_item_hour(req.getParameter("time"));
    	exp.setExp_item_name(req.getParameter("name"));
    	
    	exp.setFk_course_no(req.getParameter("cnum"));
    	exp.setExp_type(req.getParameter("type"));
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
				exps=userDAOProxy.select(exp);
				jb.put("len", exps.length);
				session.setAttribute("exps_len", exps.length-1);
				session.setAttribute("exps", exps);
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==3)
    		try {
				userDAOProxy.delete(exp);
				exps=userDAOProxy.select(exp);
				session.setAttribute("exps_len", exps.length-1);
				session.setAttribute("exps", exps);
				out.println(jb);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==2)
    		try {
    			exp.setExp_item_no(Integer.parseInt(req.getParameter("num")));
				userDAOProxy.updata(exp);
				exps=userDAOProxy.select(exp);
				session.setAttribute("exps_len", exps.length-1);
				session.setAttribute("exps", exps);
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==4)
    		try {
				userDAOProxy.insert(exp);
				exps=userDAOProxy.select(exp);
				session.setAttribute("exps_len", exps.length-1);
				session.setAttribute("exps", exps);
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
