package app.dao;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DocumentDAO {
	Connection conn = null;
	ConexioBBDD conexio = null;
	
	 private static final int BUFFER_SIZE = 4096;

	private void conectar() throws SQLException {
		conexio = new ConexioBBDD();
		this.conn = conexio.getConn();
	}

	public void saveCorrectFiles(File file, String usuari, String monthName, String year) throws SQLException, IOException {
		conectar();
		byte[] pdfData = new byte[(int) file.length()];
		
		DataInputStream dis = new DataInputStream(new FileInputStream(file));
		
		dis.readFully(pdfData);  // read from file into byte[] array
		dis.close();
		
		String consulta = "INSERT INTO tDocuments (fitxer,tipus,usuari,descripcio,AuditFields_InsertDate,AuditFields_UpdateDate,categoria)"
				+ " VALUES (?,?,?,?,?,?,?)";
		PreparedStatement preStatement = null;

		try {
			preStatement = conn.prepareStatement(consulta);
			preStatement.setBytes(1, pdfData);
			preStatement.setString(2, ".pdf");
			preStatement.setString(3, usuari);
			preStatement.setString(4, "Incidències Nòmina " + monthName + " " + year);
			preStatement.setTimestamp(5, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
			preStatement.setTimestamp(6, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
			preStatement.setInt(7, 5);
			preStatement.execute();

		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			preStatement.close();
			conn.close();
		}
	}
	
	public void gedPdfSaved() throws SQLException {
		conectar();
		String consulta = "Select fitxer from tDocuments where id= 110;";
		String filePath = "C:/Users/jvalls/Desktop/jaumet.pdf";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(consulta);
			
			while (rs.next()) {
				Blob blob = rs.getBlob("fitxer");
                InputStream inputStream = blob.getBinaryStream();
                OutputStream outputStream = new FileOutputStream(filePath);
                
                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
 
                inputStream.close();
                outputStream.close();
                System.out.println("File saved");
                
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			rs.close();
			st.close();
			conn.close();
		}
	}

}
