package com.kirno.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext2.xml" })
public class ExchangeServiceTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private ExchangeService exchangeService;
	
	@Test
	public void addExchangeTest(){
		System.out.println(exchangeService.addExchange("12"));
	}


}
