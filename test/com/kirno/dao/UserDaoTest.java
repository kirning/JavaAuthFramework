package com.kirno.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kirno.modal.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext2.xml" })
public class UserDaoTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private UserDao dao;

	/**
	 * 
	 */
	@Test
	public void findTest() {
		List<Map<String, String>> params = new ArrayList<>();

		Map<String, String> param = new HashMap<>();
		param.put("key", "username");
		param.put("value", "tom");
		params.add(param);

		Map<String, String> param2 = new HashMap<>();
		param2.put("key", "password");
		param2.put("value", "123456");
		params.add(param2);

		User user = dao.find(params);
		if (user != null) {
			System.out.println(user);
		} else {
			System.out.println("not found");
		}
	}

	@Test
	public void addTest() {
		User user = new User();
		user.setName("ะกร๗");
		user.setPhone("123456789");
		user.setUsername("tom");
		user.setPassword("123456");
		user.setRole(1);		
		int re = dao.add(user);
		System.out.println(re);
	}
	
	@Test
	public void updateTest(){
		
		Map<String, String> params = new HashMap<>();
		params.put("password", "789");		
		
		dao.update(params, "1");
	}

}
