package servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import entity.Account;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		String logout = request.getParameter("logout");
		if (logout != null && logout.equals("true")){
			//session.removeAttribute("account");
			Enumeration<String> e = session.getAttributeNames();
			while (e.hasMoreElements()){
				session.removeAttribute(e.nextElement());
			}
			response.sendRedirect("home.jsp");
			return;
		}
		if (username != null && password != null) {
			AccountDao adao = new AccountDao();
			Account acc = adao.isValid(username, password);
			if (acc != null) {
				session.setAttribute("account", acc);
				response.sendRedirect("home.jsp");
				return;
			}
			session.setAttribute("login_error", "账号或密码错误");
			response.sendRedirect("home.jsp");
			return;
		}
		session.setAttribute("login_error", "登陆失败");
		response.sendRedirect("home.jsp");
		return;
	}


}
