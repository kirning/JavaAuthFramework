package com.kirno.dao;

import org.springframework.stereotype.Repository;

import com.kirno.annotation.Table;

@Repository
@Table("t_secertKty")
public class SecretKeyDao extends AbstractDao<String> {

	public String get() {
		String sql = "select * from t_secretKey";
		return jdbc.query(sql, re -> {
			if (re.next()) {
				return re.getString(2);
			}
			return null;
		});
	}

	public boolean save(String encodeToString) {
		String sql = "insert into t_secretKey (secretKey) values (?)";
		int re = -1;
		try {
			re = jdbc.update(sql, param -> {
				param.setString(1, encodeToString);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re != 0;

	}

}
