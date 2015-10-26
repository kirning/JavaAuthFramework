package com.kirno.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kirno.dao.UserDao;
import com.kirno.modal.User;


@Service
public class UserService {
	
	@Resource
	private UserDao dao;
	
	@Resource
	private TokenService tokenService;
	
	/**
	 * 添加代理	 
	 * @return
	 */
	public boolean addPoxy(String name, String phone, String username, String password) {
		return add(name, phone, username, password, 2);
	}
	
	/**
	 * 添加财务
	 * @param name
	 * @param phone
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean addFinance(String name, String phone, String username, String password){
		return add(name, phone, username, password, 1);
	}
	
	public boolean add(String name, String phone, String username, String password, int role){
		
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setUsername(username);
		user.setPhone(phone);
		user.setRole(role);
		
		int result = dao.add(user);
		return result != -1;
	}

	public User login(String username, String password) {
		List<Map<String, String>> params = new ArrayList<>();
		
		HashMap<String,String> param1 = new HashMap<>();
		param1.put("key","username");
		param1.put("value",username);
		params.add(param1);
		
		HashMap<String,String> param2 = new HashMap<>();
		param2.put("key","password");
		param2.put("value",password);
		params.add(param2);
		
		User user = dao.find(params);
		if(user == null){
			return null;
		}
				
		user.setToken(tokenService.createToken(user.getId()+"", user.getRole()+""));
		
		return user;
	}

	public boolean updatePassword(String token, String password) {
		
		Map<String,String> params = new HashMap<>();
		params.put("password", password);
		
		return dao.update(params, tokenService.getUserIdFromToken(token));		
	}

	public boolean updateUserMoney(String userId, float price) {
		return dao.updateUserMoney(userId,price) != 0;
		
	}
	

}
