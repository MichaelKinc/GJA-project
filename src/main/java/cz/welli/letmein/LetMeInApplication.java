package cz.welli.letmein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class })
public class LetMeInApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetMeInApplication.class, args);
	}

}
