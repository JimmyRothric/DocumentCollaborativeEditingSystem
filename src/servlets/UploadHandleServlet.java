package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.ContributionDao;
import dao.ContributorDao;
import dao.DocumentDao;
import entity.Account;
import entity.Contribution;
import entity.Contributor;
import entity.Document;
import handler.FileHandle;

/**
 * Servlet implementation class UploadHandleServlet
 */
@WebServlet("/UploadHandleServlet")
public class UploadHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadHandleServlet() {
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
		HttpSession session = request.getSession();
		String docid = request.getParameter("docid");
		String function = request.getParameter("function");
		Account acc = (Account) session.getAttribute("account");
		//得到上传文件的保存目录
        String savePath = this.getServletContext().getRealPath("/upload");
        //上传时生成的临时文件保存目录
        String tempPath = this.getServletContext().getRealPath("/temp");
        System.out.println(savePath);
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdir();
        }
        
        //消息提示
        String message = "上传失败";
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            factory.setSizeThreshold(1024*100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
            //设置上传时生成的临时文件的保存目录
            factory.setRepository(tmpFile);
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //监听文件上传进度
            upload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                }
            });
             //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8"); 
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
            upload.setFileSizeMax(1024*1024);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
            upload.setSizeMax(1024*1024*10);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            System.out.println(list);
            for(FileItem item : list){
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //得到上传文件的扩展名
                    String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("上传的文件的扩展名是："+fileExtName);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //得到文件保存的名称
                    String saveFilename = filename;
                    //得到文件的保存目录
                    String realSavePath = FileHandle.makePath(saveFilename, savePath);
                    String storePath = realSavePath + "\\" + saveFilename;
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(storePath);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    //item.delete();
                    
                    if (function != null && function.equals("create")) {
                    	 
                    	 DocumentDao ddao = new DocumentDao();
                         Document doc = new Document(filename,storePath);
                         ddao.addDocument(doc);
                         ContributorDao cdao = new ContributorDao();
                         cdao.addContributor(new Contributor(acc.getAccountID(),doc.getDocumentID(),Contributor.AUTHORITY_DEGREE_POSSESSED));
                         message = "文件创建成功！";
                         response.sendRedirect("DocumentServlet?function=showMyFile");
                         return;
                    }
                    if (function != null && function.equals("update")) {
                    	if (docid != null) {
                    		DocumentDao ddao = new DocumentDao();
                    		Document old_doc = ddao.getDocumentByID(docid);
                    		System.out.println("docid=" + docid+"|");
                    		if (old_doc != null) {
                    			 ContributionDao cdao = new ContributionDao();
                    			 ArrayList<Contribution> ctbList = cdao.getALLContributionByDID(docid);
                    			 boolean flag = true;
                    			 for (Contribution c : ctbList) {
                    				 if (c.getState().equals(Contribution.STATE_WAITED)) {
                    					 flag = false;
                    					 break;
                    				 }
                    			 }
                    			 if (flag) {
                    				 Document doc = new Document(old_doc,filename,storePath);
                                	 ddao.updateDocument(old_doc, doc);
                                	 message = "文件更新成功！";
                    			 }else {
                    				 message = "必须先审核完所有贡献记录再更新文档";
                    			 }
                            	
                    		}
                    	}
                    }
                    if (function != null && function.equals("upload")) {
                    	if (docid != null) {
                    		System.out.println("upload");
                    		ContributionDao cdao = new ContributionDao();
                    		cdao.addContribution(new Contribution(docid,acc.getAccountID(),storePath,Contribution.STATE_WAITED));
                    		message = "文档上传成功！";
                    	}
                    }
                   
                }
            }
        }catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "单个文件超出最大值！！！");
//            request.getRequestDispatcher("/message.jsp").forward(request, response);
//            return;
        }catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
//            request.getRequestDispatcher("/message.jsp").forward(request, response);
//            return;
        }catch (Exception e) {
            message= e.getMessage();
            e.printStackTrace();
        }
        request.setAttribute("loc", "DocumentServlet?function=showdoc&docid=" + docid);
        request.setAttribute("message",message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
        
	}
	

	


}
