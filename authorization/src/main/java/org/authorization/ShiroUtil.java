package org.authorization;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroUtil {
	/**
	 * 登陆
	 * @param iniPath
	 * @param username
	 * @param password
	 * @return
	 */
	public static Subject login(String iniPath,String username,String password){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniPath);
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername(username);
		token.setPassword(password.toCharArray());
		subject.login(token);
		return subject;
	}
}
