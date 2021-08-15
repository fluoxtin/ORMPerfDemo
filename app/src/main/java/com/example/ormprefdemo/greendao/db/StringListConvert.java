package com.example.ormprefdemo.greendao.db;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Arrays;
import java.util.List;

public class StringListConvert implements PropertyConverter<List<String>, String> {
    @Override  // 转换成对象属性
    public List<String> convertToEntityProperty(String databaseValue) {
        List<String> strings = null;
        if (databaseValue.contains(",")) {
            String[] val = databaseValue.split(",");
            return Arrays.asList(val);
        }
        return null;
    }

    @Override
    public String convertToDatabaseValue(List<String> entityProperty) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : entityProperty) {
            stringBuilder.append(s).append(",");
        }
        String res = stringBuilder.toString();
        return res.substring(0, res.length() - 1);
    }
}
