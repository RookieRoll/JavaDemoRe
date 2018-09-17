package com.kobold.Entities;

import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private String driver;
    private String url;
    private String user;
    private String pwd;

    	public Configuration() {
		this("db.properties");
	}

	public Configuration(String filePath) {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setDriver(properties.getProperty("driver"));
        this.setPwd(properties.getProperty("Pwd"));
        this.setUrl(properties.getProperty("url"));
        this.setUser(properties.getProperty("user"));
	}

	public Configuration(String driver, String url, String user, String pwd) {
        this.setDriver(driver);
        this.setPwd(pwd);
        this.setUrl(url);
        this.setUser(user);
	}

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


}
