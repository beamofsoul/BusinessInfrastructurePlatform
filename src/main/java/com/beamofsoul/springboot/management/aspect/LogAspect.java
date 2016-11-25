package com.beamofsoul.springboot.management.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beamofsoul.springboot.entity.User;

//@Aspect
//@Configuration
public class LogAspect {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(LogAspect.class);

	@Pointcut("execution(* com.beamofsoul.springboot.service.*.get*(..))")
	public void aroundLog() {

	}

	@Before(value="execution(* com.beamofsoul.springboot.service.*.get*(*)) && args(param)",argNames="param")
	public void beginingLog(Object param) {
		LOGGER.info("#### Before executing method, input param: " + param);
	}

	@After("execution(* com.beamofsoul.springboot.service.*.get*(..))")
	public void endingLog() {
		LOGGER.info("#### After executing method...");
	}

	@AfterReturning(value = "execution(* com.beamofsoul.springboot.service.*.get*(..))", 
			returning = "returnValue")
	public void finalLog(Object returnValue) {
		LOGGER.info("#### AfterReturning return value: " + returnValue);
	}

	@Around(value = "aroundLog()")
	public Object processingAspect(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		LOGGER.info("#### Executing aspect: " + this.getClass());
		Object obj = thisJoinPoint.proceed();
		if (obj != null) {
			User thing = (User) obj;
			thing.setUsername(thing.getUsername() + "=========");
			obj = thing;
		} else {
			obj = new User();
		}
		LOGGER.info("#### Executing aspect finished, haven't return value...");
		return obj;
	}
}
