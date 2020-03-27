package br.com.caelum.contas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	
	public Connection getConnection() throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		
		//return DriverManager.getConnection("jdbc:hsqldb:file:contas.db","sa", "");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/schema1?useTimezone=true&serverTimezone=UTC","root", "mySql123");
	}

}
