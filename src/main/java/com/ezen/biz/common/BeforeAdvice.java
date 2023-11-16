package com.ezen.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice {
//	@Pointcut("execution(* com.ezen.biz..*Impl.*(..))")
//	public void allPointcut() {} // 포인트컷 ID
	
	@Before("PointcutCommon.allPointcut()")
	public void beforeLog(JoinPoint jp) {
		Signature sig = jp.getSignature();
		String method = sig.getName(); // 비즈니스 메소드 이름을 얻어온다.
		Object[] args = jp.getArgs(); // 메소드 호출 시 인자 정보를 가져온다.

		System.out.println("\n[사전 처리] 비즈니스 로직 수행 전 작업...");
		if (args.length == 0) {
			System.out.printf("메소드명 : %s(), args:없음\n", method);
		} else {
			System.out.printf("메소드명 : %s(), args:%s\n", method, args[0]);
		}

	}

}
