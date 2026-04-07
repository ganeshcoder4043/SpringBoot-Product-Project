package com.springbootproject.product.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) //prePostEnabled yeh byDefault true Rheta hai
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable()) //// disable for post , update delete without token allow
                .authorizeHttpRequests(request ->{  //authorizeHttpRequests -> kaun sa request allow and deny
                    request.requestMatchers(HttpMethod.GET, "/api/**").permitAll(); // all get request allowed
                    request.anyRequest().authenticated(); // get request ke alva sari request authenticate ho gya hai
                }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();  //BCryptPasswordEncoder used for password hashing
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.builder()  // user define for admin
                .username("admin")
                .password(passwordEncoder().encode("admin"))  // admin -> encode -> jhd#bd@k&$kjfsh
                .roles("ADMIN")
                .build();

        UserDetails seller = User.builder()  // user define for seller
                .username("seller")
                .password(passwordEncoder().encode("seller"))  // seller -> encode -> jhd#bd@k&$kjfsh
                .roles("SELLER")
                .build();
        return new InMemoryUserDetailsManager(admin, seller);  // password saved/store  in memory not in db because InMemoryUserDetailsManager -> It's for testing and demo.
    }

}
