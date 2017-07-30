package com.project.university.aspect;

import javax.naming.directory.InvalidAttributeValueException;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.project.university.domain.Student;

@Aspect
public class LoggingAspect {

	private Logger logger = Logger.getLogger(LoggingAspect.class);

	@Before("execution(public * com.project.university.service.StudentCheckoutService..*(..))")
	public void logAccountDAO(JoinPoint jointPt) {
		logger.info("before advice- " + jointPt.getSignature());
	}
	
	
	

	@AfterThrowing(pointcut = "execution(public * com.project.university.service..*.makePayment(..))",throwing = "ex")
	public void paymentException(JoinPoint jointPt,InvalidAttributeValueException ex) {
		logger.error("after throwing- " + ex.getMessage());
	}
	
	

	@Around("execution(public * com.project.university.service.StudentCheckoutService.checkout(..))")
	public void logAroundAccount(ProceedingJoinPoint jointPt) {
		
		Object[] checkoutArgs = jointPt.getArgs();
		
		logger.info("around-" + ((Student)checkoutArgs[0]).getName() );

		
		try {
			jointPt.proceed();
		} catch (Throwable e) {
			
			e.printStackTrace();
		}

		logger.info("around -" + ((Student)checkoutArgs[0]).getName());

	}

}
