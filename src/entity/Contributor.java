package entity;

import java.util.HashMap;

public class Contributor {
	private String accountID;
	private String documentID;
	private String authority;
	/**
	 * 编辑权限:拥有
	 */
	public static final String AUTHORITY_DEGREE_POSSESSED = "P";
	/**
	 * 编辑权限:可编辑
	 */
	public static final String AUTHORITY_DEGREE_EDITABLE = "E";
	/**
	 * 编辑权限:只读
	 */
	public static final String AUTHORITY_DEGREE_READ_ONLY = "R";
	
	public static HashMap<String,String> sMap = new HashMap<>();
	static {
		sMap.put("P", "所有者");
		sMap.put("E", "可编辑");
		sMap.put("R", "可查看");
	}
	
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
	public String getAuthorityStr() {
		return sMap.get(authority);
	}
	
	
	
	
	
}
