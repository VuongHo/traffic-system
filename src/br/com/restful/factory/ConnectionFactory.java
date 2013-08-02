package br.com.restful.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionFactory {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/demo_traffic";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public Connection createConnection(){
		
		Connection connect = null;
		
		try {
			
			Class.forName(DRIVER);
			connect = DriverManager.getConnection(URL, USER, PASSWORD);
			
		} catch (Exception e) {
			System.out.println("An error occurred while connecting to database: " + URL);
			e.printStackTrace();
		}
		return connect;
	}
	
	
	public void disConnect(Connection connect, PreparedStatement pstmt, ResultSet rs){
		
		try {
			
			if(connect != null){
				connect.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(rs != null){
				rs.close();
			}
					
		} catch (Exception e) {
			System.out.println("An error occurred while connecting to database: " + URL);
		}
	}
}

