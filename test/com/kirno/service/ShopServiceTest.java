package com.kirno.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kirno.modal.Shop;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext2.xml" })
public class ShopServiceTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private ShopService shopService;
	
	@Test
	public void addShopTest(){
		boolean re = shopService.addShop("abd", "abd", "aaa", "123456789", "tom", "123456","1");
		System.out.println(re);
		
	}
	
	@Test
	public void getShopByPoxyId(){
		List<Shop> list = shopService.getShopByPoxyId("37");
		System.out.println(list);
	}
	
	@Test
	public void getShopWithUserIdTest(){
		Shop shop = shopService.getShopWithUserId(1+"");
		System.out.println(shop);
	}

}
