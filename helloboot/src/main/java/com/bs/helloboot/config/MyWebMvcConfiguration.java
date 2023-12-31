package com.bs.helloboot.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.bs.helloboot.common.interceptor.LoggerInterceptor;
import com.bs.helloboot.websocket.ChattingServer;

@Configuration
@EnableWebSocket
public class MyWebMvcConfiguration implements WebMvcConfigurer,WebSocketConfigurer{
	
	private ChattingServer chatting;
	
	public MyWebMvcConfiguration(ChattingServer chatting) {
		this.chatting=chatting;
	}	
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatting, "/chatting");
	}
	
	//view에 대한 설정
	//기본 화면전환에 대한 설정을 하는 메소드를 재정의 할 수 있다
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/test").setViewName("test");
		registry.addViewController("/chattingpage").setViewName("chattingpage");
		
	}
	
	//Interceptor설정
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/member/*");
		
	}

	//cors에 대한 허용 설정
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//ex) http://localhost:3000은 Node.js 의 기본서버 주소로 예시로 사용함.
		registry.addMapping("/**").allowedOrigins("http://localhost:3000");
	}
	
	//HandlerExceptionResolver를 이용해서 spring에서 발생하는 에러 처리하기 -> webmvc모듈에서 제공
	@Bean
	public HandlerExceptionResolver handleExceptionResolver() {
		Properties exceptionProp=new Properties();
		exceptionProp.setProperty(IllegalAccessException.class.getName(), "error/accessException");
		
		SimpleMappingExceptionResolver resolver=new SimpleMappingExceptionResolver();
		resolver.setExceptionMappings(exceptionProp);
		resolver.setDefaultErrorView("error/error");
		
		return resolver;
	}
	
	
}
