package data;

public class Contributor {
	private String accountID;
	private String documentID;
	private String authority;
	
	public Contributor() {
		
	}
	public Contributor(String accountID, String documentID, String authority) {
		super();
		this.accountID = accountID;
		this.documentID = documentID;
		this.authority = authority;
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
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	
	
	
}
