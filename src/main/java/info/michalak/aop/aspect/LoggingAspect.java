package info.michalak.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	//Pointcuts
	@Before("execution(public String getName())")    // ADVIEC Method that runs before any (public String getName())
// 	@Before("execution(public String  info.michalak.aop.model.Circle.getName())")  
// 	@Before("execution(public String  get*())")  any public, String method	, 	that starts with  get   *is a wild card 	 with no arguments
//	@Before("execution(public * 	  get*())")	 any public, *Any return type,   that starts with  get   *is a wild card	 with no arguments
//	@Before("execution( * 	  get*())")			any access type Any return type,   that starts with  get   *is a wild card	 with no arguments
//	@Before("execution( * 	  *(*))")			any access type Any return type,   any name   *is a wild card	with any but one argument
//	@Before("execution( * 	  *(*))")			any access type Any return type,   any name   *is a wild card	with 0 to any number argument		
	public void loggingAdvice(){
		System.out.println("Advice run. Get Method called");			
	}
//@Pointcut
	@Pointcut("execution(public String getName())")
	public void allGetters(){}  // Dummy method that holds all expressions 
	
//@Before
	@Before("allGetters")
	public void secondAdvice(){
		System.out.println("Advice run. Get Method called");			
	}
	
	
	@Pointcut("execution(public String  info.michalak.aop.model.Circle.*(..))") // All in circle methods
	public void allCircleMethods1(){		
	};
	
	@Pointcut("within( info.michalak.aop.model.Circle)")					// All in circle methods
	public void allCircleMethods2(){		
	};
	
	@Pointcut("within( info.michalak.aop.model.*)")							// All Classes in Package Model 		
	public void allClassesInModelPackeage(){		
	};
	
	@Pointcut("within( info.michalak.aop.model..*)")						// All Classes in  Package Model and 0 or more Sub Packages
	public void allClassesInModelPackeageAndSubpackages(){		
	};
	
	
	@Before(" allGetters && allCircleMethods1")  					//&&   Chaining Point cuts
	public void allGettersInCircle(){		
	}
	
// JoinPoint	
	@Before("allCircleMethods1")  								// JoinPoint knows what method triggered the call //Every ware when u can apply ADVICE / usually methods
	public void allGettersInCircle(JoinPoint joinPoint){		// 
		System.out.println(joinPoint.toString());
		
		System.out.println(joinPoint.getTarget());				//Give to string of Object Method that was called Very powerful and useful method
	}
	
	@Before("args(name)")  					//runs before method that takes argument name "setName(String name)"
	public void allGettersInCircle(String name  ){
		System.out.println("A method that takes String as argument" + name);
	}
	
	

	
//@After	
// like finally
	//@After regardless regardless if success
	//@AfterReturning only when completes successfully eg not when exception is thrown 
	//@AfterThrowing executes when exception is thrown
	
	
	
	@AfterReturning(pointcut="args(name)",  returning="rerturnString")  					//runs before method that takes argument name "setName(String name)" and returning value
	public void allGettersInCircle(String name, String rerturnString){
		System.out.println("A method that takes String as argument; " + name + "Returning value; " + rerturnString);
	}
	
	@AfterThrowing(pointcut="args(name)",  throwing="ex")  					//runs before method that takes argument name "setName(String name)" and throws exception ex
	public void allGettersInCircle(String name, Exception ex){
		System.out.println("A method that takes String as argument; " + name + "Returning value; " + ex);
	}
	
	
	
//@Around	
	
	@Around("allGetters")
	public Object myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {

		Object returnValue = null;
		try {
			// before method execution

			returnValue = proceedingJoinPoint.proceed(); // method execution //
															// returnValue
			// after method execution

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return returnValue;

	}
	
	@Around("allGetters")
	public void myAroundAdvice1(ProceedingJoinPoint proceedingJoinPoint) {

		Object returnValue = null;
		try {
			// before method execution

			returnValue = proceedingJoinPoint.proceed(); // method execution //		+ 	returnValue is what method returns												// returnValue
			// after method execution
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	@Around("@annotation(info.michalak.aop.aspect.Loggable)")		// runs for evrything marked Loggable
	public void myAroundAdvice2(ProceedingJoinPoint proceedingJoinPoint) {
	
		try {
			proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
