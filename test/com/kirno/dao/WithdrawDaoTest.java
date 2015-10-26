package com.kirno.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kirno.modal.Withdraw;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext2.xml" })
public class WithdrawDaoTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private WithdrawDao dao;

	/**
	 * 
	 */
	@Test
	public void findTest() {
		Withdraw entity = new Withdraw();
		entity.setMoney(15.1f);
		entity.setTime(new Date());
		entity.setStatus(2);
		entity.setUserId(1);
		System.out.println(entity);
		System.out.println(dao.add(entity));
		
	}
	

}
