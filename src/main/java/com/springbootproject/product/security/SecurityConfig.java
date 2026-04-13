package com.springbootproject.product.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) //prePostEnabled yeh byDefault true Rheta hai
public class SecurityConfig {

    /*
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
     */


                  /* JWT */

    /*
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    // Security Filter Chain (Sabse Important Bean)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable()) // CSRF Disable (JWT stateless hai)
                .authorizeHttpRequests(request ->{  //authorizeHttpRequests -> kaun sa request allow and deny
                    request.requestMatchers( "/user/register", "/user/login").permitAll();
                    request.requestMatchers(HttpMethod.GET, "/api/**").permitAll(); // all get request allowed
                    request.anyRequest().authenticated(); // get request ke alva sari request authenticate ho gya hai
                }).authenticationProvider(authenticationProvider())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

//                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    // Password Encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();  //BCryptPasswordEncoder used for password hashing
    }

    // Authentication Provider Bean ( Authenticate karega ki user vaild hai ki nhi
    @Bean
    public AuthenticationProvider authenticationProvider (){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();  // DaoAuthenticationProvider-> databases se user valid hai ki nhi yhi Kaam krta hai
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    // Authentication Manager Bean (yhi andar se provider ko check karega )
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config ) throws Exception {
       // Agar username mil gaya aur password match ho gaya -> Success.
        // Agar username nahi mila ya password galat hai -> Exception throw karega.
        return config.getAuthenticationManager();





     */

          /* OAuth2*/

    // Password Encoder Bean
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();  //BCryptPasswordEncoder used for password hashing
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable()) //// disable for post , update delete without token allow
                .authorizeHttpRequests(request ->{  //authorizeHttpRequests -> kaun sa request allow and deny
                    request.requestMatchers(HttpMethod.GET, "/api/**").permitAll(); // all get request allowed
                    request.anyRequest().authenticated(); // get request ke alva sari request authenticate ho gya hai
                }).oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        ));
        return http.build();
    }


    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakRoleReader());
        return jwtAuthenticationConverter;
    }
}
