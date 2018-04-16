package data;

import java.util.Date;

public class Document {
	private String documentID;
	private String title;
	private String path;
	private Date createDate;
	private Date lastModifyDate;
	private int version;
	
	
	public Document() {
		
	}
	public Document(String id, String title, String path, Date createDate) {
		super();
		this.documentID = id;
		this.title = title;
		this.path = path;
		this.createDate = createDate;
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

}
