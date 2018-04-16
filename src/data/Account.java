package data;

public class Account {
	private String accountID;
	private String password;
	private String name;
	//tempt attribute
	private String email;
	
	public Account() {
		
	}
	public Account(String accountID, String password, String name, String email) {
		super();
		this.accountID = accountID;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	public Account(String id, String password, String name) {
		super();
		this.accountID = id;
		this.password = password;
		this.name = name;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
