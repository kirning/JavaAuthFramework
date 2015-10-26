package com.kirno.fliter;

import javax.annotation.Resource;

import com.kirno.actions.AuthAction;
import com.kirno.service.TokenService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class FinanceAuth extends AbstractInterceptor{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TokenService tokenService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		AuthAction action = (AuthAction)invocation.getAction();
		String token = action.getToken();	
		String id = tokenService.getRoleFromToken(token);
		//如果不是代理
		if(!id.equals("1")){
			return "error";
		}
		String result = invocation.invoke();
		return result; 	

	}
}
