package com.example.ormprefdemo.dbflow.db;

import androidx.annotation.Nullable;

import com.dbflow5.converter.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class StringListConvert extends TypeConverter<String, List<String>> {
    @Nullable
    @Override
    public String getDBValue(@Nullable List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();
        if (strings == null || strings.isEmpty()) {
            return "";
        } else {
            for (String s : strings) {
                stringBuilder.append(s).append(",");
            }
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }
    }

    @Nullable
    @Override
    public List<String> getModelValue(@Nullable String s) {
        String[] values= s.split(",");
        if (values.length < 2)
            return null;
        else {
            return Arrays.asList(values);
        }
    }
}
