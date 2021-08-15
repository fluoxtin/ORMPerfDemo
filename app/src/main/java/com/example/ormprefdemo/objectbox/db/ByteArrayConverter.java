package com.example.ormprefdemo.objectbox.db;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.converter.PropertyConverter;

public class ByteArrayConverter implements PropertyConverter<List<byte[]>, byte[]> {
    private static final int FEATURE_SIZE = 348 * 4;
    @Override
    public List<byte[]> convertToEntityProperty(byte[] databaseValue) {
        int length = databaseValue.length / FEATURE_SIZE;
        List<byte[]> res = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            byte[] data = new byte[FEATURE_SIZE];
            for (int j = 0; j < FEATURE_SIZE; j++) {
                data[j] = databaseValue[i * FEATURE_SIZE + j];
            }
            res.add(data);
        }
        return res;
    }

    @Override
    public byte[] convertToDatabaseValue(List<byte[]> entityProperty) {
        if (entityProperty == null) {
            return null;
        } else {
            int size = 0;
            for (byte[] entity : entityProperty) {
                size += entity.length;
            }
            byte[] res = new byte[size];
            int pos = 0;
            for (byte[] entities : entityProperty) {
                for (byte b : entities) {
                    res[pos++] = b;
                }
            }
            return res;
        }
    }
}
