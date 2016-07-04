package org.authorization;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * 权限字符串解析器
 * @author qiaolin
 *
 */
public class BitAndWildPermissionResolver implements PermissionResolver{

	/**
	 * 字符串转换
	 */
	public Permission resolvePermission(String permissionString) {
		if(permissionString.startsWith("+")){
			return new BitPermission(permissionString);
		}
		return new WildcardPermission(permissionString);
	}

}
