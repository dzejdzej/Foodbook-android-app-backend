package foodbook.android.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/*@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)*/
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}    