package is.hi.hbv501g.TheHopby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TheHopbyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheHopbyApplication.class, args);
	}

}
