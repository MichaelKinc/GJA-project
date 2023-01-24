package cz.welli.letmein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class LetMeInApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetMeInApplication.class, args);
	}

}
