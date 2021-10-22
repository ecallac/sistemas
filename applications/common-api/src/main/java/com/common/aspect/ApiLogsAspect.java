/**
 * 
 */
package com.common.aspect;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ecalla
 *
 */
@Component
@Aspect
public class ApiLogsAspect {
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void allResources() {
	}
	
//	@Before("allResources()")
//	public void apiRequestLog(JoinPoint jp) throws Exception {
//		String log = "Method: " +jp.getSignature().getName() + "() >>> arguments: ";
//		for (Object arg : jp.getArgs()) {
//			log += "	Arg=" + arg;
//		}
//		LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
//	}
	
//	@AfterReturning(pointcut = "allResources()",returning = "result")
//	public void apiResponseLog(JoinPoint jp, Object result) {
//		String log = "<<< Return Object << " + jp.getSignature().getName() + " : " + result;
//		LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
//	}
	
	@AfterThrowing(pointcut = "allResources()",throwing = "exception")
	public void apiExceptionLog(JoinPoint jp,Exception exception) {
		String log = "<<< Return Exception << " + jp.getSignature().getName() + " : " + exception.getClass().getSimpleName();
		LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
	}
}