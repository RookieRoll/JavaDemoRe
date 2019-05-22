package com.springbootexample.bootdemo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class MyWebApplicationBuilder extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 设置启动类,用于独立tomcat运行的入口
		return builder.sources(MyWebApplicationBuilder.class);
	}
}
