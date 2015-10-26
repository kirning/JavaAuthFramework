package com.kirno.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kirno.modal.Trade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext2.xml" })
public class TradeServiceTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private TradeService tradeService;
	
	@Test
	public void updateTradeStatusTest(){
		boolean result = tradeService.updateTradeStatus("1");
		System.out.println(result);
		
	}
	
	@Test
	public void getTradeTest(){
		List<Trade> list = tradeService.getTrade();
		System.out.println(list);
	}
	
	@Test
	public void updateTotlePriceTest(){
		tradeService.updateTotlePrice("29", 600);
	}

}
