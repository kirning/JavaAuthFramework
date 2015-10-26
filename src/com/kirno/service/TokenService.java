package com.kirno.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import com.kirno.dao.TokenDao;

/**
 * 操作Token
 * @author Administrator
 *
 */
@Service
public class TokenService {

	private final static long tokenTimeOut = 1 * 60 * 60 * 1000;
	
	@Resource
	private SecretService secret;
	
	@Resource
	private TokenDao tokenDao;

	/**
	 * 移除token
	 * 
	 * @param token
	 */
	public void removeTokan(String token) {
		
	}
	
	/**
	 * 查看是否存在该token
	 * @return 
	 */
	public boolean isTokenExist(String token){
		return tokenDao.isExist(token);
	}

	/**
	 * 创建token
	 * @param userId
	 * @return
	 */
	public String createToken(String userId, String role) {
		String value = userId + "&" + getDate() + "&" + role;
		String token = secret.encode(value);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("token", token);
		params.addValue("userId", userId);
		
		Map<String,String> delParams = new HashMap<>();
		delParams.put("userId", userId);		
		tokenDao.del(delParams);

		tokenDao.add(params);
		return token;
	}

	/**
	 * 更新token
	 * 
	 * @param token
	 * @return
	 */
	public String updateToken(String token) {
		String userId = getUserIdFromToken(token);
		String newToken = userId + "&" + getDate() + "&" + getRoleFromToken(token);
		
		newToken = secret.encode(newToken);		
		
		Map<String, String> params = new HashMap<>();		
		params.put("token", newToken);		
		
		tokenDao.update(params, userId);
		return newToken;
	}

	/**
	 * 判断token是否过期
	 * 
	 * @param token
	 * @return
	 */
	public boolean isTokenTimeOut(String token) {
		String time = secret.decode(token).split("&")[1];
		long cha = getDate() - Long.valueOf(time);
		if (cha > tokenTimeOut) {
			return true;
		} else {
			return false;
		}
	}

	private long getDate() {
		return new Date().getTime();
	}
	
	private String[] getEncodeToken(String token){
		return secret.decode(token).split("&");
	}

	public String getUserIdFromToken(String token) {				
		return getEncodeToken(token)[0];
	}
	public String getTimeFromToken(String token) {				
		return getEncodeToken(token)[1];
	}
	public String getRoleFromToken(String token) {				
		return getEncodeToken(token)[2];
	}

	public void del(String token) {
		Map<String, String> params = new HashMap<>();
		params.put("userId", token);
		tokenDao.del(params);
	}
	
	

}
