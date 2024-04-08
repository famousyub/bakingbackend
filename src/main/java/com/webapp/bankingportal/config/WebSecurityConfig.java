package com.webapp.bankingportal.config;

import com.webapp.bankingportal.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.webapp.bankingportal.security.JwtAuthenticationEntryPoint;
import com.webapp.bankingportal.security.JwtAuthenticationFilter;
import com.webapp.bankingportal.service.JWTUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	//@Autowired
	//private JWTUserDetailsService jwtUserDetailsService;


	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		//auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	/*
	 *
	 *
	 * */

	private static final String[] WHITE_LIST_URL = { "/api/users/register", "/api/users/login",
			"/api/users/generate-otp", "/api/users/verify-otp" };

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


		http.csrf().disable().authorizeHttpRequests()
		.requestMatchers("/api/users/register/*").permitAll()
				.requestMatchers("/api/users/login/*").permitAll()
				.requestMatchers("/api/users/register").permitAll()
				.requestMatchers("/api/facture/*").permitAll()
				.requestMatchers("/api/users/login").permitAll()
				.requestMatchers("/api/account/pin/*").permitAll()
				.requestMatchers("/api/dashboard/*").permitAll()
				.requestMatchers("/api/account/*").permitAll()
				.requestMatchers("/api/users/generate-otp").permitAll()
				.requestMatchers("/api/users/verify-otp").permitAll()
				.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				 .requestMatchers("swagger-ui.html","/v3/api-docs/**","/swagger-ui/**","/actuator/**").permitAll()
				.anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

//    @Override
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {
//    	 http.cors().and().csrf().disable()
//    	 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//         .exceptionHandling(auth -> auth.authenticationEntryPoint(jwtAuthenticationEntryPoint))
//                .authorizeRequests()
//                .antMatchers().permitAll()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .anyRequest().authenticated()
//           
//           
//            http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    	 return http.build();
//    
//    }
//   
}


/*



import taffah.achraf.ebankingbackend.security.jwt.AuthEntryPointJwt;
import taffah.achraf.ebankingbackend.security.jwt.AuthTokenFilter;
import taffah.achraf.ebankingbackend.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}

 */