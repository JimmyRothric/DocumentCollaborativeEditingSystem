package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import entity.Contribution;
import entity.Contributor;
import entity.Document;

public class ContributionDao extends BaseDao {

	public ContributionDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 添加更新记录
	 * @param contribution Contribution class 实例
	 * @return 操作结果成功 or 失败
	 */
	public boolean addContribution(Contribution contribution) {
		String sql = "insert into Contribution values(?, ?, ?, ?, ?, ?)";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, contribution.getDocumentID());
			stmt.setString(2, contribution.getContributionID());
			stmt.setString(3, contribution.getAccountID());
			stmt.setString(4, contribution.getPath());
			stmt.setTimestamp(5, new Timestamp(contribution.getUploadDate().getTime()));
			stmt.setString(6, contribution.getState());
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
	 * 删除更新记录
	 * @param contributionid 更新记录ID
	 * @return 操作结果成功 or 失败
	 */
	public boolean delContribution(String contributionid) {
		String sql = "delete from Contribution where Cid = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, contributionid);
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
	 * 按文档ID获取所有更新记录实例
	 * @param docid 文档ID
	 * @return 更新记录列表
	 */
	public ArrayList<Contribution> getALLContributionByDID(String docid){
		ArrayList<Contribution> contributionList = new ArrayList<Contribution>();
		String sql = "select * from Contribution where Did = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, docid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String documentid = rs.getString(1);
				String contribution = rs.getString(2);
				String accountid = rs.getString(3);
				String path = rs.getString(4);
				Date upload_date = rs.getDate(5);
				String state  = rs.getString(6);
				contributionList.add(new Contribution(documentid, contribution, accountid, path, upload_date, state));
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contributionList;
	}
	
	/**
	 * 按编辑者ID获取所有更新记录实例
	 * @param accid 账号ID
	 * @return 更新记录列表
	 */
	public ArrayList<Contribution> getContributionByAID(String accid) {
		ArrayList<Contribution> contributionList = new ArrayList<Contribution>();
		String sql = "select * from Contribution where Uid = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, accid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String documentid = rs.getString(1);
				String contribution = rs.getString(2);
				String accountid = rs.getString(3);
				String path = rs.getString(4);
				Date upload_date = rs.getDate(5);
				String state  = rs.getString(6);
				contributionList.add(new Contribution(documentid, contribution, accountid, path, upload_date, state));
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contributionList;
	}
	
	/**
	 * 更改已上传的更新记录审核状态(是否接收修改文档)
	 * @param contributionid 更新记录ID
	 * @param state 状态
	 * @return 操作结果成功 or 失败
	 */
	public boolean changeState(String contributionid, String state) {
		String sql = "update Contribution set state = ? where Cid = ? ";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, state);
			stmt.setString(2, contributionid);
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

}
