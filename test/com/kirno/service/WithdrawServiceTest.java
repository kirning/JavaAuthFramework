package com.kirno.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kirno.modal.Withdraw;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext2.xml" })
public class WithdrawServiceTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private WithdrawService service;
	
	@Test
	public void addWithdrawTest(){
//		System.out.println(service.addWithdraw("15.4"));
	}
	
	@Test
	public void getWithdrawListByUserId(){
		List<Withdraw> list = service.getWithdrawListByUserId("39");
		System.out.println(list.size());
	}


}
