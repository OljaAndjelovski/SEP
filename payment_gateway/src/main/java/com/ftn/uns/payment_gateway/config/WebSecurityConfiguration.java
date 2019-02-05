package com.ftn.uns.payment_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; 

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/api/orders/**").authenticated()
                //.antMatchers(HttpMethod.GET, "/sessions/**").authenticated()
                .antMatchers("/api/**").permitAll()
                .and().csrf().disable()
                //.addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
        http.headers().frameOptions().sameOrigin().disable();
    }
    
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
						.allowedHeaders("Content-Type", "Date", "Total-Count", "loginInfo", "jwt_token",
								"passwordStatus")
						.exposedHeaders("Content-Type", "Date", "Total-Count", "loginInfo", "jwt_token",
								"passwordStatus")
						.maxAge(3600);
			}
		};
	}
}