package entity;

public class Invitation {
	private String documentID;
	private String senderID;
	private String receiverID;
	

	public Invitation(String documentID, String senderID, String receiverID) {
		super();
		this.documentID = documentID;
		this.senderID = senderID;
		this.receiverID = receiverID;
	}
	public String getSenderID() {
		return senderID;
	}
	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}
	public String getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}
	public String getDocumentID() {
		return documentID;
	}
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	
	
}
