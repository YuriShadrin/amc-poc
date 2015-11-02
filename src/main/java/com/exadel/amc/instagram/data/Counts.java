package com.exadel.amc.instagram.data;

import com.google.gson.annotations.SerializedName;

public class Counts {

    private Long media;
    
    @SerializedName("followed_by")
    private Long followedBy;
    
    private Long follows;

    public Long getMedia() {
        return media;
    }

    public void setMedia(Long media) {
        this.media = media;
    }

    public Long getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(Long followedBy) {
        this.followedBy = followedBy;
    }

    public Long getFollows() {
        return follows;
    }

    public void setFollows(Long follows) {
        this.follows = follows;
    }

    @Override
    public String toString() {
        return "Counts [media=" + media + ", followedBy=" + followedBy + ", follows=" + follows + "]";
    }
}
