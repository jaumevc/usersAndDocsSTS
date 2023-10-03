package app.gestio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import app.frames.FrameLogin;

@SpringBootApplication
public class GestioUsuarisApplication implements CommandLineRunner{

	private static Logger LOG = LoggerFactory.getLogger(GestioUsuarisApplication.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
//	@Autowired
//	UserRepository userRepo;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(GestioUsuarisApplication.class).headless(false).run(args);
//		SpringApplication.run(GestioUsuarisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		FrameLogin loginFrame = new FrameLogin(jdbcTemplate);
		loginFrame.setVisible(true);
		
//		System.setProperty("java.awt.headless", "false");
//		SwingUtilities.invokeLater(() -> {
//			FrameLogin loginFrame = new FrameLogin(jdbcTemplate);
//			loginFrame.setVisible(true);
//			LOG.info("Proves d'inici realitzades ok");
//		});
	}

//	@Override
//	public void run(String... args) throws Exception {
//
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
