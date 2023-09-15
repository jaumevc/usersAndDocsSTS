package com.gestio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class GestioUsuarisApplication implements CommandLineRunner{

	private static Logger LOG = LoggerFactory.getLogger(GestioUsuarisApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(GestioUsuarisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hola des de diput");
		LOG.warn("Hola Mon Jaume");
		LOG.info("Proves");
	}

}
