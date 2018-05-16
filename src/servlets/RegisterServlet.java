package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import data.Account;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password0 = request.getParameter("password0");
		String password1 = request.getParameter("password1");
		HttpSession session = request.getSession();
		
		if (username != null && password0 != null && password1 != null) {
			if (true) { //修改判断正则
				if (password1.equals(password0)) {
					AccountDao adao = new AccountDao();
					adao.addAccount(new Account(username,password0,"未命名"));
					response.sendRedirect("login.jsp");
					return;
				}
				session.setAttribute("register_error", "两次密码不一致");
				response.sendRedirect("register.jsp");
				return;
			}
			session.setAttribute("register_error", "账号或密码格式错误");
			response.sendRedirect("register.jsp");
			return;
		}
		session.setAttribute("register_error", "注册错误");
		response.sendRedirect("register.jsp");
		return;
	}

}
