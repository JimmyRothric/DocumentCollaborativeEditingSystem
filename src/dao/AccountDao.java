package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Account;

public class AccountDao extends BaseDao {
	public AccountDao() {
		// TODO Auto-generated constructor stub
		super();
	}
	/**
	 * 添加账号
	 * @param acc Account Class实例
	 * @return 操作结果成功 or 失败
	 */
	public boolean addAccount(Account acc) {
		String sql = "insert into Account values(?, ?, ?, ?)";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, acc.getAccountID());
			stmt.setString(2, acc.getPassword());
			stmt.setString(3, acc.getName());
			stmt.setString(4, acc.getEmail());
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
	 * 设置用户名
	 * @param uid 操作的对象账号ID
	 * @param name 将要更改的新用户名
	 * @return 操作结果成功 or 失败
	 */
	public boolean setName(String uid, String name) {
		String sql = "update Account set name = ? where Uid = ? ";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, uid);
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
	 * 设置密码
	 * @param uid 操作的对象账号ID
	 * @param password 将要更改的新密码
	 * @return 操作结果成功 or 失败
	 */
	public boolean setPassword(String uid,String password) {
		String sql = "update Account set password = ? where Uid = ? ";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, password);
			stmt.setString(2, uid);
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
	 * 删除账号
	 * @param uid 操作的对象账号ID
	 * @return 操作结果成功 or 失败
	 */
	public boolean delAccount(String uid) {
		String sql = "delete from Account where Uid = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, uid);
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
	 * 检验账号是否存在(注册操作时检验)
	 * @param uid 账号ID
	 * @return 账号是否存在 是 or 否
	 */	
	public boolean isExisted(String uid) {
		String sql = "select * from Account where Uid = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, uid);
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
	 * 检验登录ID与密码是否匹配
	 * @param uid 账号ID
	 * @param password 账号密码
	 * @return 该账号Account Class实例 or null
	 */
	public Account isValid(String uid,String password) {
		String sql = "select * from Account where Uid = ? and password = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, uid);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			Account acc = null;
			if (rs.next()) {
				acc = new Account(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4));
			}
			stmt.close();
			con.close();
			return acc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 按ID获取Name
	 * @param uid
	 * @return
	 */
	public String getAccount(String uid) {
		String name = null;
		String sql = "select name from Account where Uid = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, uid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
	
}
