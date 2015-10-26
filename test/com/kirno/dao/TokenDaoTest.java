package com.kirno.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext2.xml" })
public class TokenDaoTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private TokenDao tokenDao;
	
	@Test
	public void isExistTest(){		
		boolean re = tokenDao.isExist("abc");
		System.out.println(re);
	}
	
	@Test
	public void addTest(){
		System.out.println(tokenDao.add(new MapSqlParameterSource("token", "456789")));
	}
	
	@Test
	public void delTest(){
		Map<String,String> delParams = new HashMap<>();
		delParams.put("userId", "1");
		tokenDao.del(delParams);
	}

}
