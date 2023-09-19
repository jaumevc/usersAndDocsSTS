package app.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;

public class UtilsLog {
	
	public static void escribirLog(String rutaArchivo, String mensaje) {

        Logger logger = Logger.getLogger("");
        FileHandler fh;

        try {
            fh = new FileHandler(rutaArchivo);
            logger.addHandler(fh);

            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.info(mensaje);
            
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void crearFitxer(String rutaArchivo, String missatge){
        File file = new File(rutaArchivo);
        if(file.exists()) {
        	try {
        		missatge = "\n"+missatge;
        	    Files.write(Paths.get(rutaArchivo), missatge.getBytes(), StandardOpenOption.APPEND);
        	}catch (IOException e) {
        		 e.printStackTrace();
        	}
        }else {
        	try (PrintWriter out = new PrintWriter(file)/*, StandardCharsets.UTF_8)*/){
              out.print(missatge);
          } catch (IOException e) {
              e.printStackTrace();
          }
        }
    }
}
