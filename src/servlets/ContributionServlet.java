package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.ContributionDao;
import entity.Contribution;

/**
 * Servlet implementation class ContributionServlet
 */
@WebServlet("/ContributionServlet")
public class ContributionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContributionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String function = request.getParameter("function");
		String ctbid = request.getParameter("ctbid");
		if (function != null && ctbid != null) {
			if (function.equals("pass")) {
				ContributionDao cdao = new ContributionDao();
				cdao.changeState(ctbid, Contribution.STATE_ACCEPT);
				
//				response.sendRedirect("DocumentServlet?function=showMyFile");
//				return;
			}
			if (function.equals("notpass")) {
				ContributionDao cdao = new ContributionDao();
				cdao.changeState(ctbid, Contribution.STATE_REJECT);
//				response.sendRedirect("DocumentServlet?function=showMyFile");
//				return;
			}
			if (function.equals("cancel")) {
				ContributionDao cdao = new ContributionDao();
				cdao.delContribution(ctbid);
//				response.sendRedirect("DocumentServlet?function=teamFile");
//				return;
			}
			session.setAttribute("page", "commit");
			response.sendRedirect("DocumentServlet?function=showdoc");
			return;
		}
	}

}
