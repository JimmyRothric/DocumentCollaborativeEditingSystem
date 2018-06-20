package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import entity.Account;

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
		String id = request.getParameter("accountid");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password0");
		HttpSession session = request.getSession();
		boolean success = false;
		boolean isExisted = false;
		if (id != null && email != null && name != null && password != null ) {
			AccountDao adao = new AccountDao();
			isExisted = adao.isExisted(id);
			if (isExisted) {
				session.setAttribute("register_error", "该用户已存在");
				response.sendRedirect("home.jsp");
				return;
			} else {
				success = adao.addAccount(new Account(id, password, name, email));
			}
			if (success) {
				response.sendRedirect("home.jsp");
				return;
			} else {
				session.setAttribute("register_error", "注册失败");
				response.sendRedirect("home.jsp");
				return;
			}
		}
	}

}
