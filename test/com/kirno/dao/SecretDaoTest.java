package com.kirno.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext2.xml" })
public class SecretDaoTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private SecretKeyDao dao;
	
	@Test
	public void saveTest(){
		dao.save("123");
	}
	
	@Test
	public void getTest(){
		String s = dao.get();
		System.out.println(s);
	}

}
