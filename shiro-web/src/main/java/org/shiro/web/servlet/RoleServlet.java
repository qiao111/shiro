package org.shiro.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

@WebServlet(name="roleServlet",urlPatterns="/role")
public class RoleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1226971113950593726L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Subject subject = SecurityUtils.getSubject();
		subject.checkRole("admin");
		req.setAttribute("subject", subject);
		req.getRequestDispatcher("/WEB-INF/jsp/hasRole.jsp").forward(req, resp);
	}
}
