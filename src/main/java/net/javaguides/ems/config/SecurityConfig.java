package net.javaguides.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
        .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults());

        return http.build();
    } // component based spring security style

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails kapil = User.builder()
                            .username("KApil")
                            .password(passwordEncoder().encode("KApil"))
                            .roles("USER")
                            .build();

        UserDetails admin = User.builder()
                            .username("Admin")
                            .password(passwordEncoder().encode("Admin"))
                            .roles("ADMIN")
                            .build();
        return new InMemoryUserDetailsManager(kapil, admin);
    }
}