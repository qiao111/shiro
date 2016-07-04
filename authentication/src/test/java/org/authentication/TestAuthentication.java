package org.authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class TestAuthentication {
	
	@Test
	public void testAuthentication(){
		//获取SecurityManager工厂   SecurityManager:安全管理器
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//获取SecurityManager实例
		SecurityManager securityManager = factory.getInstance();
		//绑定到SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		//获取主体 当前用户
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");
		//登陆 身份验证
		subject.login(token);
		//是否已经登陆
		System.out.println(subject.isAuthenticated());
		subject.logout();
	}
	
	@Test
	public void testSingleRealm(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		//获取SecurityManager实例
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");
		//验证身份
		subject.login(token);
		//是否已经登陆
		System.out.println(subject.isAuthenticated());
		subject.logout();
	}
	
	@Test
	public void testMultiRealms(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
		//获取SecurityManager实例
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");
		//验证身份
		subject.login(token);
		//是否已经登陆
		System.out.println(subject.isAuthenticated());
		subject.logout();
	}
	
	/**
	 * 测试JDBCRealm
	 */
	@Test
	public void testJDBCRealm(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
		//获取SecurityManager实例
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
		//验证身份
		subject.login(token);
		//是否已经登陆
		System.out.println(subject.isAuthenticated());
		subject.logout();
	}
	/**
	 * 所有都成功的验证策略
	 */
	@Test
	public void testAllSuccessStrategy(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-authenticator-all-success.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername("zhangsan");
		token.setPassword("123".toCharArray());
		//身份验证
		subject.login(token);
		//获取所有验证通过的信息
		PrincipalCollection collections = subject.getPrincipals();
		for(String realmName:collections.getRealmNames()){
			System.out.println(realmName);
		}
		//退出登录
		subject.logout();
	}
	
	/**
	 * 返回第一个验证通过的信息
	 */
	@Test
	public void testFirstSuccessStrategy(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-authenticator-first-success.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername("lisi");
		token.setPassword("123".toCharArray());
		//身份验证
		subject.login(token);
		//获取所有验证通过的信息
		PrincipalCollection collections = subject.getPrincipals();
		for(String realmName:collections.getRealmNames()){
			System.out.println(realmName);
		}
		//退出登录
		subject.logout();
	}
	
	/**
	 * 只要有一个验证成功即可，返回所有验证通过的身份认证信息
	 */
	@Test
	public void testAtLeastOneSuccessStrategy(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-authenticator-atleastone-success.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername("zhangsan");
		token.setPassword("123".toCharArray());
		//身份验证
		subject.login(token);
		//获取所有验证通过的信息
		PrincipalCollection collections = subject.getPrincipals();
		for(String realmName:collections.getRealmNames()){
			System.out.println(realmName);
		}
		//退出登录
		subject.logout();
	}
	
	@Test
	public void testOnlyOneSuccessStrategy(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-authenticator-onlyone-success.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername("zhangsan");
		token.setPassword("123".toCharArray());
		//身份验证
		subject.login(token);
		//获取所有验证通过的信息
		PrincipalCollection collections = subject.getPrincipals();
		for(String realmName:collections.getRealmNames()){
			System.out.println(realmName);
		}
		//退出登录
		subject.logout();
	}
}
