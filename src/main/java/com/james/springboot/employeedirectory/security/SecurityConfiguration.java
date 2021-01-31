package com.james.springboot.employeedirectory.security;

import com.james.springboot.employeedirectory.security.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private UserPrincipalDetailsService userPrincipalDetailsService;

	@Autowired
	public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
		this.userPrincipalDetailsService = userPrincipalDetailsService;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//In-memory authentication
//		auth.inMemoryAuthentication()
//		.withUser("admin").password(passwordEncoder().encode("admin123"))
//		.authorities("ACCESS_LIST","ADD_EMPLOYEE","ROLE_ADMIN")
//
//		.and()
//		.withUser("jjk226").password(passwordEncoder().encode("jjk123"))
//		.roles("EMPLOYEE").authorities("ACCESS_LIST")
//
//		.and()
//		.withUser("test").password(passwordEncoder().encode("test123"))
//		.authorities("ACCESS_LIST", "ADD_EMPLOYEE", "ROLE_TEST");

		//configuring using authentication provider
		auth.authenticationProvider(this.daoAuthenticationProvider());

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/sensors/**").authenticated()
			.antMatchers("/sensors/**").permitAll()
			.antMatchers("/employees/homepage").permitAll()
			.antMatchers("/profile/**").authenticated()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/management/**").hasAnyRole("MANAGER", "ADMIN")
			.antMatchers("/employees/list").hasAuthority("ACCESS_LIST")
				.antMatchers("/employees/add").hasAuthority("ADD_EMPLOYEE")
			.antMatchers("/api/public/users").hasAnyRole("ADMIN", "TEST")
			.and()
			.formLogin()
			.loginPage("/login").permitAll()
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login")
			.and()
			.rememberMe().tokenValiditySeconds(1000);

	}


	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

		return daoAuthenticationProvider;
	}

}
