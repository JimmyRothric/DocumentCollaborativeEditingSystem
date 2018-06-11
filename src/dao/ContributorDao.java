package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Contributor;

public class ContributorDao extends BaseDao {

	public ContributorDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 添加编辑者
	 * @param contributor Contributor class实例
	 * @return 操作结果成功 or 失败
	 */
	public boolean addContributor(Contributor contributor) {
		String sql = "insert into Contributor values(?, ?, ?)";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, contributor.getAccountID());
			stmt.setString(2, contributor.getDocumentID());
			stmt.setString(3, contributor.getAuthority());
			stmt.executeUpdate();
			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 删除编辑者
	 * @param accid 账号ID
	 * @param docid 文档ID
	 * @return 操作结果成功 or 失败
	 */
	public boolean delContributor(String accid, String docid) {
		String sql = "delete from Contributor where Uid = ? and Did = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, accid);
			stmt.setString(2, docid);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 更改编辑者权限
	 * @param accid 账号ID
	 * @param docid 文档ID
	 * @param authority 权限
	 * @return 操作结果成功 or 失败
	 */
	public boolean changeAuthority(String accid, String docid, String authority) {
		String sql = "update Contributor set authority = ? where Uid = ? and Did = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, authority);
			stmt.setString(2, accid);
			stmt.setString(3, docid);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 按文档ID获取编辑者列表(任意权限)
	 * @param docid
	 * @return Contributor class实例列表
	 */
	public ArrayList<Contributor> getContributorsByDID(String docid) {
		ArrayList<Contributor> contributorList = new ArrayList<Contributor>();
		String sql = "select * from Contributor where Did = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, docid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String accountID = rs.getString(1);
				String documentID = rs.getString(2);
				String authority = rs.getString(3);
				contributorList.add(new Contributor(accountID, documentID, authority));
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contributorList;
	}
	
	/**
	 * 检验权限
	 * @param accid 账号ID
	 * @param docid 文档ID
	 * @return 权限("P", "E", "R")
	 */
	public String getAuthority(String accid, String docid) {
		String state = "";
		String sql = "select authority from Contributor where Uid = ? and Did = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, accid);
			stmt.setString(2, docid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				state = rs.getString(1);
			}
			stmt.close();
			con.close();
			return state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	
	public Contributor getPContributorByDID(String docid) {
		Contributor contributor = null;
		String sql = "select * from Contributor where Did = ? and authority = 'P'";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, docid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String accountID = rs.getString(1);
				String documentID = rs.getString(2);
				String authority = rs.getString(3);
				contributor = new Contributor(accountID, documentID, authority);
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contributor;
	}
	
	
}
