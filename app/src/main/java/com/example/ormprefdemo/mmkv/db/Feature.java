package com.example.ormprefdemo.mmkv.db;

import android.os.Parcel;
import android.os.Parcelable;

public class Feature implements Parcelable {
    byte[] bytes1;

    public Feature() {}

    public Feature(byte[] bytes1) {
        this.bytes1 = bytes1;
    }

    protected Feature(Parcel in) {
        bytes1 = in.createByteArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(bytes1);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Feature> CREATOR = new Creator<Feature>() {
        @Override
        public Feature createFromParcel(Parcel in) {
            return new Feature(in);
        }

        @Override
        public Feature[] newArray(int size) {
            return new Feature[size];
        }
    };

    public byte[] getBytes1() {
        return bytes1;
    }

    public void setBytes1(byte[] bytes1) {
        this.bytes1 = bytes1;
    }
}
