package com.example.ormprefdemo.mmkv.db;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BaseUser implements Parcelable {
    private Long id;
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
    private List<String> tagIds;
    private List<String> tags;
    private List<String> imageUrls;
//    private List<byte[]> featuress;  // face feature
    private List<Feature> features;
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
            List<String> tagIds,
            List<String> tags,
            List<String> imageUrls,
            List<Feature> features,
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

    protected BaseUser(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        personId = in.readString();
        externalId = in.readString();
        gender = in.readString();
        age = in.readInt();
        cardNumber = in.readString();
        identityNumber = in.readString();
        personName = in.readString();
        groupName = in.readString();
        description = in.readString();
        payload = in.readString();
        tagIds = in.createStringArrayList();
        tags = in.createStringArrayList();
        imageUrls = in.createStringArrayList();
        features = in.createTypedArrayList(Feature.CREATOR);
        featureExtracted = in.readByte() != 0;
        visitCount = in.readLong();
        syncId = in.readLong();
        hasFeatures = in.readByte() != 0;
    }

    public static final Creator<BaseUser> CREATOR = new Creator<BaseUser>() {
        @Override
        public BaseUser createFromParcel(Parcel in) {
            return new BaseUser(in);
        }

        @Override
        public BaseUser[] newArray(int size) {
            return new BaseUser[size];
        }
    };

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

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(personId);
        parcel.writeString(externalId);
        parcel.writeString(gender);
        parcel.writeInt(age);
        parcel.writeString(cardNumber);
        parcel.writeString(identityNumber);
        parcel.writeString(personName);
        parcel.writeString(groupName);
        parcel.writeString(description);
        parcel.writeString(payload);
        parcel.writeStringList(tagIds);
        parcel.writeStringList(tags);
        parcel.writeStringList(imageUrls);
        parcel.writeList(features);
        parcel.writeByte((byte) (featureExtracted ? 1 : 0));
        parcel.writeLong(visitCount);
        parcel.writeLong(syncId);
        parcel.writeByte((byte) (hasFeatures ? 1 : 0));
    }

    @Override
    public @NotNull String toString() {
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
                '}';
    }
}
