package com.kirno.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kirno.modal.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext2.xml" })
public class OrderServiceTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private OrderService orderService;
	
	@Test
	public void queryForListTest(){
		List<Order> list = orderService.queryForList(27);
		System.out.println(list);
	}
	
	@Test
	public void addOrderTest(){
		int re = orderService.addOrder("123", "15.1", 28);
		System.out.println(re);
	}
	
	@Test
	public void customScanTest(){
		System.out.println(orderService.customScan("3", ""));
	}
	
	@Test
	public void updateOrderStatusBeathTest(){
		boolean re = orderService.updateOrderStatusBeath(28);
		System.out.println(re);
	}

}
