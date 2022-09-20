package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.MyDBConnect;
import model.OBSUser;

public class UserDao {
	public OBSUser login(String username, String password) {
		Connection conn = MyDBConnect.dbConnect();
		OBSUser user = null;

		try {
			PreparedStatement ps = conn.prepareStatement("select * from obsuser where USER_ID=? and USER_PWD=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new OBSUser(rs.getString(1), rs.getString(2), rs.getString(3));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public int registerCustomer(OBSUser user) {
		Connection conn = MyDBConnect.dbConnect();
		int registerCount = 0;
		try {

			PreparedStatement updatebook = conn.prepareStatement("insert into obsuser values(?,?,'customer')");
			updatebook.setString(1, user.getUser_id());
			updatebook.setString(2, user.getUser_pwd());

			registerCount = updatebook.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registerCount;
	}

	public int checkUnique(OBSUser user) {
		Connection conn = MyDBConnect.dbConnect();
		int checkUniquecount = 0;

		try {

			PreparedStatement checkUnique = conn.prepareStatement("select * from obsuser where user_id=?");
			checkUnique.setString(1, user.getUser_id());

			ResultSet checkUniqueid = checkUnique.executeQuery();

			if (checkUniqueid.next()) {
				checkUniquecount += 1;
			}
		} catch (SQLException e) {

		}
		return checkUniquecount;

	}
}