package com.kirno.utils;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kirno.service.SecretService;
import com.kirno.service.TokenService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext2.xml" })
public class TokenUtilsTest extends AbstractJUnit4SpringContextTests {
	@Resource
	private TokenService tokenService;
	
	@Test
	public void createTokenTest(){
		String str = tokenService.createToken("1", "1");
		System.out.println(str);
	}
	
	@Resource
	SecretService secretUtils;
	
	@Test
	public void decodeTest(){
		String src = "b4649ac0cc0f12afc7a9286cdecc56e10a1a0ce596ab0293dd65c3416d942d8b";
		String result = secretUtils.decode(src);
		System.out.println(result);
	}

}
