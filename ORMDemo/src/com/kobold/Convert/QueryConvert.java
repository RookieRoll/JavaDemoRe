package com.kobold.Convert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryConvert {
    public static <T> List<T> ConvertToObjectList(ResultSet rs,Class<T> tClass) {
        Class clazz =tClass;
        var paramList = clazz.getDeclaredFields();
        List<T> list = new ArrayList<>();
        try {
            while(rs.next()){
                T obj = (T) clazz.getDeclaredConstructor().newInstance();
                for (Field field : paramList) {
                    field.setAccessible(true);
                    Object value=(rs.getObject(field.getName()));
                    field.set(obj,value);
                }
                list.add(obj);
            }
            rs.close();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
