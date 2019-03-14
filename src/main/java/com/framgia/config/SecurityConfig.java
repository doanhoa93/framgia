package com.framgia.config;
/*

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.framgia")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/private/**").hasAnyRole("ADMIN", "MANAGER")
				.antMatchers("/public/**")
				.permitAll()
				.and()
				.formLogin();
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication()
				.withUser("manager")
				.password(encoder.encode("manager"))
				.roles("ADMIN", "MANAGER");
		auth.inMemoryAuthentication()
				.withUser("client")
				.password(encoder.encode("client"))
				.roles("USER");
	}
}  */
