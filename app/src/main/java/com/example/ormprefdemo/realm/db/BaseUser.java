package com.example.ormprefdemo.realm.db;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class BaseUser extends RealmObject {
    @PrimaryKey
    private Long id;
    @Index
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
//    private List<String> tagIds;
//    private List<String> tags;
//    private List<String> imageUrls;
//    private List<byte[]> features;  // face feature
    private RealmList<String> tagIds;
    private RealmList<String> tags;
    private RealmList<String> imageUrls;
    private RealmList<byte[]> features;
    private boolean featureExtracted;
    private long visitCount;
    private long syncId;
    private boolean hasFeatures;

    public BaseUser() {}

    public BaseUser(
            Long id,
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
            RealmList<String> tagIds,
            RealmList<String> tags,
            RealmList<String> imageUrls,
            RealmList<byte[]> features,
            boolean featureExtracted,
            long visitCount,
            long syncId,
            boolean hasFeatures
    ) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setTagIds(RealmList<String> tagIds) {
        this.tagIds = tagIds;
    }

    public RealmList<String> getTags() {
        return tags;
    }

    public void setTags(RealmList<String> tags) {
        this.tags = tags;
    }

    public RealmList<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(RealmList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<byte[]> getFeatures() {
        return features;
    }

    public void setFeatures(RealmList<byte[]> features) {
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

