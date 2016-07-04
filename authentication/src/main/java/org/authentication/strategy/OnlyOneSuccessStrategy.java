package org.authentication.strategy;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;

/**
 * 自定义的验证策略
 * @author qiaolin
 *
 */
public class OnlyOneSuccessStrategy extends AbstractAuthenticationStrategy {
	
	/**
	 * 验证每个realm之后
	 */
	@Override
	public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo,
			AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
		AuthenticationInfo info ;
		if(singleRealmInfo == null){
			info = aggregateInfo;
		}else{
			if(aggregateInfo == null){
				info = singleRealmInfo;
			}else{
				info = merge(singleRealmInfo, aggregateInfo);
				if(info.getPrincipals().getRealmNames().size()>1){
					throw new AuthenticationException("只允许有一个身份验证通过");
				}
			}
		}
		return info;
	}
}
