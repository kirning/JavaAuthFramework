package com.kirno.dao;

import java.util.Date;
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
public class ShopDaoTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private ShopDao shopDao;
	
	@Test
	public void addTest(){
		Shop shop = new Shop();
		shop.setShopName("abc");
		shop.setAddress("abc");
		shop.setUserId(1);
		shop.setStatus(1);
		shop.setJoinTime(new Date());
		shopDao.add(shop);
	}
	
	@Test
	public void getPresentShopTest(){
		List<Shop> list = shopDao.getPresentShop();
		System.out.println(list.size());
	}

}
