package data;

public class Contribution {
	private String accountID;
	private String documentID;
	private String contributionID;
	private String path;
	
	
	public Contribution() {
		
	}
	public Contribution(String accountID, String documentID, String contributionID, String path) {
		super();
		this.accountID = accountID;
		this.documentID = documentID;
		this.contributionID = contributionID;
		this.path = path;
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
	
	
	
}
