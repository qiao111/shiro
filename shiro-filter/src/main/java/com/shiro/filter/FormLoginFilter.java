package com.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

public class FormLoginFilter extends PathMatchingFilter {
	
	private String loginUrl = "/login.jsp";
	
	private String successUrl = "/";
	
	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		if(SecurityUtils.getSubject().isAuthenticated()){
			return true;
		}
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if(isLoginRequest(req)){
			if("post".equalsIgnoreCase(req.getMethod())){//如果是post请求
				boolean loginSuccess = login(req);
				if(loginSuccess){
					return redirectToSuccessUrl(req,resp);//中断拦截器链
				}
			}
			return true;//继续连接器链
		}else{//保存当前地址并重定向到登陆地址
			saveRequestAndRedirectToLogin(req, resp);
			return false;
		}
	}
	
	/**
	 * 重定向到登陆成功页面
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException 
	 */
	private boolean redirectToSuccessUrl(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		WebUtils.redirectToSavedRequest(req, resp, successUrl);
		return false;
	}

	/**
	 * 登陆
	 * @param req
	 * @return
	 */
	private boolean login(HttpServletRequest req) {
		String username = (String)req.getParameter("username");
		String password = (String)req.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		req.setAttribute("subject", subject);
		return subject.isAuthenticated();// 是否身份验证通过
	}

	/**
	 * 是否是登陆请求
	 * @param request
	 * @return
	 */
	private boolean isLoginRequest(HttpServletRequest request){
		return pathsMatch(loginUrl, WebUtils.getPathWithinApplication(request));
	}
	
	public void saveRequestAndRedirectToLogin(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		WebUtils.saveRequest(req);
		WebUtils.issueRedirect(req, resp, loginUrl);
	}
}
