package com.alierqul.todolist.configure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private UserAuthService userAuthService;
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic();
    http.csrf().disable();// Browserdan üretilen bir token beklemediğini ifade eder
    //BasicAuthenticationEntryPoint.class;
    //Browser da herhangi bir pop-up login inin gelmesini engelleyen method.
    http.httpBasic().authenticationEntryPoint(new AuthEntryPoint());
    
    http
    .authorizeHttpRequests().antMatchers(HttpMethod.POST, "/api/1.0/auth").authenticated()
    .and()
    .authorizeHttpRequests().anyRequest().permitAll();
    
    http
    .sessionManagement()
    .sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
        
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  
  auth.userDetailsService(userAuthService).passwordEncoder(passwordEncoder());
  }
  
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
