package com.kirno.fliter;

import javax.annotation.Resource;

import com.kirno.actions.AuthAction;
import com.kirno.service.TokenService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

/**
 * 权限认证
 * @author Administrator
 *
 */
public class TokenAuth extends AbstractInterceptor{
	
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
		//如果token不存在抛出错误
		if(!tokenService.isTokenExist(token+"")){
			return "error";
		}
		//如果token已超时抛出错误
		if(tokenService.isTokenTimeOut(token+"")){
			return "error";
		}		
		invocation.addPreResultListener(new PreResultListener() {
			
			@Override
			public void beforeResult(ActionInvocation invocation, String resultCode) {
				AuthAction s = (AuthAction)invocation.getAction();				
				if(!s.getClass().getSimpleName().equals("CustomScanAction")){
					s.setToken(tokenService.updateToken(token));
				}
				System.out.println(s.getClass().getSimpleName()+"请求得到的新Token: "+ s.getToken());
			}
		});
		String result = invocation.invoke();
		return result;
	}

	
	

}
