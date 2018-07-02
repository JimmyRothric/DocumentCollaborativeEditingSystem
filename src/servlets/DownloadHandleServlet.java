package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContributionDao;
import dao.DocumentDao;
import entity.Contribution;
import entity.Document;
import handler.FileHandle;

/**
 * Servlet implementation class DownLoadHandleServlet
 */
@WebServlet("/DownloadHandleServlet")
public class DownloadHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadHandleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// TODO
		String docid = request.getParameter("docid");
		String hdocid = request.getParameter("hdocid");
		String ctbid = request.getParameter("ctbid");
		String path = null;
		String filename = null;
		File file = null;
		if (docid != null) {
			DocumentDao ddao = new DocumentDao(); 
			Document d = ddao.getDocumentByID(docid);
			path = d.getPath();
			filename = d.getTitle();
			file = new File(path);
		}
		if (hdocid != null) {
			String index = request.getParameter("index");
			int i = Integer.parseInt(index);
			DocumentDao ddao = new DocumentDao(); 
			Document d = ddao.getALLDocumentHistory(hdocid).get(i);
			path = d.getPath();
			filename = d.getTitle();
			file = new File(path);
		}
		if (ctbid != null) {
			ContributionDao cdao = new ContributionDao();
			Contribution c = cdao.getContributionByCID(ctbid);
			String p = c.getPath();
			path = p;
			filename = p.substring(p.lastIndexOf("\\")+1);
			file = new File (path);
		}
		/*
		String fileName = request.getParameter("filename");  //23239283-92489-阿凡达.avi
        fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");
        //通过文件名找出文件的所在目录
        String path = FileHandle.findFileSavePathByFileName(fileName,fileSaveRootPath);
        //得到要下载的文件
        File file = new File(path + "\\" + fileName);
        */
        //如果文件不存在
		if (file == null) {
            request.setAttribute("message", "错误");
            request.setAttribute("loc", "DocumentServlet?function=showdoc&docid=" + docid);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
		}
        if(!file.exists()){
            request.setAttribute("message", "您要下载的资源不存在！！");
            request.setAttribute("loc", "DocumentServlet?function=showdoc&docid=" + docid);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        //处理文件名
        //String realname = fileName.substring(fileName.indexOf("_")+1);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(path);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
