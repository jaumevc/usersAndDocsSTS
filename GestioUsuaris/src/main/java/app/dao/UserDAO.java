package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import app.beans.User;

public class UserDAO {

	Connection connection = null;
	ConexioBBDD conexio = null;

	private String conectar() throws SQLException {
		conexio = new ConexioBBDD();
		String isConnect = conexio.conectar();
		if (isConnect.equals("conectat")) {
			connection = conexio.getConnection();
		} else {
			JOptionPane.showMessageDialog(null, isConnect, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return isConnect;
	}

	public String registrarUser(User user) throws SQLException {
		String insert = "";

		if (!conectar().equals("conectat")) {
			return "error";
		}

		String consulta = "INSERT INTO tUsuaris (NIF,Cognom1,Cognom2,Nom,login,checked)" + "  VALUES (?,?,?,?,?,?)";
		PreparedStatement preStatement = null;

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, user.getNif());
			preStatement.setString(2, user.getCognom1());
			preStatement.setString(3, user.getCognom2());
			preStatement.setString(4, user.getNom());
			preStatement.setString(5, user.getLogin());
			preStatement.setBoolean(6, user.isChecked());
			preStatement.execute();

			insert = "\nREGISTRE OK!!\nDE L\'USUARI AMB NIF: " + user.getNif();

		} catch (SQLException e) {
			insert = "ERROR!!.\n" + e.getMessage();
		} catch (Exception e) {
			insert = "ERROR!! \n" + e.getMessage();
		} finally {
			preStatement.close();
			connection.close();
			conexio.desconectar();
		}
		return insert;

	}

	public Map<String, String> getLoginByUser(Set<String> dnis) throws SQLException {
		Map<String, String> logins = new HashMap<>();

		if (!StringUtils.equals(conectar(),"conectat")) {
			return logins;
		}
		
		StringBuilder sb = new StringBuilder("");

		int i = 0;
		for (String dni : dnis) {
			if (i == 0)
				sb.append("'"+dni+"'");
			else
				sb.append(", " + "'"+dni+"'");
			i++;
		}

		String consulta = "SELECT NIF, login FROM tUsuaris WHERE NIF in ("+ sb +");";
		Statement stmnt= null;
		try {
			stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery(consulta);
			while(rs.next()){
				String nif = rs.getString("NIF");
				String login = rs.getString("login");
				logins.put(nif, login);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmnt.close();
			connection.close();
			conexio.desconectar();
		}

		return logins;
	}

	public String updateUser(User user) {
		return null;
	}

}
