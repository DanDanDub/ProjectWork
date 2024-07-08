package it.danilo.projectwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "it.danilo.projectwork")
@SpringBootApplication
public class ProjectWorkApplication {

	public static void main(String[] args) {
		Class<ProjectWorkApplication> classi = ProjectWorkApplication.class;
		SpringApplication.run(classi, args);
	}

}
