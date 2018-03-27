package io.javabrains.sbs.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

//Aspect Multiple Example
// https://dzone.com/articles/implementing-aop-with-spring-boot-and-aspectj

// Annotations Example
// https://howtodoinjava.com/spring/spring-aop/aspectj-around-annotation-example/

@Aspect // indicates that this is an Aspect
@Configuration // indicates that this file contains a Spring Bean Configuration for an Aspect
public class AlbumServiceAspect {
	// What kind of method calls I would intercept
	// execution(* PACKAGE.*.*(..))
	// Weaving & Weaver
	// @Before: We would want to execute the Aspect before the execution of the
	// method
	// @Before("execution(* io.javabrains.sbs.albumtags.*.*(..))") // target
	// everything under this package
	// @Before("execution(* io.javabrains.sbs.albumtags.AlbumService.find*(..))") //
	// target all methods, that starts with find, in album service
	@Before("execution(* io.javabrains.sbs.albumtags.AlbumService*.*(..))") // target all methods in AlbumService
	public void before(JoinPoint joinPoint) {
		// Advice
		// System.out.println("================================= Check for
		// AlbumService=========================== ");
		// System.out.println(joinPoint.getSignature().getName());
		Object[] signatureArgs = joinPoint.getArgs();
		for (Object object : signatureArgs) {
			// System.out.println("Args value are : " + object);
		}
		// System.out.println(" Allowed execution for {}" + joinPoint);
	}

	@AfterReturning(value = "execution(* io.javabrains.sbs.albumtags.AlbumService*.*(..))", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		/*System.out.println("================================= Check for AlbumService=========================== ");
		System.out.println(joinPoint.getSignature().getName());
		System.out.println(joinPoint + " returned with value " + result);*/
	}

	@After(value = "execution(* io.javabrains.sbs.albumtags.AlbumService*.*(..))")
	public void after(JoinPoint joinPoint) {
		/*System.out.println("================================= Check for AlbumService=========================== ");
		System.out.println(joinPoint.getSignature().getName());
		System.out.println("after execution of " + joinPoint);*/
	}

}
