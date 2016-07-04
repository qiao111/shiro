package org.authorization;

import java.util.Arrays;

import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * 授权测试
 * @author qiaolin
 *
 */
public class TestAuthorization {
	
	/**
	 * 判断用户是否有某个角色
	 */
	@Test
	public void testhasRole(){
		Subject subject = ShiroUtil.login("classpath:shiro-role.ini", "zhangsan", "123");
		//判断拥有角色role1
		Assert.assertTrue(subject.hasRole("role1"));
		//判断拥有角色role1 and role2
		Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1","role2")));
		//判断含有角色的返回结果
		boolean[] results = subject.hasRoles(Arrays.asList("role1","role2","role3"));
		for(boolean result:results){
			System.out.println(result);
		}
	}
	
	/**
	 * 检查用户是否有某个角色  没有则抛出异常
	 */
	@Test
	public void testcheckRole(){
		Subject subject = ShiroUtil.login("classpath:shiro-role.ini", "zhangsan", "123");
		//判断拥有角色role1
		subject.checkRole("role1");
		//判断拥有角色role1 and role2
		subject.checkRoles("role1","role2");
	}
	
	/**
	 * 测试权限
	 */
	@Test
	public void testIsPermitted(){
		Subject subject = ShiroUtil.login("classpath:shiro-permission.ini", "zhangsan", "123");
		Assert.assertTrue(subject.isPermitted("user:create"));
		boolean[] results = subject.isPermitted("user:create","user:update","user:view");
		for(boolean result:results){
			System.out.println(result);
		}
	}
	
	/**
	 * 测试权限
	 */
	@Test
	public void testCheckPermitted(){
		Subject subject = ShiroUtil.login("classpath:shiro-permission.ini", "zhangsan", "123");
		subject.checkPermission("user:create");
		subject.checkPermissions("user:create","user:update","user:view");
	}
	
	@Test
	public void testDefinePermission(){
		Subject subject = ShiroUtil.login("classpath:shiro-authorizer.ini", "zhangsan", "123");
		System.out.println(subject.isPermitted("+user2+4"));
		System.out.println(subject.isPermitted("user1:update"));
		System.out.println(subject.isPermitted("user2:update"));
		System.out.println(subject.isPermitted("menu:view"));
		System.out.println(subject.isPermitted("+user1+2"));
		System.out.println(subject.isPermitted("+user1+8"));
		System.out.println(subject.isPermitted("+user2+10"));
	}
}
