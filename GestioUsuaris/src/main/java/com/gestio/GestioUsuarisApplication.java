package com.gestio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frames.FrameLogin;

@SpringBootApplication
public class GestioUsuarisApplication /*implements CommandLineRunner*/{

	private static Logger LOG = LoggerFactory.getLogger(GestioUsuarisApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(GestioUsuarisApplication.class, args);
		System.setProperty("java.awt.headless", "false");
		SwingUtilities.invokeLater(() -> {
			FrameLogin loginFrame = new FrameLogin();
			loginFrame.setVisible(true);
			LOG.info("Proves d'inici realitzades ok");
		});
	}

//	@Override
//	public void run(String... args) throws Exception {
//		LOG.warn("Hola Mon Jaume");
//		LOG.info("Proves");
//		try {
//			FrameLogin loginFrame = new FrameLogin();
//			loginFrame.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
