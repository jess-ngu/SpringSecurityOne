package ca.sheridancollege.nguye399.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{

	@Autowired
	private LoginAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/owner/**").hasRole("OWNER")
			.antMatchers("/manager/**").hasRole("MANAGER")
			.antMatchers("/employee/**").hasRole("EMPLOYEE")
			.antMatchers("/", "/**", "/css/**", "/js/**", "/images/**").permitAll() //Login not required
			.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
		.and()
			.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.permitAll()
		.and()
			.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler);
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {	
		auth
		.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
			.withUser("admin").password("password").roles("OWNER", "MANAGER", "EMPLOYEE")
			.and()
			.withUser("billy").password("password").roles("OWNER")
			.and()
			.withUser("bob").password("password").roles("MANAGER")
			.and()
			.withUser("joe").password("password").roles("EMPLOYEE");
	}
}
