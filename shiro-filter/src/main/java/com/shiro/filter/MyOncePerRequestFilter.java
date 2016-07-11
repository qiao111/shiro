package com.shiro.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

/**
 * OncePerRequestFilter保证一次请求只调用一次doFilterInternal
 * 即内部forward不再多执行一次doFilterInternal
 * @author qiaolin
 *
 */
public class MyOncePerRequestFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		System.out.println("==================once per request===================");
		chain.doFilter(request, response);
	}
}
