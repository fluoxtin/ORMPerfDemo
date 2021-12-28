package com.example.ormprefdemo.objectbox.db;


import java.util.List;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class BaseUser {
    @Id(assignable = true)
    public long id;
    private String personId;
    private String externalId;
    private String gender;
    private int age;
    private String cardNumber;
    private String identityNumber;
    private String personName;
    private String groupName;
    private String description;
    private String payload;
    @Convert(
            converter = StringListConverter.class,
            dbType = String.class
    )
    private List<String> tagIds;
    @Convert(
            converter = StringListConverter.class,
            dbType = String.class
    )
    private List<String> tags;
    @Convert(
            converter = StringListConverter.class,
            dbType = String.class
    )
    private List<String> imageUrls;
    @Convert(
            converter = ByteArrayConverter.class,
            dbType = byte[].class
    )
    private List<byte[]> features;  // face feature
    private boolean featureExtracted;
    private long visitCount;
    private long syncId;
    private boolean hasFeatures;

    public BaseUser() {}

    public BaseUser(
            String personId,
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
            List<String> imageUrls,
            List<byte[]> features,
            boolean featureExtracted,
            long visitCount,
            long syncId,
            boolean hasFeatures
    ) {
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
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<byte[]> getFeatures() {
        return features;
    }

    public void setFeatures(List<byte[]> features) {
        this.features = features;
    }

    public boolean isFeatureExtracted() {
        return featureExtracted;
    }

    public void setFeatureExtracted(boolean featureExtracted) {
        this.featureExtracted = featureExtracted;
    }

    public long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(long visitCount) {
        this.visitCount = visitCount;
    }

    public long getSyncId() {
        return syncId;
    }

    public void setSyncId(long syncId) {
        this.syncId = syncId;
    }

    public boolean isHasFeatures() {
        return hasFeatures;
    }

    public void setHasFeatures(boolean hasFeatures) {
        this.hasFeatures = hasFeatures;
    }

}
