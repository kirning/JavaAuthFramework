package com.kirno.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.kirno.annotation.Table;
import com.kirno.modal.User;

@Repository
@Table("t_user")
public class UserDao extends AbstractDao<User> {

	public User find(List<Map<String, String>> param) {
		String sql = "select * from t_user where 1=1 ";
		StringBuffer sqlItem = new StringBuffer();
		for (Map<String, String> item : param) {
			sqlItem.append(" and t_user." + item.get("key") + " = '" + item.get("value") + "'");
		}
		sql += sqlItem;
		User result = null;		
		try {
			result = jdbc.query(sql, re -> {
				User user = null;
				if (re.next()) {
					user = new User();
					user.setName(re.getString("name"));
					user.setPhone(re.getString("phone"));
					user.setRole(re.getInt("role"));
					user.setId(re.getInt("id"));
					user.setMoney(re.getFloat("money"));
				}
				return user;
			});
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return result;
	}

	public int updateUserMoney(String userId, float price) {
		String sql = "update t_user set t_user.money = t_user.money - :price where t_user.id = :userId and (t_user.money-:price) > 0;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		params.addValue("price", price);
		return jdbcName.update(sql, params);		
	}

}
