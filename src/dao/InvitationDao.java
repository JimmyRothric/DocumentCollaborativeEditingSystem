package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Invitation;

public class InvitationDao extends BaseDao {

	public InvitationDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 添加邀请
	 * 1.需要由调用该方法的类检验 该文档是否为邀请发送者所拥有
	 * 2.需要由调用该方法的类检验 在Invitation表中是否已有同样的邀请记录(使用此类的isExisted方法)
	 * 3.需要由调用该方法的类检验 被邀请人是否已存在Contributor表中
	 * @param invitation Invitation class实例
	 * @return 操作结果成功 or 失败
	 */
	public boolean addInvitation(Invitation invitation) {
		String sql = "insert into Invitation values(?, ?, ?)";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, invitation.getDocumentID());
			stmt.setString(2, invitation.getSenderID());
			stmt.setString(3, invitation.getReceiverID());
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
	 * 删除邀请
	 * @param docid 文档ID
	 * @param receiverid 接收者账号ID
	 * @return 操作结果成功 or 失败
	 */
	public boolean delInvitation(String docid, String receiverid) {
		String sql = "delete from Invitation where Did = ? and Receiver_id = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, docid);
			stmt.setString(2, receiverid);
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
	 * 检验邀请是否存在
	 * @param docid 文档ID
	 * @param receiverid 接收者账号ID
	 * @return 存在 or 不存在
	 */
	public boolean isExisted(String docid, String receiverid) {
		String sql = "select * from Invitation where Did = ? and Receiver_id = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, docid);
			stmt.setString(2, receiverid);
			ResultSet rs = stmt.executeQuery();
			boolean isExisted = false;
			if (rs.next()) {
				isExisted = true;
			}
			stmt.close();
			con.close();
			return isExisted;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 获取发送的邀请实例
	 * @param senderid 发送者账号ID
	 * @return 所有发送的邀请列表
	 */
	public ArrayList<Invitation> getInvitationofSender(String senderid) {
		ArrayList<Invitation> invitationList = new ArrayList<Invitation>();
		String sql = "select * from Invitation where Sender_id = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, senderid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String docid = rs.getString(1);
				String sid = rs.getString(2);
				String rid = rs.getString(3);
				invitationList.add(new Invitation(docid, sid, rid));
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invitationList;
	}
	
	/**
	 * 获取接收的邀请实例
	 * @param receiverid 接收者账号ID
	 * @return 所有收到的邀请列表
	 */
	public ArrayList<Invitation> getInvitationofReceiver(String receiverid) {
		ArrayList<Invitation> invitationList = new ArrayList<Invitation>();
		String sql = "select * from Invitation where Receiver_id = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, receiverid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String docid = rs.getString(1);
				String sid = rs.getString(2);
				String rid = rs.getString(3);
				invitationList.add(new Invitation(docid, sid, rid));
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invitationList;
	}
	
	public int getCountInvitationofReceiver(String receiverid) {
		String sql = "select count(*) from Invitation where Receiver_id = ?";
		int cnt = 0;
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, receiverid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
			stmt.close();
			con.close();
			return cnt;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
