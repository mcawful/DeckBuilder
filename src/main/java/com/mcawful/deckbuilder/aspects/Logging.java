/**
 * 
 */
package com.mcawful.deckbuilder.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Michael McAuliffe
 *
 */
@Aspect
@Component
public class Logging {

	private static Logger repoLog = LoggerFactory.getLogger("Repo");
	private static Logger serviceLog = LoggerFactory.getLogger("Service");
	private static Logger controllerLog = LoggerFactory.getLogger("Controller");
	private static Logger utilLog = LoggerFactory.getLogger("Util");
	
	@Pointcut("within(com.mcawful.deckbuilder.repos.*)")
	public void repoClassMethods() {
		// Method intentionally left empty
	}

	@Pointcut("within(com.mcawful.deckbuilder.services.*)")
	public void serviceClassMethods() {
		// Method intentionally left empty
	}

	@Pointcut("within(com.mcawful.deckbuilder.controllers.*)")
	public void controllerClassMethods() {
		// Method intentionally left empty
	}

	@Pointcut("within(com.mcawful.deckbuilder.utils.*)")
	public void utilClassMethods() {
		// Method intentionally left empty
	}
	
	@Before(value = "repoClassMethods() && args(object)")
	public void logBeforeRepoClassMethods(JoinPoint jp, Object object) {

		this.before(jp, object, repoLog);
	}

	@AfterReturning(value = "repoClassMethods()", returning = "returnValue")
	public void logAfterReturningRepoClassMethods(JoinPoint jp, Object returnValue) {

		this.afterReturning(jp, returnValue, repoLog);
	}

	@AfterThrowing(value = "repoClassMethods()", throwing = "exception")
	public void logAfterThrowingRepoClassMethods(JoinPoint jp, Throwable exception) {

		this.afterThrowing(jp, exception, repoLog);
	}

	@Before(value = "serviceClassMethods() && args(object)")
	public void logBeforeServiceClassMethods(JoinPoint jp, Object object) {

		this.before(jp, object, serviceLog);
	}

	@AfterReturning(value = "serviceClassMethods()", returning = "returnValue")
	public void logAfterReturningServiceClassMethods(JoinPoint jp, Object returnValue) {

		this.afterReturning(jp, returnValue, serviceLog);
	}

	@AfterThrowing(value = "serviceClassMethods()", throwing = "exception")
	public void logAfterThrowingServiceClassMethods(JoinPoint jp, Throwable exception) {

		this.afterThrowing(jp, exception, serviceLog);
	}

	@Before(value = "controllerClassMethods() && args(object)")
	public void logBeforeControllerClassMethods(JoinPoint jp, Object object) {

		this.before(jp, object, controllerLog);
	}

	@AfterReturning(value = "controllerClassMethods()", returning = "returnValue")
	public void logAfterReturningControllerClassMethods(JoinPoint jp, Object returnValue) {

		this.afterReturning(jp, returnValue, controllerLog);
	}

	@AfterThrowing(value = "controllerClassMethods()", throwing = "exception")
	public void logAfterThrowingControllerClassMethods(JoinPoint jp, Throwable exception) {

		this.afterThrowing(jp, exception, controllerLog);
	}

	@Before(value = "utilClassMethods() && args(object)")
	public void logBeforeUtilClassMethods(JoinPoint jp, Object object) {

		this.before(jp, object, utilLog);
	}

	@AfterReturning(value = "utilClassMethods()", returning = "returnValue")
	public void logAfterReturningUtilClassMethods(JoinPoint jp, Object returnValue) {

		this.afterReturning(jp, returnValue, utilLog);
	}

	@AfterThrowing(value = "utilClassMethods()", throwing = "exception")
	public void logAfterThrowingUtilClassMethods(JoinPoint jp, Throwable exception) {

		this.afterThrowing(jp, exception, utilLog);
	}

	/**
	 * 
	 * @param jp
	 * @param obj
	 * @param log
	 */
	private void before(JoinPoint jp, Object obj, Logger log) {

		String objStr = obj.toString().replace("\n", "").replace("\r", "");

		if (objStr.length() > 10)
			objStr.substring(0, 40);

		log.info(jp.getSignature() + " - Passed-In: " + objStr);
	}

	/**
	 * 
	 * @param jp
	 * @param rtn
	 * @param log
	 */
	private void afterReturning(JoinPoint jp, Object rtn, Logger log) {

		if (rtn != null) {

			String objStr = rtn.toString().replace("\n", "").replace("\r", "");

			if (objStr.length() > 10)
				objStr.substring(0, 40);

			log.info(jp.getSignature() + " - Returned: " + objStr);

		} else
			log.info(jp.getSignature().toString());
	}

	/**
	 * 
	 * @param jp
	 * @param e
	 * @param log
	 */
	private void afterThrowing(JoinPoint jp, Throwable e, Logger log) {

		log.warn(jp.getSignature() + " Exception: " + e.toString());
	}
}
