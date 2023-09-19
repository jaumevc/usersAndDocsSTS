package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexioBBDD {
	
	Connection conn=null;

	//connect to producció
//	private String connectionUrl = "jdbc:sqlserver://sec:1433;"//"jdbc:sqlserver://sec.diputaciolleida.es:1433;"//"jdbc:sqlserver://SEC:1433;"
//			+ "databaseName=documents;"
//			+ "user=addUser;"
//			+ "password=Jg.1a2b3c;"
//			+ "encrypt=false;"
//			+ "trustServerCertificate=false;";
	
	// connect to test
	 private String connectionUrl = "jdbc:sqlserver://localhost:1433;"
			 +	"database=demodb;"
			 +  "user=addUser;"
			 +  "password=Jv.1a2b3c;"
			 +  "encrypt=true;trustServerCertificate=true;";
	
	public String conectar() {
		String resposta="";
		try {
			conn=DriverManager.getConnection(connectionUrl);
						
			if (conn!=null) {
				resposta="conectat";
			}else{
				resposta="NO S\'HA POGUT CONECTAR A BBDD";//+ ds.getDatabaseName();//+nomBd;
			}
			
		}catch (SQLException e) {
			String missatge= e.getMessage();
			resposta="Hi ha una excepcio de tipus SQLException:\n"+missatge.replaceAll("\\.", ".\n");
		}
		
		return resposta;
	}
	
	public Connection getConn() {
		try {
			conn=DriverManager.getConnection(connectionUrl);
						
		}catch (SQLException e) {
			e.getStackTrace();
		}
		return conn;
	}
	
	public Connection getConnection(){
		return conn;
	}
	public void desconectar() throws SQLException{
		conn.close();
		conn=null;
	}

}
