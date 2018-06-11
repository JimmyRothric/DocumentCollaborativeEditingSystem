package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ContributionDao;
import dao.ContributorDao;
import dao.DocumentDao;
import entity.Account;
import entity.Contribution;
import entity.Document;
import handler.FileHandle;

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
		
		if (function.equals("showMyFile")) {
			DocumentDao ddao = new DocumentDao();
			ArrayList<Document> docList = ddao.getPossessedDocumentByAID(account.getAccountID());
			request.setAttribute("docList", docList);
			
			ContributionDao cdao = new ContributionDao();
			ArrayList<ArrayList<Contribution>> ctbList = new ArrayList<ArrayList<Contribution>>();
			for (Document d :docList) {
				ctbList.add(cdao.getALLContributionByDID(d.getDocumentID()));
			}
			request.setAttribute("ctbList", ctbList);
			request.getRequestDispatcher("/myfile.jsp").forward(request, response);
			return;
		}
		if (function.equals("teamFile")) {
			DocumentDao ddao = new DocumentDao();
			ArrayList<Document> docList = ddao.getEditableDocumentByAID(account.getAccountID());
			request.setAttribute("docList", docList);
			
			ContributionDao cdao = new ContributionDao();
			ContributorDao ctdao = new ContributorDao();
			ArrayList<String> nameList = new ArrayList<String>();
			ArrayList<ArrayList<Contribution>> ctbList = new ArrayList<ArrayList<Contribution>>();
			for (Document d :docList) {
				ctbList.add(cdao.getContributionByAIDDID(account.getAccountID(), d.getDocumentID()));
				nameList.add(ctdao.getPContributorByDID(d.getDocumentID()).getAccountID());
			}
			request.setAttribute("ctbList", ctbList);
			request.setAttribute("nameList", nameList);
			
	
			
			request.getRequestDispatcher("/teamfile.jsp").forward(request, response);
			return;
		}
		if (function.equals("showHistory")) {
			String docid = request.getParameter("docid");
			DocumentDao ddao = new DocumentDao();
			ArrayList<Document> hdocList = ddao.getALLDocumentHistory(docid);
			request.setAttribute("hdocList", hdocList);
			request.getRequestDispatcher("/historyfile.jsp").forward(request, response);
			return;
		}
		if (function.equals("rollback")) {
			String hdocid = request.getParameter("hdocid");
			String version = request.getParameter("version");
			int ver = Integer.parseInt(version);
			DocumentDao ddao = new DocumentDao();
			Document newd = ddao.getCertainDocumentHistory(hdocid, ver);
			Document oldd = ddao.getDocumentByID(hdocid);
			newd.setLastModifyDate(Calendar.getInstance().getTime());
			newd.setVersion(oldd.getVersion()+1);
			ddao.updateDocument(oldd, newd);
			response.sendRedirect("DocumentServlet?function=showMyFile");
			return;
		}
		if (function.equals("showRecord")) {
			String docid = request.getParameter("docid");
			DocumentDao ddao = new DocumentDao();
			Document dnow = ddao.getDocumentByID(docid);
			ArrayList<Document> doldList = ddao.getALLDocumentHistory(docid);
			ArrayList<ArrayList<Integer>> difAllList = new ArrayList<ArrayList<Integer>>();
			ArrayList<String> filePathList = new ArrayList<String>();
			Document dtmp = dnow;
			filePathList.add(dtmp.getPath().substring(dtmp.getPath().lastIndexOf("\\upload")+1));
			for (Document d : doldList) {
				filePathList.add(d.getPath().substring(d.getPath().lastIndexOf("\\upload")+1));
				difAllList.add(FileHandle.compare(dtmp.getPath(), d.getPath()));
				dtmp = d;
			}
			request.setAttribute("filePathList", filePathList);
			request.setAttribute("difAllList", difAllList);
			request.getRequestDispatcher("/filerecord.jsp").forward(request, response);
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
