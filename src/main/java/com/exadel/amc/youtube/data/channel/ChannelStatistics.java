package com.exadel.amc.youtube.data.channel;

import com.exadel.amc.youtube.data.AbstractStatistics;

public class ChannelStatistics extends AbstractStatistics {

    private Long subscriberCount;
    private Boolean hiddenSubscriberCount;
    private Long videoCount;

    public Long getSubscriberCount() {
        return subscriberCount;
    }

    public void setSubscriberCount(Long subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

    public Boolean getHiddenSubscriberCount() {
        return hiddenSubscriberCount;
    }

    public void setHiddenSubscriberCount(Boolean hiddenSubscriberCount) {
        this.hiddenSubscriberCount = hiddenSubscriberCount;
    }

    public Long getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Long videoCount) {
        this.videoCount = videoCount;
    }

    @Override
    public String toString() {
        return "ChannelStatistics [viewCount=" + getViewCount() + ", commentCount=" + getCommentCount()
                + ", subscriberCount=" + getSubscriberCount() + ", hiddenSubscriberCount=" + getHiddenSubscriberCount()
                + ", videoCount=" + getVideoCount() + "]";
    }

}
