package com.kobold.Service;

import com.kobold.api.ExecuteService;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ExecuteServiceImpl implements ExecuteService {
	private Connection connection;

	public ExecuteServiceImpl(Connection connection) {
		this.connection = connection;
	}

	public void insert(Object object) {
		Class clazz = object.getClass();
		String sql = getInsertSql(clazz);
		Field[] fields = clazz.getDeclaredFields();
		NamedParameterStatement preparedStatement = null;
		try {
			preparedStatement = new NamedParameterStatement(connection, sql);
			for (Field field : fields) {
				field.setAccessible(true);
				preparedStatement.setObject(field.getName(), field.get(object));
			}
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	public void excuteSql(String sql, Map<String, Object> map) {
		try {
			NamedParameterStatement parameterStatement = new NamedParameterStatement(connection, sql);
			for (String key : map.keySet()) {
				parameterStatement.setObject(key, map.get(key));
			}
			parameterStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String getInsertSql(Class clazz) {
		String tableName = clazz.getSimpleName();
		Field[] fields = clazz.getDeclaredFields();
		var sql = "Insert into " + tableName + " ( ";
		sql += Arrays.stream(fields).map((m) -> m.getName()).collect(Collectors.joining(","));
		sql += ") values(";
		sql += Arrays.stream(fields).map((m) -> ":" + m.getName()).collect(Collectors.joining(","));
		sql += ")";
		return sql;
	}
}
