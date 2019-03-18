package com.manager.diy.ms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.manager.diy.ms.po.Tmodule;

@Repository
public class ModuleDao {
	
	private BeanPropertyRowMapper<Tmodule> rowMapper = new BeanPropertyRowMapper<Tmodule>(Tmodule.class);

	@Autowired
	private JdbcTemplate jdbc;
	
	public List<Tmodule> search(){
		String sql = "select * from t_module";
		List<Tmodule> t = jdbc.query(sql, rowMapper);
		System.out.println(t);
		return t;
	}
}
