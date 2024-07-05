package com.mouritech.mt_interview.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class FresherAOP {
	
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Before("execution(* com.mouritech.mt_interview.service.*.*(..))")
	public void beforeMethodExcetion(JoinPoint point)
	{
		//System.out.println("before executing method "+point.getSignature().getName());
		logger.info("before executing method "+point.getSignature().getName());
		
	}
	@After("execution(* com.mouritech.mt_interview.service.*.*(..))")
	public void afterMethodExcetion(JoinPoint point)
	{
		//System.out.println("after excuting method "+point.getSignature().getName());
		logger.info("after executing method "+point.getSignature().getName());
	}

}
