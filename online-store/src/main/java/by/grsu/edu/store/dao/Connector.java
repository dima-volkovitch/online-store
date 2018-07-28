package by.grsu.edu.store.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	private static Connection connection;

	private static String URL;
	private static String USER;
	private static String PASSWORD;
	private static String DRIVER;

	private Connector() throws FileNotFoundException, IOException {
	}

	public static Connection getConnection()
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		if (connection == null) {
			USER = "root";
			URL = "jdbc:mysql://localhost:3306/online_store?useLegacyDatetimeCode=false&serverTimezone=UTC";
			PASSWORD = "root";
			DRIVER = "com.mysql.cj.jdbc.Driver";

			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		}
		return connection;
	}
}
