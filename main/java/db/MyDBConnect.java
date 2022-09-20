package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDBConnect {

	public static Connection dbConnect() {

		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// step-2
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "dbuser", "dbuser");
			// if (conn != null) {
			// System.out.println("DB Connected Successfully..!");
			// } else {
			// System.out.println("DB Fail to connect...!");
			// }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}

		return conn;
	}
}
