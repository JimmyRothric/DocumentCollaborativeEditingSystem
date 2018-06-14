package handler;

public class CompareMo {
	private int id;
	private String oldLine;
	private String newLine;
	private String tag;
	public CompareMo(int id, String oldLine, String newLine, String tag) {
		super();
		this.id = id;
		this.oldLine = oldLine;
		this.newLine = newLine;
		this.tag = tag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOldLine() {
		return oldLine;
	}
	public void setOldLine(String oldLine) {
		this.oldLine = oldLine;
	}
	public String getNewLine() {
		return newLine;
	}
	public void setNewLine(String newLine) {
		this.newLine = newLine;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
