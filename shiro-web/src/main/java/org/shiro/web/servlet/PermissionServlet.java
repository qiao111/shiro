package org.shiro.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

@WebServlet(name="permissionServlet",urlPatterns="/permission")
public class PermissionServlet extends HttpServlet {
	
	private static final long serialVersionUID = -5659383411945542370L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Subject subject = SecurityUtils.getSubject();
		subject.checkPermission("user:create");
		req.setAttribute("subject", subject);
		req.getRequestDispatcher("/WEB-INF/jsp/hasPermission.jsp").forward(req, resp);
	}
}
