package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import dao.ContributionDao;
import dao.ContributorDao;
import dao.DocumentDao;
import entity.Account;
import entity.Contribution;
import entity.Contributor;
import entity.Document;
import handler.CompareListUtils;
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
		if (function == null) {
			return;
		}
	//	String showdocBtn = request.getParameter("showdocBtn");
	//	if (showdocBtn != null) {
		if (function.equals("showdoc")) {
			String docID = request.getParameter("docid");
			//String docPath = request.getParameter("docPath");
			DocumentDao dao = new DocumentDao();
			ContributionDao ctdao = new ContributionDao();
			ContributorDao crdao = new ContributorDao();
			
			Document document = dao.getDocumentByID(docID);
			String docPath = document.getPath();
			System.out.println("123"+docPath);
			docPath = docPath.substring(docPath.lastIndexOf("\\upload")+1);
			
			ArrayList<Contribution> contributionList = new ArrayList<Contribution>();
			contributionList = ctdao.getALLContributionByDID(docID);
			
			ArrayList<Contributor> contributorList = new ArrayList<Contributor>();
			contributorList = crdao.getEContributorsByDID(docID);
			
			String owner = crdao.getPContributorByDID(docID).getAccountID();
			String authority = crdao.getAuthority(account.getAccountID(), docID);
			
			System.out.println(docPath);
			request.setAttribute("doc", document);
			request.setAttribute("src", docPath);
			request.setAttribute("owner", owner);
			request.setAttribute("contributionList", contributionList);
			request.setAttribute("contributorList", contributorList);
			request.setAttribute("authority", authority);
			request.getRequestDispatcher("/doc.jsp").forward(request, response);
			return;
		}

		
		if (function.equals("showMyFile")) {
			String accid = request.getParameter("accid");
			if (accid == null) {
				accid = account.getAccountID();
			}
			DocumentDao ddao = new DocumentDao();
			ArrayList<Document> docList = ddao.getPossessedDocumentByAID(accid);
			request.setAttribute("docList", docList);
			
			//redundant code
			ContributionDao cdao = new ContributionDao();
			ArrayList<ArrayList<Contribution>> ctbList = new ArrayList<ArrayList<Contribution>>();
			for (Document d :docList) {
				ctbList.add(cdao.getALLContributionByDID(d.getDocumentID()));
			}
			request.setAttribute("ctbList", ctbList);
			//
			
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
		if (function.equals("showContribution")) {
			String docid = request.getParameter("docid");
			ContributionDao cdao = new ContributionDao();
			ContributorDao ctdao = new ContributorDao();
			ArrayList<Contributor> ctorList = ctdao.getEContributorsByDID(docid);
			ArrayList<String> textList = new ArrayList<String>();
			ArrayList<Double> dataList = new ArrayList<Double>();
			ArrayList<String> colorList = new ArrayList<String>();
			for (Contributor c : ctorList) {
				textList.add("\""+c.getAccountID()+"\"");
				dataList.add((double) cdao.getContributionByAIDDID(c.getAccountID(), docid).size());
				colorList.add("\""+getRndColor()+"\"");
			}
			double sum = 0;
			for (Double d : dataList) {
				sum += d.doubleValue();
			}
			for (int i = 0; i < dataList.size(); i++) {
				dataList.set(i, dataList.get(i) / sum);
			}
		
			
			request.setAttribute("textList", textList);
			request.setAttribute("dataList", dataList);
			request.setAttribute("colorList", colorList);
			
			request.getRequestDispatcher("/contribution.jsp").forward(request, response);
			return;
			
		}
		if (function.equals("showRecord")) {
			String savePath = this.getServletContext().getRealPath("/upload");
			String docid = request.getParameter("docid");
			DocumentDao ddao = new DocumentDao();
			Document dnow = ddao.getDocumentByID(docid);
			ArrayList<Document> doldList = ddao.getALLDocumentHistory(docid);
			//ArrayList<ArrayList<Integer>> difAllList = new ArrayList<ArrayList<Integer>>();
		
			ArrayList<String> filePathList = new ArrayList<String>();
			ArrayList<String> diffPathList = new ArrayList<String>();
			Document dtmp = dnow;
			filePathList.add(dtmp.getPath().substring(dtmp.getPath().lastIndexOf("\\upload")+1));
			for (Document d : doldList) {
				filePathList.add(d.getPath().substring(d.getPath().lastIndexOf("\\upload")+1));
				//difAllList.add(FileHandle.compare(dtmp.getPath(), d.getPath()));
				String tpath = CompareListUtils.compare(savePath, dtmp.getPath(), d.getPath());
				diffPathList.add(tpath.substring(tpath.lastIndexOf("\\upload")+1));
				dtmp = d;
			}
			request.setAttribute("diffPathList", diffPathList);
			request.setAttribute("filePathList", filePathList);
			//request.setAttribute("difAllList", difAllList);
			request.getRequestDispatcher("/filerecord.jsp").forward(request, response);
			return;
		}
		
		
	}
	private String getRndColor() {
		//红色  
        String red;   
        //绿色  
        String green;  
        //蓝色  
        String blue;  
        //生成随机对象  
        Random random = new Random();    
        //生成红色颜色代码  
        red = Integer.toHexString(random.nextInt(256)).toUpperCase();  
        //生成绿色颜色代码  
        green = Integer.toHexString(random.nextInt(256)).toUpperCase();   
        //生成蓝色颜色代码  
        blue = Integer.toHexString(random.nextInt(256)).toUpperCase();    
             
        //判断红色代码的位数  
        red = red.length()==1 ? "0" + red : red ;    
        //判断绿色代码的位数  
        green = green.length()==1 ? "0" + green : green ;   
        //判断蓝色代码的位数  
        blue = blue.length()==1 ? "0" + blue : blue ;  
        //生成十六进制颜色值  
        String color = "#"+red+green+blue;  
          
        return color;  
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
