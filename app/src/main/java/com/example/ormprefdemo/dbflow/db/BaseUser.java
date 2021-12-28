package com.example.ormprefdemo.dbflow.db;

import com.dbflow5.annotation.Column;
import com.dbflow5.annotation.PrimaryKey;
import com.dbflow5.annotation.Table;
import com.dbflow5.structure.BaseModel;

import java.util.List;

@Table(database = MyDatabase.class)
public class BaseUser extends BaseModel {
    @PrimaryKey(autoincrement = true)
    public long id;
    @Column
    public String personId;
    @Column
    public String externalId;
    @Column
    public String gender;
    @Column
    public int age;
    @Column
    public String cardNumber;
    @Column
    public String identityNumber;
    @Column
    public String personName;
    @Column
    public String groupName;
    @Column
    public String description;
    @Column
    public String payload;
    @Column(typeConverter = StringListConvert.class)
    public List<String> tagIds;
    @Column(typeConverter = StringListConvert.class)
    public List<String> tags;
    @Column(typeConverter = StringListConvert.class)
    public List<String> imageUrls;
    @Column(typeConverter = ByteArrayConvert.class)
    public List<byte[]> features;
    @Column
    public boolean featureExtracted;
    @Column
    public long visitCount;
    @Column
    public long syncId;
    @Column
    public boolean hasFeatures;
    @Column
    public boolean test;

    public BaseUser() {

    }

    public BaseUser(String personId,
                    String externalId,
                    String gender,
                    int age,
                    String cardNumber,
                    String identityNumber,
                    String personName,
                    String groupName,
                    String description,
                    String payload,
                    List<String> tagIds,
                    List<String> tags,
                    List<String> imageUrls, List<byte[]> features,
                    boolean featureExtracted,
                    long visitCount,
                    long syncId,
                    boolean hasFeatures,
                    boolean test) {
        this.personId = personId;
        this.externalId = externalId;
        this.gender = gender;
        this.age = age;
        this.cardNumber = cardNumber;
        this.identityNumber = identityNumber;
        this.personName = personName;
        this.groupName = groupName;
        this.description = description;
        this.payload = payload;
        this.tagIds = tagIds;
        this.tags = tags;
        this.imageUrls = imageUrls;
        this.features = features;
        this.featureExtracted = featureExtracted;
        this.visitCount = visitCount;
        this.syncId = syncId;
        this.hasFeatures = hasFeatures;
        this.test = test;
    }

    @Override
    public String toString() {
        return "BaseUser{" +
                "id=" + id +
                ", personId='" + personId + '\'' +
                ", externalId='" + externalId + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", cardNumber='" + cardNumber + '\'' +
                ", identityNumber='" + identityNumber + '\'' +
                ", personName='" + personName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                ", payload='" + payload + '\'' +
                ", tagIds=" + tagIds +
                ", tags=" + tags +
                ", imageUrls=" + imageUrls +
                ", features=" + features +
                ", featureExtracted=" + featureExtracted +
                ", visitCount=" + visitCount +
                ", syncId=" + syncId +
                ", hasFeatures=" + hasFeatures +
                ", test=" + test +
                '}';
    }
}
