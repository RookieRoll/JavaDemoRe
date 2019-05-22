package com.kobold.Convert;

import com.kobold.api.TypeConvertor;

public class MySqlConvert implements TypeConvertor {
	@Override
	public String databaseTypeToJavaType(String column) {
		switch (column.toLowerCase()) {
			case "varchar":
			case "char":
				return "String";
			case "smallint":
			case "int":
			case "tinyint":
				return "Integer";
			case "bigint":
				return "Long";
			case "double":
				return "Double";
			case "float":
				return "Double";
			case "clob":
				return "java.sql.Clob";
			case "blob":
				return "java.sql.Blob";
			case "date":
				return "java.sql.Date";
			case "time":
				return "java.sql.Time";
			case "timestamp":
				return "java.sql.Timestamp";
			default:
				return null;
		}
	}

	@Override
	public String JavaToDatabaseTyppe(String column) {
		return null;
	}
}
