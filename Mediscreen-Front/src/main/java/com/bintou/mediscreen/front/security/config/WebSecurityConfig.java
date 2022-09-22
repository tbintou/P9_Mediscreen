package com.bintou.mediscreen.front.security.config;

import com.bintou.mediscreen.front.service.auth.UserDetailsAuthenticate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final AuthenticationSuccess authenticationSuccess;

    @Autowired
    public WebSecurityConfig(AuthenticationSuccess authenticationSuccess) {
        this.authenticationSuccess = authenticationSuccess;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsAuthenticate();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return authProvider;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/registration","/css/**","/js/**", "/images/**").permitAll()
                .antMatchers("/user/**").hasAnyAuthority("ADMIN")
                .antMatchers("/**").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .successHandler(authenticationSuccess)
                .permitAll()
                .loginPage("/login")
                .and()
                .logout()
                .permitAll();

        http.logout()
                .logoutUrl("/app-logout")
                .logoutSuccessUrl("/login");
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //Web resources
        return (web) -> web.ignoring().antMatchers("/css/**");
    }
}

