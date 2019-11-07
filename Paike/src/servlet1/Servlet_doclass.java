package servlet1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Class_info;
import bean.Room_info;
import daouse.DAOFactory;
import daouse.UserDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Servlet_doclass
 */
@WebServlet("/Servlet_doclass")
public class Servlet_doclass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_doclass() {
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
    	Class_info cla=new Class_info();
    	Class_info[] clas=null;
    	cla.setClass_name(req.getParameter("cname"));
    	
    	cla.setMajior_name(req.getParameter("name"));
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
				clas=userDAOProxy.select(cla);
				jb.put("len", clas.length-1);
				session.setAttribute("clas_len", clas.length-1);
				session.setAttribute("clas", clas);
				System.out.println(clas.length+"");
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==3)
    		try {
				userDAOProxy.delete(cla);
				clas=userDAOProxy.select(cla);
				session.setAttribute("clas_len", clas.length-1);
				session.setAttribute("clas", clas);
				out.println(jb);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==2)
    		try {
    			cla.setClass_no(Integer.parseInt(req.getParameter("num")));
				userDAOProxy.updata(cla);	
				clas=userDAOProxy.select(cla);
				session.setAttribute("clas_len", clas.length-1);
				session.setAttribute("clas", clas);		
				out.println(jb);
				out.flush();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	if(flag==4)
    		try {
				userDAOProxy.insert(cla);	
				clas=userDAOProxy.select(cla);
				session.setAttribute("clas_len", clas.length-1);
				session.setAttribute("clas", clas);
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
