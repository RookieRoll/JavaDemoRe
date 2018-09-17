package com.kobold.Service;

import com.kobold.Connection.ConnectionPool;
import com.kobold.Connection.ConnectionPoolUtils;
import com.kobold.Entities.Configuration;

import java.sql.Connection;
import java.sql.SQLException;


public class ConfigurationService {

	private static ConnectionPool pool;

	public static Connection getConnect(Configuration configuration) {
		pool = ConnectionPoolUtils.GetPoolInstance(configuration);
		try {
			return pool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void returnConnection(Connection con) {
		pool.returnConnection(con);
	}

}
