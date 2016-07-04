package org.authentication;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealmOne implements Realm{

	public String getName() {
		return "MyRealmOne";
	}

	public boolean supports(AuthenticationToken token) {
		//仅仅支持UsernamePasswordToken
		return token instanceof UsernamePasswordToken;
	}

	/**
	 * 获取验证身份信息
	 */
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		if(!"zhangsan".equals(username)){
			throw new UnknownAccountException("用户名错误");
		}
		if(!"123".equals(password)){
			throw new IncorrectCredentialsException("密码错误");
		}
		return new SimpleAuthenticationInfo(username, password, getName());
	}

}
