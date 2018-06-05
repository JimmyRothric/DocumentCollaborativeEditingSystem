package entity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import handler.FileHandle;

public class Document {
	private String documentID;
	private String title;
	private String path;
	private Date createDate;
	private Date lastModifyDate;
	private int version;
	
	
	public Document() {
		
	}
	
	public Document(String documentID, String title, String path, Date createDate, Date lastModifyDate, int version) {
		super();
		this.documentID = documentID;
		this.title = title;
		this.path = path;
		this.createDate = createDate;
		this.lastModifyDate = lastModifyDate;
		this.version = version;
	}

	public Document(Document old_doc,String title,String path) {
		super();
		Date tdate = Calendar.getInstance().getTime();
		this.documentID = old_doc.documentID;
		this.title = title;
		this.path = path;
		this.createDate = old_doc.createDate;
		this.lastModifyDate = tdate;
		this.version = old_doc.version + 1;
	}

	public Document(String title,String path) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		DecimalFormat df = new DecimalFormat("000"); 
		Date tdate = Calendar.getInstance().getTime();
		this.documentID = sdf.format(tdate) + df.format(this.hashCode() % 100);
		this.title = title;
		this.path = path;
		this.createDate = tdate;
		this.lastModifyDate = tdate;
		this.version = 1;
	}
	public String getDocumentID() {
		return documentID;
	}
	public void setDocumentID(String id) {
		this.documentID = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String replace() {
		String str = path.substring(path.lastIndexOf("\\upload")+1);
		return str.replaceAll("\\\\","%");
	}

	

}
