package com.kirno.actions;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.ActionProxy;

public class LoginActionTest extends StrutsSpringTestCase {

	@Override
	protected String[] getContextLocations() {
		return new String[] {"file:WebContent/WEB-INF/applicationContext2.xml"};
	}
	
	@Test
	public void LoginTest() throws Exception{
		request.setParameter("username", "tom");
		request.setParameter("password", "123456");
		
		ActionProxy actionProxy = getActionProxy("/login");
		LoginAction action = (LoginAction)actionProxy.getAction();
		String resutl = action.execute();
		System.out.println(resutl);
	}

}
