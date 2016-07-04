package org.authorization;


import org.apache.shiro.authz.Permission;

import com.alibaba.druid.util.StringUtils;

public class BitPermission implements Permission{
	
	//资源标识符
	private String resourceIdentify;
	//权限位
	private int permissionBit;
	//实例ID
	private String instanceId;
	
	/**
	 * 
	 * @param permissionString +资源标识符+权限位+实例ID
	 */
	public BitPermission(String permissionString){
		String[] permissions = permissionString.split("\\+");
		if(permissions.length  > 1){
			resourceIdentify = permissions[1];
		}
		if(StringUtils.isEmpty(resourceIdentify)){
			resourceIdentify = "*";
		}
		if(permissions.length > 2){
			permissionBit = Integer.valueOf(permissions[2]);
		}
		if(permissions.length > 3){
			instanceId = permissions[3];
		}
		if(StringUtils.isEmpty(instanceId)){
			instanceId="*";
		}
	}
	
	public boolean implies(Permission p) {
		if(!(p instanceof BitPermission)){
			return false;
		}
		BitPermission other = (BitPermission)p;
		if(!("*".equals(this.resourceIdentify)||
				other.resourceIdentify.equals(this.resourceIdentify))){
			return false;
		}
		if(!(this.permissionBit == 0 || (this.permissionBit & other.permissionBit)!=0)){
			return false;
		}
		
		if(!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))){
			return false;
		}
		return true;
	}
	
}
