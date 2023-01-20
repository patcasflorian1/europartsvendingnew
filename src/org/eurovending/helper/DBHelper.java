package org.eurovending.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {

	// Connection the database
			public Connection getConnection()  {
				Properties connectionsProps = new Properties();
				connectionsProps.put("user", "root");
				//connectionsProps.put("password", "electro-smart");
				connectionsProps.put("password", "*2Go9x}cZ_M}");
				//connectionsProps.put("password", "luca77dgl");
				String nameOfCompany = "eurovending_web";
				//String nameOfCompany = "magazin_eurovending";
				String createDataBase = "CREATE TABLE IF NOT EXISTS "+nameOfCompany;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					return DriverManager.getConnection("jdbc:mysql://localhost/"+nameOfCompany+"?createDatabaseIfNotExist=true",
							connectionsProps);
				} catch (SQLException |ClassNotFoundException e) {
					e.printStackTrace();

				}
				return null;
			}
		
		
		public void closeConnection(Connection conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

}

