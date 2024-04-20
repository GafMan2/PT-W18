package wine.cellar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class WineCellarApplication {

	public static void main(String[] args) {
		SpringApplication.run(WineCellarApplication.class, args);
	}

	// You need to create a bean configuration that returns
	// an instance of PasswordEncoder to use the
	// BCryptPasswordEncoder.
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
