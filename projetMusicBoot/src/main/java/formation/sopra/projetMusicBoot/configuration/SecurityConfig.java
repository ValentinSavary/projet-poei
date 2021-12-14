package formation.sopra.projetMusicBoot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/musics/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// authentification par user =>authenticated
		// par role=>hasRole hasAnyRole

		// @formatter:off
		
		http
			// precision de l URL de l api
			.antMatcher("/api/**")
			// mise en place des tokens
				.csrf().ignoringAntMatchers("/api/**").ignoringAntMatchers("/musics/**").ignoringAntMatchers("/covers/**")
			.and()
			// creation session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			// autorisations requetes
			.authorizeRequests()
			.antMatchers(HttpMethod.POST,"/api/user/**").permitAll()
			.antMatchers(HttpMethod.PUT,"/api/user/**").authenticated()
			.antMatchers(HttpMethod.DELETE,"/api/user/**").authenticated()
			.antMatchers(HttpMethod.GET,"/api/music/**").permitAll()
			.antMatchers(HttpMethod.GET,"/api/album/**").permitAll()
			.antMatchers(HttpMethod.GET,"/api/artist/**").permitAll()
			.antMatchers(HttpMethod.GET,"/api/playlist/**").authenticated()
			.antMatchers(HttpMethod.GET,"/api/user/**").authenticated()
			.antMatchers(HttpMethod.POST,"/api/music/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST,"/api/album/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST,"/api/artist/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST,"/api/playlist/**").authenticated()
			.antMatchers(HttpMethod.PUT,"/api/music/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT,"/api/album/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT,"/api/artist/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT,"/api/playlist/**").authenticated()
			.antMatchers(HttpMethod.DELETE,"/api/music/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE,"/api/album/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE,"/api/artist/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE,"/api/playlist/**").authenticated()
			.antMatchers(HttpMethod.POST,"/musics/**").permitAll()
			.antMatchers(HttpMethod.POST,"/covers/**").permitAll()
			.antMatchers("/api/auth").permitAll()
			.and()
			//authentification Basic (cf PostMan)
			.httpBasic();
			
		// @formatter:on
	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}