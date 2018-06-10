package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ContributorDao;
import dao.InvitationDao;
import entity.Account;
import entity.Contributor;
import entity.Invitation;

/**
 * Servlet implementation class InvitationServlet
 */
@WebServlet("/InvitationServlet")
public class InvitationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvitationServlet() {
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
		//doGet(request, response);
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("account");
		String invBtn = request.getParameter("invBtn");
		String function = request.getParameter("function");
		
		try {
			if (function != null) {
				String sender_id = request.getParameter("sender_id");
				String receiver_id = request.getParameter("receiver_id");
				String document_id = request.getParameter("document_id");
				if (sender_id != null && receiver_id != null && document_id != null) {
					if (function.equals("accept")) {
						ContributorDao cdao = new ContributorDao();
						cdao.addContributor(new Contributor(receiver_id,document_id,Contributor.AUTHORITY_DEGREE_EDITABLE));
						//TODO 提示邀请接受成功
					}
					InvitationDao idao = new InvitationDao();
					idao.delInvitation(document_id, receiver_id);
					response.sendRedirect("home.jsp");
					return;
				}

				throw new Exception("参数错误或其他错误");
			}
			if (invBtn != null) {
				String recv_id = request.getParameter("recv_id");
				String doc_id = request.getParameter("doc_id");
				if (recv_id != null && doc_id != null && acc != null) {
					InvitationDao idao = new InvitationDao();
					if (idao.isExisted(doc_id, recv_id)) {
						request.setAttribute("loc","ContributorServlet?function=show&docid="+doc_id);
						request.setAttribute("message","已邀请该用户");
					    request.getRequestDispatcher("/message.jsp").forward(request, response);
					    return;
					}else if (idao.addInvitation(new Invitation(acc.getAccountID(),recv_id,doc_id))) {
						request.setAttribute("loc","ContributorServlet?function=show&docid="+doc_id);
						request.setAttribute("message","邀请成功");
					    request.getRequestDispatcher("/message.jsp").forward(request, response);
					    return;
					}
				}
				throw new Exception("参数错误或其他错误");
			}
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("loc","home.jsp");
			request.setAttribute("message",e.getMessage());
		    request.getRequestDispatcher("/message.jsp").forward(request, response);
		}

		
	}

}
