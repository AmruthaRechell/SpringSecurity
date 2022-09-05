package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
		
	
		@Autowired
		UserDetailsService userDetailsService;
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			auth.userDetailsService(userDetailsService);
			
			
			/*
			auth.inMemoryAuthentication()
			.withUser("Sushma").password(getPasswordEncoder().encode("Sushma123")).roles("USER")
			.and()
			.withUser("Amrutha").password(getPasswordEncoder().encode("Amrutha123")).roles("ADMIN");
			*/
			}
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("USER","ADMIN")
			.antMatchers("/all", "/authenticate").permitAll()
			.and()
			.formLogin();
		}@Bean
		public AuthenticationManager getAuthenticationManager() throws Exception{
			return super.authenticationManager();
		}
		@Bean
		public PasswordEncoder getPasswordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}
}
