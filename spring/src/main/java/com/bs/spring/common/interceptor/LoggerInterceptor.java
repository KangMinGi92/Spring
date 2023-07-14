package com.bs.spring.common.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.controller.DemoController;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//preHandle 컨트롤러에 매핑메소드가 실행 되기 전 시점
		//반환형이 boolean true반환하면 매핑메소드가 실행, false를 반환하면 매핑메소드 실행을 하지 않음
		log.debug("----- 인터셉터 prehandle 실행 ------");
		log.debug(request.getRequestURI());
		Map params=request.getParameterMap();
		for(Object key : params.keySet()) {
			System.out.println(key);
		}
		log.debug("-------------------------------");
		//response.sendRedirect(request.getContextPath());
		
		//handler -> 실행되는 controller클래스, 실행되는 메소드확인
		HandlerMethod hm=(HandlerMethod)handler;
		log.debug("{}",hm.getBean());
		DemoController demo=(DemoController)hm.getBean();
		
		log.debug("{}",hm.getMethod());
		Method m=hm.getMethod();
//		m.invoke(m, null)
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//afterCompletion은 컨트롤러에서 메소드 실행가 실행 된 후 시점
		log.debug("----- 인터셉터 postHandle -----");
		log.debug("{}",modelAndView.getViewName());
		Map modelData=modelAndView.getModel();
		log.debug("{}",modelData);
		log.debug("----------------------------");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object hanlder,
			Exception ex) throws Exception{
		//afterCompletion은 컨트롤러에서 return으로 응답 한 완료 후 시점
		//View에서 Exception 을 발생하지 않으면 null 발생하면 에러 메세지 띄워줌
		log.debug("----- 응답 후 인터셉터실행 -----");
		log.debug("요청주소{} : ",request.getRequestURI());
		log.debug("에러메세지 {} ",ex!=null?ex.getMessage():"응답성공");
		log.debug("-------------------------");
		
	}

}
