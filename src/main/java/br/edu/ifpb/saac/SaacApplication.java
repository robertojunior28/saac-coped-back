package br.edu.ifpb.saac;

import br.edu.ifpb.saac.business.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAsync
@EnableWebMvc
public class SaacApplication implements WebMvcConfigurer, CommandLineRunner {

	@Autowired
	private RoleService roleService;

	
	public static void main(String[] args) {
		SpringApplication.run(SaacApplication.class, args);
		
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
	}

	@Override
	public void run(String... args) throws Exception {

		roleService.createDefaultValues();
	}
	
}