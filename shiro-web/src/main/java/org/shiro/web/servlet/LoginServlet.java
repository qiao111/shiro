package org.shiro.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@WebServlet(name="loginServlet",urlPatterns="/login")
public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 257259951733322164L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//跳转到login.jsp页面
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String error = null;
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try{
			subject.login(token);
		}catch(UnknownAccountException e){
			error = "用户名/密码错误";
		}catch(IncorrectCredentialsException e){
			error = "用户名/密码错误";
		}catch(AuthenticationException e){
			error = "其他错误：" + e.getMessage();
		}
		if(error != null){
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
		}else{
			req.setAttribute("subject", subject);
			req.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp").forward(req, resp);
		}
	}
}
