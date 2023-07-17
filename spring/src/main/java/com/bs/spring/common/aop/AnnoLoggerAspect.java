package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class AnnoLoggerAspect {

	//pointcut설정
	@Pointcut("within(com.bs.spring.member..*)")
	public void loggerTest() {}
	
	//advisor설정
	@Before("loggerTest()")
	public void loggerBefore(JoinPoint jp) {
		log.debug("======= annotation aop before =======");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("===========aop before================");
	}
	
	@Pointcut("execution(* com.bs.spring.memo..*(..))")
	public void memoLogger() {}
	
	@After("memoLogger()")
	public void afterLogger(JoinPoint jp) {
		log.debug("======= annotation aop after =======");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("===========aop after================");
	}
	
	//메소드 실행 전,후에 특정 로직을 실행할때 사용
	//pointcut을 바로 지정해서 사용할 수 도 있다.
	@Around("execution(* com.bs.spring..*DaoImpl.*(..))")
	public Object daoLogger(ProceedingJoinPoint join) throws Throwable{
		//전,후 로직은 한번에 설정할 수 있다.....
		//전, 후를 구분하는 구문은 ProceedingJoinPoint클래스가 제공하는 proceed()메소드를 이용
		//proceed()메소드가 호출한 다음 라인은 후처리, 그전 라인은 전처리
		//proceed()메소드는 Object를 반환한다.
		StopWatch stop=new StopWatch();
		log.debug("============= around logger dao  before=============");
		log.debug("-------------전처리 내용 구현 ----------------------------");
		log.debug("====================================================");
		Signature sig=join.getSignature();
		String classMethod=sig.getDeclaringType().getName()+sig.getClass();
		//join.getTarget();
		Object obj=join.proceed();
		stop.start();
		log.debug("============= around logger dao after ==============");
		log.debug("-------------후처리 내용 구현 ----------------------------");
		log.debug("실행시간 : "+stop.getTotalTimeMillis()+"ms");
		log.debug("====================================================");
		return obj;
	}
	
	@AfterThrowing(pointcut = "loggerTest()", throwing = "e")
	public void afterThrowing(JoinPoint jp, Throwable e) {
		log.debug("에러발생!!!!!!!!!");
		Signature sig=jp.getSignature();
		log.debug("{}",sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("{}",e.getMessage());
		StackTraceElement[] stacktrace=e.getStackTrace();
		for(StackTraceElement element : stacktrace) {
			log.debug("{}",element);
		}
	}
	
}
