package com.example.ormprefdemo.dbflow.db;

import androidx.annotation.Nullable;

import com.dbflow5.converter.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class ByteArrayConvert extends TypeConverter<byte[], List<byte[]>> {
    private static final int FEATURE_SIZE = 348 * 4;
    @Nullable
    @Override
    public byte[] getDBValue(@Nullable List<byte[]> list) {
        if (list == null || list.isEmpty())
            return null;
        else {
            int size = 0;
            for (byte[] bytes : list) {
                size += bytes.length;
            }
            byte[] res = new byte[size];
            int pos = 0;
            for (byte[] bytes : list) {
                for (byte b : bytes) {
                    res[pos++] = b;
                }
            }
            return res;
        }
    }

    @Nullable
    @Override
    public List<byte[]> getModelValue(@Nullable byte[] bytes) {
        int length = bytes.length / FEATURE_SIZE;
        List<byte[]> res = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            byte[] data = new byte[FEATURE_SIZE];
            for (int j = 0; j < FEATURE_SIZE; j++) {
                data[j] = bytes[i * FEATURE_SIZE + j];
            }
            res.add(data);
        }
        return res;
    }
}
