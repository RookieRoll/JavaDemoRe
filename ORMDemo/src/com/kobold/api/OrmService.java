package com.kobold.api;

import java.util.List;
import java.util.Map;

public interface OrmService<T> {
	List<T> findAll(String sql, Map<String, Object> objectMap, Class<T> tClass);

	T findFirst(String sql, Map<String, Object> objectMap, Class<T> tClass);

	void insert(Object object);

	void excuteSql(String sql, Map<String, Object> map);

	void beginTransaction();

	void commitTransaction();

	void rollBackTransaction();
}
