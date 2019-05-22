import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class SqlUtil {
	/*
	 将对象转化为相应的sql
	 */
	public static String convertToSqlInsert(Object obj) {
		if (obj == null) return "";
		String tablename = obj.getClass().getSimpleName();
		Field[] fields = obj.getClass().getDeclaredFields();

		List<String> prop = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		try {
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				prop.add(fields[i].getName());
				values.add("'" + fields[i].get(obj).toString() + "'");
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		String sql = "INSERT INTO " + tablename + "( " + String.join(",", prop) + ") VALUES (" + String.join(",", values) + ");";
		System.out.println(sql);
		return sql;
	}

	/*
	将集合转换为对应的sql
	 */
	public static <T> String listConvertToSqlInsert(List<T> objs) {
		if (objs == null || objs.size() == 0)
			return "";
		String tableName = objs.get(0).getClass().getName();
		Field[] fields = objs.get(0).getClass().getDeclaredFields();

		List<String> sqls = new ArrayList<>();
		try {
			for (int i = 0; i < objs.size(); i++) {
				List<String> prop = new ArrayList<>();
				List<String> values = new ArrayList<>();
				final int const_i = i;
				Stream.of(fields).forEach(m -> {
					m.setAccessible(true);
					prop.add(m.getName());
					try {
						values.add("'" + m.get(objs.get(const_i)).toString() + "'");
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				});

				String sql = "INSERT INTO " + tableName + "( " + String.join(",", prop) + ") VALUES (" + String.join(",", values) + ")";
				sqls.add(sql);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return String.join(";\n", sqls);
	}

	/*
	修改数据
	 */
	public static String ConvertToSqlUpdate(Object obj) throws Exception {
		if (obj == null) return "";
		String tablename = obj.getClass().getSimpleName();
		Field[] fields = obj.getClass().getDeclaredFields();

		List<String> prop = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		String key = "";
		String keyValue = "";
		try {
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				PrimaryKeyAnnotation annotation = fields[i].getAnnotation(PrimaryKeyAnnotation.class);
				if (annotation != null) {
					key = fields[i].getName();
					keyValue = fields[i].get(obj).toString();
				} else {
					prop.add(fields[i].getName());
					values.add("'" + fields[i].get(obj).toString() + "'");
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		if (key.isEmpty() || keyValue.isEmpty())
			throw new Exception("关键字不能为空");
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ").append(tablename).append(" SET ");
		for (int i = 0; i < prop.size() - 1; i++) {
			builder.append(prop.get(i) + "= '" + values.get(i) + "', ");
		}
		builder.append(prop.get(prop.size() - 1) + "=" + values.get(prop.size() - 1));
		String sql = builder.append("where ").append(key + "='" + keyValue + "'").toString();
		System.out.println(sql);
		return sql;

	}


}
