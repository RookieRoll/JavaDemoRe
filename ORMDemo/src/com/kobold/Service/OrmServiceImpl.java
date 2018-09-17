package com.kobold.Service;

import com.kobold.Entities.Configuration;
import com.kobold.api.ExecuteService;
import com.kobold.api.QueryService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrmServiceImpl<T> implements com.kobold.api.OrmService<T> {
	private ExecuteService executeService;
	private QueryService<T> queryService;
	private Connection connection;

	public OrmServiceImpl(Configuration configuration) {
		connection = ConfigurationService.getConnect(configuration);
		executeService = new ExecuteServiceImpl(connection);
		queryService = new QueryServiceImpl<>(connection);
	}

	public void beginTransaction() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void commitTransaction() {
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollBackTransaction() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<T> findAll(String sql, Map<String, Object> objectMap, Class<T> tClass) {
		List<T> list = queryService.findAll(sql, objectMap, tClass);
		ConfigurationService.returnConnection(connection);
		return list;
	}

	@Override
	public T findFirst(String sql, Map<String, Object> objectMap, Class<T> tClass) {
		T t = queryService.findFirst(sql, objectMap, tClass);
		ConfigurationService.returnConnection(connection);
		return t;
	}

	@Override
	public void insert(Object object) {
		executeService.insert(object);
		ConfigurationService.returnConnection(connection);
	}

	@Override
	public void excuteSql(String sql, Map<String, Object> map) {
		executeService.excuteSql(sql, map);
		ConfigurationService.returnConnection(connection);
	}


}
