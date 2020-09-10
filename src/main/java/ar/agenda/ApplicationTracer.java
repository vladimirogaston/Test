package ar.agenda;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;

@Slf4j
public class ApplicationTracer implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		try {
			String params = Arrays.toString(invocation.getArguments());
			log.info("METHOD: " + invocation.getMethod().toString() + " PARAMS: " + params);
			return invocation.proceed();
		} catch (Throwable tr) {
			log.error("EXCEPTION: " + tr.getMessage());
			throw tr;
		}
	}
}
