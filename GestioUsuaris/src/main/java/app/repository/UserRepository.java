package app.repository;

import java.beans.JavaBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.beans.User;

@Repository
@PropertySource("classpath:application.properties")
public class UserRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public UserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional
	public String save(User user) {
		jdbcTemplate.update("INSERT INTO tUsuaris (NIF,Cognom1,Cognom2,Nom,login,checked) VALUES (?,?,?,?,?,?)", 
				user.getNif(), 
				user.getCognom1(), 
				user.getCognom2(), 
				user.getNom(), 
				user.getLogin(),
				user.isChecked());
		
		return "\nREGISTRE OK!!\nDE L\'USUARI AMB NIF: " + user.getNif();
	}
	 
	 

}
