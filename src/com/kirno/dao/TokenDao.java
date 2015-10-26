package com.kirno.dao;

import java.util.Map;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.kirno.annotation.Table;

@Repository
@Table("t_token")
public class TokenDao extends AbstractDao<String> {

	public boolean isExist(String string) {
		String sql = "select count(id) from t_token where token = :token";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("token", string);
		return jdbcName.query(sql, params, (ResultSetExtractor<Boolean>) rs -> {
			rs.next();
			return rs.getInt(1) != 0;
		});
	}

	@Override
	public boolean update(Map<String, String> params, String userId) {
		if (params.size() == 0) {
			return true;
		}

		StringBuilder sql = new StringBuilder("update " + tableName + " set ");
		for(String key : params.keySet()){
			sql.append(key + " = '" + params.get(key)+"'");
		}
		sql.append(" where userId = :userId");

		return jdbcName.update(sql.toString(), new MapSqlParameterSource("userId", userId)) != 0;
	}

}
