package com.kirno.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.AbstractSqlParameterSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.kirno.annotation.Table;
import com.kirno.modal.Withdraw;

public class AbstractDao<T> {

	@Resource(name = "jdbcTemplate")
	protected JdbcTemplate jdbc;

	@Resource(name = "namedTemplate")
	protected NamedParameterJdbcTemplate jdbcName;

	String tableName;

	public AbstractDao() {
		Table table = getClass().getAnnotation(Table.class);
		if (table != null) {
			tableName = table.value();
		}
	}
	
	public T get(String id, ResultSetExtractor<T> resultSet){
		String sql = "select * from "+tableName+ " where id = :id";
		return jdbcName.query(sql, new MapSqlParameterSource("id",id), resultSet);
	}

	/**
	 * 添加用户，并返回id
	 * 
	 * @param 实体
	 * @return 用户的id，如果为-1则添加不成功
	 */
	public int add(T t) {
		return addData(new BeanPropertySqlParameterSource(t));
	}

	public int add(MapSqlParameterSource param) {
		return addData(param);
	}

	private int addData(AbstractSqlParameterSource sqlParamter) {
		int id = -1;
		try {
			SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc);
			insert.withTableName(tableName);
			insert.setGeneratedKeyName("id");
			Number result = insert.executeAndReturnKey(sqlParamter);
			if (result != null) {
				id = result.intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * 根据id修改信息
	 * 
	 * @param params
	 * @param id
	 * @return
	 */
	public boolean update(Map<String, String> params, String id) {
		if (params.size() == 0) {
			return true;
		}

		StringBuilder sql = new StringBuilder("update " + tableName + " set ");
		for(String key : params.keySet()){
			sql.append(key + " = " + params.get(key)+" ,");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(" where id = :id");

		return jdbcName.update(sql.toString(), new MapSqlParameterSource("id", id)) != 0;
	}
	
	public boolean update(Map<String, String> params, Map<String, String> screen) {
		if (params.size() == 0) {
			return true;
		}

		StringBuilder sql = new StringBuilder("update " + tableName + " set ");
		//要修改的参数
		for(String key : params.keySet()){
			sql.append(key + " = " + params.get(key));
		}
		//筛选条件
		sql.append(" where ");
		for(String key : screen.keySet()){
			sql.append(key + " = " + params.get(key));
		}
		return jdbc.update(sql.toString()) != 0;
	}

	public boolean del(Map<String, String> params) {
		StringBuilder sql = new StringBuilder("delete from " + tableName + " where ");
		for (String key : params.keySet()) {
			sql.append(key + " = "+ params.get(key));
		}
		
		return jdbc.update(sql.toString()) != 0;

	}
	
	public List<T> queryForList(Map<String, String> params, RowMapper<T> mRowMapper) {
		StringBuilder sql = new StringBuilder("select * from "+tableName+" where 1=1 ");

		for (String key : params.keySet()) {
			sql.append("and "+ key + " = '" + params.get(key) + "' ");
		}		
		
		return jdbc.query(sql.toString(), mRowMapper);	
	}
	
	public List<T> queryForList(RowMapper<T> mRowMapper) {
		String sql = "select * from "+tableName;
		return jdbc.query(sql, mRowMapper);
	}
	
	public T query(Map<String, String> params, ResultSetExtractor<T> resultSet){
		StringBuilder sql = new StringBuilder("select * from "+tableName+" where 1=1 ");

		for (String key : params.keySet()) {
			sql.append("and "+ key + " = '" + params.get(key) + "' ");
		}
		return jdbc.query(sql.toString(), resultSet);
	}

}
