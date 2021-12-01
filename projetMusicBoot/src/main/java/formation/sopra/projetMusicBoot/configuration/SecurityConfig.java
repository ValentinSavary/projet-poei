package formation.sopra.projetMusicBoot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// authentification par user =>authenticated
		// par role=>hasRole hasAnyRole

		// @formatter:off
		
		http
			.antMatcher("/api/**")
				.csrf().ignoringAntMatchers("/api/**")
			.and()
				.authorizeRequests()
					.antMatchers("/**").permitAll();
		// @formatter:on
	}

}
