package likeLion.hackathon.team1.queryCube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QueryCubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryCubeApplication.class, args);
	}

}
