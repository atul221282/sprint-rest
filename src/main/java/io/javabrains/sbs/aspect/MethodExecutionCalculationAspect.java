package io.javabrains.sbs.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

//Aspect Multiple Example
// https://dzone.com/articles/implementing-aop-with-spring-boot-and-aspectj

// Annotations Example
// https://howtodoinjava.com/spring/spring-aop/aspectj-around-annotation-example/
@Aspect
@Configuration
public class MethodExecutionCalculationAspect {
	@Around("@annotation(io.javabrains.sbs.albumtags.TrackTime)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("================================= Check for Method call =========================== ");
		long startTime = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long timeTaken = System.currentTimeMillis() - startTime;
		System.out.println("Time Taken by " + joinPoint.getSignature().getName() + " is " + timeTaken);
		return result;
	}
}
