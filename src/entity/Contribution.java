package entity;

import java.util.Date;

public class Contribution {
	private String documentID;
	private String contributionID;
	private String accountID;
	private String path;
	private Date uploadDate;
	private String state;
	/**
	 * 审核状态:等待审核
	 */
	public static final String STATE_WAITED = "W";
	/**
	 * 审核状态:拒绝更改
	 */
	public static final String STATE_REJECT = "R";
	/**
	 * 审核状态:接收更改
	 */
	public static final String STATE_ACCEPT = "A";
	
	public Contribution() {
		
	}
	public Contribution(String documentID, String contributionID, String accountID, String path, Date uploadDate,
			String state) {
		super();
		this.documentID = documentID;
		this.contributionID = contributionID;
		this.accountID = accountID;
		this.path = path;
		this.uploadDate = uploadDate;
		this.state = state;
	}


	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getDocumentID() {
		return documentID;
	}
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	public String getContributionID() {
		return contributionID;
	}
	public void setContributionID(String contributionID) {
		this.contributionID = contributionID;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
