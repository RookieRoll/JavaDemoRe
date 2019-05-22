package com.kobold.api;

import java.util.Map;

public interface ExecuteService {
	void insert(Object object);

	void excuteSql(String sql, Map<String, Object> map);
}
