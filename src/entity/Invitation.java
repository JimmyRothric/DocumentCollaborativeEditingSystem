package entity;

public class Invitation {
	private String senderID;
	private String receiverID;
	private String documentID;
	public Invitation(String senderID, String receiverID, String documentID) {
		this.senderID = senderID;
		this.receiverID = receiverID;
		this.documentID = documentID;
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
