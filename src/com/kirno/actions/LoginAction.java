package com.kirno.actions;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.kirno.modal.User;
import com.kirno.service.UserService;

@Action
@ParentPackage("json-default")
@Result(type = "json", params={"ignoreHierarchy","false"})
@InterceptorRef("json")
public class LoginAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username, password;
	private User user;

	@Resource
	private UserService service;

	@Override
	public String execute() throws Exception {
		user = service.login(username, password);
		if(user != null){
			result = 1;
			return SUCCESS;
		}		
		
		return super.execute();
	}
	

	public User getUser() {
		return user;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
