package com.kobold.Service;

import com.kobold.Convert.QueryConvert;
import com.kobold.api.QueryService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryServiceImpl<T> implements QueryService<T> {
	private Connection connection;

	public QueryServiceImpl(Connection connection) {
		this.connection = connection;
	}

	public List<T> findAll(String sql, Map<String, Object> objectMap, Class<T> tClass) {
		NamedParameterStatement preparedStatement = null;
		try {
			preparedStatement = new NamedParameterStatement(connection, sql);
			for (String key : objectMap.keySet()) {
				preparedStatement.setObject(key, objectMap.get(key));
			}
			ResultSet set = preparedStatement.executeQuery();
			return QueryConvert.ConvertToObjectList(set, tClass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public T findFirst(String sql, Map<String, Object> objectMap, Class<T> tClass) {
		return findAll(sql, objectMap, tClass).stream().findFirst().get();
	}
}