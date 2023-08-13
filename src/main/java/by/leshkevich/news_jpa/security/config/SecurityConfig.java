package by.leshkevich.news_jpa.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/add/**").hasRole("ADMIN")
                .antMatchers("/news/home", "/user/auth", "/user/reg", "/images/**").permitAll()
                .anyRequest().hasAnyRole("ADMIN","USER","AUTHOR")
                .and()
                .formLogin().loginPage("/user/auth")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/news/home", true)
                .failureUrl("/user/auth?error")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/news/home")
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
