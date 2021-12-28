package com.example.ormprefdemo.objectbox.db;

import java.util.Arrays;
import java.util.List;

import io.objectbox.converter.PropertyConverter;

public class StringListConverter implements PropertyConverter<List<String>, String> {
    @Override
    public List<String> convertToEntityProperty(String databaseValue) {
        if (databaseValue.contains(",")) {
            String[] val = databaseValue.split(",");
            return Arrays.asList(val);
        }
        return null;
    }

    @Override
    public String convertToDatabaseValue(List<String> entityProperty) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String entity : entityProperty) {
            stringBuilder.append(entity).append(",");
        }
        String res = stringBuilder.toString();
        return res.substring(0, res.length() - 1);
    }
}
