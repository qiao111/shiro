package com.shiro.filter.env;

import javax.servlet.Filter;

import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.web.env.IniWebEnvironment;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

public class MyIniWebEnvironment extends IniWebEnvironment{
	
	@Override
	protected FilterChainResolver createFilterChainResolver() {
		PathMatchingFilterChainResolver filterChainResolver = new PathMatchingFilterChainResolver();
		DefaultFilterChainManager filterChainManager = new DefaultFilterChainManager();
		//注册默认的Filter
		for(DefaultFilter filter :DefaultFilter.values()){
			filterChainManager.addFilter(filter.name(), (Filter) ClassUtils.newInstance(filter.getFilterClass()));
		}
		//注册URL-filter的映射关系
		filterChainManager.addToChain("/login.jsp", "authc");
		filterChainManager.addToChain("/unauthorized.jsp","anon");
		filterChainManager.addToChain("/**", "authc");
		filterChainManager.addToChain("/**", "roles", "admin");
		filterChainResolver.setFilterChainManager(filterChainManager);
		return filterChainResolver;
	}
}
