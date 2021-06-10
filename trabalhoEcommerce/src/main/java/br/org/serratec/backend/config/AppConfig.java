package br.org.serratec.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//essa classe vai gerenciar a senha dos usuarios
@Configuration
public class AppConfig {
	
	@Bean
	public BCryptPasswordEncoder bCryptyPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
