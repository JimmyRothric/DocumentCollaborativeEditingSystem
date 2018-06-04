package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import entity.Contributor;
import entity.Document;

public class DocumentDao extends BaseDao {
	public DocumentDao() {
		super();
	}
	/**
	 * 添加文档(仅第一次创建文档时调用)
	 * @param doc Document class文档实例
	 * @return 操作结果成功 or 失败
	 */
	public boolean addDocument(Document doc) {
		String sql = "insert into Document values(?, ?, ?, ?, ?, ?)";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, doc.getDocumentID());
			stmt.setString(2, doc.getTitle());
			stmt.setString(3, doc.getPath());
			stmt.setTimestamp(4, new Timestamp(doc.getCreateDate().getTime()));
			stmt.setTimestamp(5, new Timestamp(doc.getLastModifyDate().getTime()));
			stmt.setInt(6, doc.getVersion());
			stmt.executeUpdate();
			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 删除文档
	 * @param docid 删除的文档ID
	 * @return 操作结果成功 or 失败
	 */
	public boolean delDocument(String docid) {
		String sql = "delete from Document where Did = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, docid);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 更新文档
	 * add current document into document history table;
	 * delete it from document table;
	 * add new document into document table.
	 * 
	 * 将现存的记录插入Document-History表中，
	 * 将其从Document表中删除，
	 * 将更新后的文档插入Document表中。
	 * @param current_doc
	 * @return 操作结果成功 or 失败
	 */
	public boolean updateDocument(Document doc) {
		boolean success = false;
		String sql1 = "insert into Document_History values(?, ?, ?, ?, ?, ?)";
		String sql2 = "delete from Document where Did = ?";
		try {
			Connection con = super.getConnection();
			//sql 1		将原文档插入Document-History表中
			PreparedStatement stmt1 = con.prepareStatement(sql1);
			stmt1.setString(1, doc.getDocumentID());
			stmt1.setString(2, doc.getTitle());
			stmt1.setString(3, doc.getPath());
			stmt1.setTimestamp(4, new Timestamp(doc.getCreateDate().getTime()));
			stmt1.setTimestamp(5, new Timestamp(doc.getLastModifyDate().getTime()));
			stmt1.setInt(6, doc.getVersion());
			stmt1.executeUpdate();
			stmt1.close();
			//step 2	将原文档记录从Document表中删除
			PreparedStatement stmt2 = con.prepareStatement(sql2);
			stmt2.setString(1, doc.getDocumentID());
			stmt2.executeUpdate();
			stmt2.close();
			con.close();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (success) {
			//step 3	将更新后的文档插入Document表中
			addDocument(doc);
			return true;
		}
		return false;
	}
	
	/**
	 * 判断文档是否存在
	 * @param docid 文档ID
	 * @return 操作结果成功 or 失败
	 */
	public boolean isExist(String docid) {
		String sql = "select * from Document where Did = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, docid);
			ResultSet rs = stmt.executeQuery();
			boolean isExisted = false;
			if (rs.next()) {
				isExisted = true;
			}
			stmt.close();
			con.close();
			return isExisted;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 按文档ID获取目标文档实例
	 * @param docid 文档ID
	 * @return Document 实例
	 */
	public Document getDocumentByID(String docid) {
		Document doc = null;
		String sql = "select * from Document where Did = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, docid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString(1);
				String title = rs.getString(2);
				String path = rs.getString(3);
				Date create_date = rs.getDate(4);
				Date last_modify_date = rs.getDate(5);
				int version  = rs.getInt(6);
				doc = new Document(id, title, path, create_date, last_modify_date, version);
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	/**
	 * 按用户账号ID获取用户所有拥有的文档(只包含用户创建的文档，不包含仅有编辑权限的文档)
	 * @param accid 用户账号ID
	 * @return 文档列表
	 */
	public ArrayList<Document> getPossessedDocumentByAID(String accid) {
		ArrayList<Document> docList = new ArrayList<Document>();
		String authority = Contributor.AUTHORITY_DEGREE_POSSESSED;
		String sql = "select Document.Did, title, path, create_date, last_modify_date, version "
				+ "from Document, Contributor "
				+ "where Document.Did = Contributor.Did and Uid = ? and authority = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, accid);
			stmt.setString(2, authority);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String title = rs.getString(2);
				String path = rs.getString(3);
				Date create_date = rs.getDate(4);
				Date last_modify_date = rs.getDate(5);
				int version  = rs.getInt(6);
				docList.add(new Document(id, title, path, create_date, last_modify_date, version));
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docList;
	}
	
	/**
	 * 按用户账号ID获取用户所有有编辑权限的文档(仅包含被邀请编辑的文档，不包含用户创建的文档)
	 * @param accid 用户账号ID
	 * @return 文档列表
	 */
	public ArrayList<Document> getEditableDocumentByAID(String accid) {
		ArrayList<Document> docList = new ArrayList<Document>();
		String authority = Contributor.AUTHORITY_DEGREE_EDITABLE;
		String sql = "select Document.Did, title, path, create_date, last_modify_date, version "
				+ "from Document, Contributor "
				+ "where Document.Did = Contributor.Did and Uid = ? and authority = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, accid);
			stmt.setString(2, authority);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String title = rs.getString(2);
				String path = rs.getString(3);
				Date create_date = rs.getDate(4);
				Date last_modify_date = rs.getDate(5);
				int version  = rs.getInt(6);
				docList.add(new Document(id, title, path, create_date, last_modify_date, version));
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docList;
	}
	
	/**
	 * 按用户账号ID获取用户所有只读的文档
	 * @param accid 用户账号ID
	 * @return 文档列表
	 */
	public ArrayList<Document> getReadOnlyDocumentByAID(String accid) {
		ArrayList<Document> docList = new ArrayList<Document>();
		String authority = Contributor.AUTHORITY_DEGREE_READ_ONLY;
		String sql = "select Document.Did, title, path, create_date, last_modify_date, version "
				+ "from Document, Contributor "
				+ "where Document.Did = Contributor.Did and Uid = ? and authority = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, accid);
			stmt.setString(2, authority);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String title = rs.getString(2);
				String path = rs.getString(3);
				Date create_date = rs.getDate(4);
				Date last_modify_date = rs.getDate(5);
				int version  = rs.getInt(6);
				docList.add(new Document(id, title, path, create_date, last_modify_date, version));
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docList;
	}
	
	/**
	 * 获取某个历史版本文档实例
	 * @param docid 文档ID
	 * @param v 版本号
	 * @return 文档实例
	 */
	public Document getCertainDocumentHistory(String docid, int v) {
		Document doc = null;
		String sql = "select * from Document_History where Did = ? and version = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, docid);
			stmt.setInt(2, v);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString(1);
				String title = rs.getString(2);
				String path = rs.getString(3);
				Date create_date = rs.getDate(4);
				Date last_modify_date = rs.getDate(5);
				int version  = rs.getInt(6);
				doc = new Document(id, title, path, create_date, last_modify_date, version);
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	/**
	 * 获取全部历史版本文档
	 * @param docid 文档ID
	 * @return 文档实例列表
	 */
	public ArrayList<Document> getALLDocumentHistory(String docid) {
		ArrayList<Document> docList = new ArrayList<Document>();
		String sql = "select * from Document_History where Did = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, docid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String title = rs.getString(2);
				String path = rs.getString(3);
				Date create_date = rs.getDate(4);
				Date last_modify_date = rs.getDate(5);
				int version  = rs.getInt(6);
				docList.add(new Document(id, title, path, create_date, last_modify_date, version));
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docList;
	}
}
