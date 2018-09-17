package com.kobold.Connection;

import com.kobold.Entities.Configuration;

public class ConnectionPoolUtils {
	private ConnectionPoolUtils() {
	}//私有静态方法

	private static ConnectionPool poolInstance = null;
	private static volatile Object object = new Object();

	public static ConnectionPool GetPoolInstance(Configuration configuration) {
		if (poolInstance == null) {
			synchronized (object) {
				if (poolInstance == null) {
					poolInstance = new ConnectionPool(configuration.getDriver(), configuration.getUrl(), configuration.getUser(), configuration.getPwd());
					try {
						poolInstance.createPool();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		return poolInstance;
	}

}
