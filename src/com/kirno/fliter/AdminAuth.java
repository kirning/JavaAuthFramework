package com.kirno.fliter;

import javax.annotation.Resource;

import com.kirno.actions.AuthAction;
import com.kirno.service.TokenService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminAuth extends AbstractInterceptor{

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
			System.out.println(action.getClass().getSimpleName()+" : "+token);
			String id = tokenService.getRoleFromToken(token);
			//如果不是管理员
			if(!id.equals("0")){
				return "error";
			}
			String result = invocation.invoke();
			return result; 	

	}

}
