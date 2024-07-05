package com.mouritech.mt_interview.security;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mouritech.mt_interview.serviceimpl.AdminJwtFilter;
import com.mouritech.mt_interview.serviceimpl.CustomAdminService;

@Configuration
public class FresherSecurity {
	
	
	@Autowired
	private AdminJwtFilter  filter;
	
	
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
		
	}
	@Bean
     public PasswordEncoder password()
     {
		return new BCryptPasswordEncoder();
    	 
     }
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/savefresherdetails","/adminregister","/generatejwttoken").permitAll();
		http.authorizeHttpRequests(requests -> requests.requestMatchers("/getallFreshers","/getbyid","/deletebyid").hasAuthority("ADMIN")
	    .anyRequest().authenticated())
		.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider())
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//		http.authorizeHttpRequests().requestMatchers("/getallFreshers").permitAll();
//		http.authorizeHttpRequests().requestMatchers("/getbyid").permitAll();
//		http.authorizeHttpRequests().requestMatchers("/deletebyid").permitAll();
		http.formLogin();
		http.httpBasic();
        http.csrf(csrf -> csrf.disable());
		return http.build();
	}
	@Bean
	public UserDetailsService userDetailService()
	{
		return new CustomAdminService();
		
	}
	@Bean
	public ObjectMapper mapper()
	{
		return new ObjectMapper();
		
	}
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService());
		provider.setPasswordEncoder( password());
		return provider;

	}

	@Bean
	AuthenticationManager autheManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();

	}
}
