/**
 * 
 */
package common.codrim.util;

import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ClassUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Administrator
 *
 */
public class DataSourceMethodInterceptor implements MethodInterceptor,InitializingBean {
	private static final Logger log = Logger.getLogger("BOSS");

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		log.info("------------------------------------拦截器切换数据源");
	}

	/* (non-Javadoc)
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		Class<?> cl=invocation.getThis().getClass();
		String className=cl.getName();
		if(ClassUtils.isAssignable(cl, Proxy.class)){
			className=invocation.getMethod().getDeclaringClass().getName();
		}
		String methodName=invocation.getMethod().getName();
		Object[] objects=invocation.getArguments();
		log.info("-------------------拦截切换数据源： className:"+className+"methodName:"+methodName+"----"+objects);
		if(methodName.contains("select")||methodName.contains("get")||methodName.contains("check")||methodName.contains("login")){
			DynamicDataSource.setCurrentLookupKey("readDataSource");
		}else if(methodName.contains("add")||methodName.contains("modify")
				||methodName.contains("update")||methodName.contains("delete")||methodName.contains("create")){
			DynamicDataSource.setCurrentLookupKey("writeDataSource");
		}else{
			DynamicDataSource.setCurrentLookupKey("writeDataSource");
		}
		Object result=invocation.proceed();
		return result;
	}

}
