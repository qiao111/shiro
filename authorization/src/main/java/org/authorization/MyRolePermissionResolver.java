package org.authorization;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class MyRolePermissionResolver implements RolePermissionResolver{

	/**
	 * 获取角色的权限集合
	 */
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		if("role1".equals(roleString)){
			return Arrays.asList((Permission)new WildcardPermission("menu:*"));
		}
		return null;
	}

}
