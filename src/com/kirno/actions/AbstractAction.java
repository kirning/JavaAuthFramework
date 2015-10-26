package com.kirno.actions;

import com.opensymphony.xwork2.ActionSupport;

public class AbstractAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int result = 0;
	protected String message;
	
	public int getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}
}
