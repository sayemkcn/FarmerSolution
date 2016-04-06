package farmer.solution.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"farmer.solution.controllers","farmer.solution.services"})
@EntityScan(basePackages="farmer.solution.entities")
@EnableJpaRepositories(basePackages="farmer.solution.repositories")
public class FarmerSolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmerSolutionApplication.class, args);
	}
}
