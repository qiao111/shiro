package com.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;
/**
 * 类似于Spring的AOP
 * @author qiaolin
 *
 */
public class MyAdviceFilter extends AdviceFilter{
	
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("===========拦截前处理=========");
		return true;// false 将中断处理
	}
	
	@Override
	protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("============拦截后处理==========");
	}
	
	@Override
	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception)
			throws Exception {
		System.out.println("==========拦截后最终处理==========");
	}
}

