package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContributorDao;
import entity.Contributor;

/**
 * Servlet implementation class ContributorServlet
 */
@WebServlet("/ContributorServlet")
public class ContributorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContributorServlet() {
        super();
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
		//doGet(request, response);
		String function = request.getParameter("function");
		String accid = request.getParameter("accid");
		String docid = request.getParameter("docid");
		if (function != null ) {
			if (function.equals("show")) {
				if (docid != null) {
					ContributorDao cdao = new ContributorDao();
					ArrayList<Contributor> ctbtrList = cdao.getContributorsByDID(docid);
					request.setAttribute("ctbtrList", ctbtrList);
					request.getRequestDispatcher("/editormanage.jsp").forward(request, response);
				    return;
				}
			}
			if (function.equals("change")) {
				//TODO
				return;
			}
			if (function.equals("del") && accid != null && docid != null) {
				ContributorDao cdao = new ContributorDao();
				cdao.delContributor(accid, docid);
				ArrayList<Contributor> ctbtrList = cdao.getContributorsByDID(docid);
				request.setAttribute("ctbtrList", ctbtrList);
				//request.getRequestDispatcher("/editormanage.jsp").forward(request, response);
				request.getRequestDispatcher("/doc.jsp").forward(request, response);
			    return;
			}
		}
		
		 request.setAttribute("message","出错");
	     request.getRequestDispatcher("/message.jsp").forward(request, response);
	     return;
	}

}
