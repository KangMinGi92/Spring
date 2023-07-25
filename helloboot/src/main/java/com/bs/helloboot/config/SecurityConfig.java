package com.bs.helloboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

import com.bs.helloboot.dto.MyAuthority;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private DbConnectProvider provider;
	
	public SecurityConfig(DbConnectProvider provider) {
		this.provider=provider;
	}
	
	//boot에서 security적용하기
	//1. 인증처리할 bean을 등록하기.
	//		인증관련설정하는 bean -> SecurityFilterChain bean 등록
	//2. 인증방법에 대한 설정 클래스를 등록
	// inMemory, DB연동방식 -> provider를 등록
	
	@Bean
	public SecurityFilterChain authenticationPath(HttpSecurity http) throws Exception{
		return http.csrf().disable()
				.formLogin()
					.successForwardUrl("/successLogin")
					.failureForwardUrl("/errorLogin")
					.usernameParameter("userId")
					.passwordParameter("pw")
					.loginProcessingUrl("/login.do")
					.loginPage("/loginpage")
				.and()
				.authorizeHttpRequests()//interceptor를 등록하는 것과 동일한 기능
					.requestMatchers(CorsUtils::isPreFlightRequest).permitAll() 
					//Security적용 돼었을 때 Cors의 PreFlight에러가 났다면 이 옵션을 처리해줘야함
					.antMatchers("/loginpage").permitAll()
					.antMatchers("/**").hasAnyAuthority(MyAuthority.ADMIN.name(),MyAuthority.USER.name())
				.and()
				.logout()
					.logoutSuccessUrl("/logout")
					.logoutUrl("/logout.do")
				.and()
				.authenticationProvider(provider)
				.build();
	}
}
