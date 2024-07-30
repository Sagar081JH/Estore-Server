package com.estore.spring_security_config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

//    @Autowired
//    DataSource dataSource;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> auth
//                .requestMatchers("/login","/register","/products").permitAll()
//                .anyRequest().authenticated());
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        //http.formLogin(Customizer.withDefaults());
//
//        http.httpBasic(Customizer.withDefaults());
//        http.csrf(AbstractHttpConfigurer::disable);
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        //.password("{noop}password1") : for demos to store pwd as it is
////        UserDetails user1= User.withUsername("user2")
////                .password(passwordEncoder().encode("userPass"))
////                .roles("USER")
////                .build();
////        UserDetails admin1= User.withUsername("admin2")
////                .password(passwordEncoder().encode("adminPass"))
////                .roles("ADMIN")
////                .build();
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
////        jdbcUserDetailsManager.createUser(user1);
////        jdbcUserDetailsManager.createUser(admin1);
//        return jdbcUserDetailsManager;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}
