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
 * Servlet implementation class MyInfoServlet
 */
@WebServlet("/MyInfoServlet")
public class MyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoServlet() {
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
		
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("account");
		String function = request.getParameter("function");
		
		if (function != null) {
			if (function.equals("updatePassword")) {
				String oldPassword = request.getParameter("password0");
				String newPassword = request.getParameter("password1");
				AccountDao dao = new AccountDao();
				boolean isOldPasswordCorrect = oldPassword.equals(acc.getPassword());
				if (isOldPasswordCorrect) {
					boolean success = dao.setPassword(acc.getAccountID(), newPassword);
					if (success) {
						request.setAttribute("message", "重置密码成功");
					} else {
						request.setAttribute("message", "重置密码失败");
					}
				} else {
					request.setAttribute("message", "原密码错误");
				}
				request.setAttribute("loc","profile.jsp");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}
			
			if (function.equals("updateName")) {
				String newName = request.getParameter("name");
			}
		}
		
		
	}

}
