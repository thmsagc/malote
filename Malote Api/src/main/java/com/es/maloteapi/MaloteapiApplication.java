package com.es.maloteapi;

import com.es.maloteapi.entity.Usuario;
import com.es.maloteapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class MaloteapiApplication {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void cadastrarAdmin(){

		String username = "admin";
		String password = "admin";

		Usuario consulta = userRepository.findByUsername(username).orElse(null);
		if(consulta == null)
			userRepository.save(new Usuario(username, password));
	}

	public static void main(String[] args) {
		SpringApplication.run(MaloteapiApplication.class, args);
	}

}
