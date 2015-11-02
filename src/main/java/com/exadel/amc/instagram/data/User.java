package com.exadel.amc.instagram.data;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("username")
    private String userName;
    
    @SerializedName("profile_picture")
    private String profilePicture;
    
    private String id;
    
    @SerializedName("full_name")
    private String fullName;

    private String bio;
    private String website;
    
    private Counts counts;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Counts getCounts() {
        return counts;
    }

    public void setCounts(Counts counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", fullName=" + fullName + ", profilePicture="
                + profilePicture + ", bio=" + bio + ", website=" + website + ", counts=" + counts + "]";
    }
    
}
