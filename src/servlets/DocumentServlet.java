package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DocumentDao;
import entity.Account;
import entity.Document;

/**
 * Servlet implementation class DocumentServlet
 */
@WebServlet("/DocumentServlet")
public class DocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocumentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String function = request.getParameter("function");
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account"); 
		if (account == null) {
			 request.setAttribute("message","请先登录");
		     request.getRequestDispatcher("/message.jsp").forward(request, response);
		     return;
		}
		String showdocBtn = request.getParameter("showdocBtn");
		if (showdocBtn != null) {
			String docPath = request.getParameter("docPath");
			System.out.println(docPath);
			request.setAttribute("src", docPath);
			request.getRequestDispatcher("/doc.jsp").forward(request, response);
			return;
		}
		if (function == null) {
			return;
		}
		ArrayList<Document> docList = null;
		if (function.equals("showMyFile")) {
			DocumentDao ddao = new DocumentDao();
			docList = ddao.getPossessedDocumentByAID(account.getAccountID());
			request.setAttribute("docList", docList);
			request.getRequestDispatcher("/myfile.jsp").forward(request, response);
			return;
		}
		if (function.equals("teamFile")) {
			DocumentDao ddao = new DocumentDao();
			docList = ddao.getEditableDocumentByAID(account.getAccountID());
			request.setAttribute("docList", docList);
			request.getRequestDispatcher("/teamfile.jsp").forward(request, response);
			return;
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
