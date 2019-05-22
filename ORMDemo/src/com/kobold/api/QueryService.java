package com.kobold.api;

import java.util.List;
import java.util.Map;

public interface QueryService<T> {
	List<T> findAll(String sql, Map<String, Object> objectMap, Class<T> tClass);

	T findFirst(String sql, Map<String, Object> objectMap, Class<T> tClass);
}
