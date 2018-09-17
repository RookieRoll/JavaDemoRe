package com.kobold;

import com.kobold.Entities.Configuration;
import com.kobold.Service.OrmServiceImpl;
import com.kobold.Test.Entity.Area;
import com.kobold.api.OrmService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// write your code here
		//定义配置文件
		while (true){
			Configuration configuration = new Configuration("com.mysql.jdbc.Driver",
					"jdbc:mysql://47.106.100.79:3306/EmsDb","root","qh18723361304");
			OrmService orm = new OrmServiceImpl(configuration);
			String sql = "select * from `Area` where Id=:id";
			Map<String, Object> value = new HashMap<>();
			value.put("id", 7);
			List<Area> demo0 = orm.findAll(sql, value, Area.class);
			demo0.stream().forEach(m-> System.out.println(m.getName()));
		}


	}
}
