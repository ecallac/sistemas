/**
 * 
 */
package com.security.web.config;

import java.util.Map;

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

import com.security.web.service.LoginService;

/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 13:38:19
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	LoginService loginService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
		builder.userDetailsService(loginService);
		builder.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(loginService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
//		http.csrf().disable();
		http.sessionManagement().maximumSessions(1);
//		http.sessionManagement().invalidSessionUrl("/expiredSession.html");
//		http.sessionManagement().sessionFixation().newSession();
		
		Map<String,String> map= loginService.getPermissionByRole();
		for (Map.Entry<String,String> entry : map.entrySet()){
			http.authorizeRequests().antMatchers(entry.getKey()).access("hasAnyRole("+entry.getValue()+")");
		}
		http.authorizeRequests().antMatchers("/login").permitAll();
//		http.authorizeRequests().antMatchers("/", "/home").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
//		http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().and().formLogin()
					.loginProcessingUrl("/j_spring_security_check")
					.loginPage("/login")
					.failureUrl("/login?error=true")
					.usernameParameter("userName")
					.passwordParameter("password")
					.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login?logout");
	}
}
