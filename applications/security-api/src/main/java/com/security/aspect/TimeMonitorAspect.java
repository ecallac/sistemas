package com.security.aspect;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author ecalla
 *
 */
@Component
@Aspect
public class TimeMonitorAspect {
	@Around("execution(* com.security.controller.*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String txid= String.valueOf(start+getRandomNumberInRange(0, 100));
        String log = "### Start Process. TxId: "+txid+". Method: " + joinPoint.getSignature().getName() +"(). Arguments: ";
		for (Object arg : joinPoint.getArgs()) {
			log += " Arg=" + arg;
		}
        LogManager.getLogger(joinPoint.getSignature().getDeclaringTypeName()).info(log);
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        LogManager.getLogger(joinPoint.getSignature().getDeclaringTypeName()).info("### End Process. TxId: "+txid+". >>> executed in " + executionTime + "ms");
        return proceed;
    }
	private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
