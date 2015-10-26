package com.kirno.service;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.stereotype.Service;

import com.kirno.dao.SecretKeyDao;
import com.opensymphony.xwork2.ActionContext;


@Service
public class SecretService {

	/**
	 * ����
	 * @param src
	 * @return
	 */
	public String decode(String src) {
		try {
			byte[] key = obtainSecretKey();
			if (key == null) {
				return null;
			}
			// ת��key
			SecretKeySpec convertKey = new SecretKeySpec(key, "AES");
			
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, convertKey);			
			
			byte[] result2 = cipher.doFinal(HexUtils.fromHexString(src));
			return new String(result2);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 *  ����
	 * @param src
	 * @return
	 */
	public String encode(String src) {

		try {
			byte[] key = obtainSecretKey();
			if (key == null) {
				return null;
			}
			// ת��key
			SecretKeySpec convertKey = new SecretKeySpec(key, "AES");

			// ����
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertKey);

			byte[] result = cipher.doFinal(src.getBytes());
			return HexUtils.toHexString(result);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Resource
	private SecretKeyDao secretKeyDao;

	// ��ȡKey
	public byte[] obtainSecretKey() {
		byte[] secretKey = null;
		try {
			// ���ڴ��л�ȡ��Կ
			Object secretKeyObjMemory = ActionContext.getContext().getApplication().get("secretKey");
			// �鿴�ڴ����Ƿ������Կ
			if (secretKeyObjMemory == null) {
				// �����ݿ��ȡ��Կ
				String secretKeyStrDB = secretKeyDao.get();
				// ������ݿⲻ������Կ
				if (secretKeyStrDB == null) {
					// ������Կ
					KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
					keyGenerator.init(128);
					SecretKey genKey = keyGenerator.generateKey();
					secretKey = genKey.getEncoded();
					// �������ݿ�
					
					secretKeyDao.save(Base64.encodeBase64String(secretKey));
				} else {
					secretKey = Base64.decodeBase64(secretKeyStrDB);
				}
				// �����ڴ�
				ActionContext.getContext().getApplication().put("secretKey", Base64.encodeBase64String(secretKey));				
			}else{
				secretKey = Base64.decodeBase64(secretKeyObjMemory.toString());
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return secretKey;
	}

}
