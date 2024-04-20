//package wine.cellar.config;

// This is commented out because I initially tried implementing a
// SecurityConfig file using a different Spring Boot 
// security package suite to handle username and password
// generation but ended up changing course and using a simpler 
// password encoder

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//public class SecurityConfig {
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}