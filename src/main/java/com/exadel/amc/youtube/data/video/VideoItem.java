package com.exadel.amc.youtube.data.video;

import com.exadel.amc.youtube.data.AbstractItem;

public class VideoItem extends AbstractItem<VideoStatus, VideoStatistics, VideoSnippet> {

    @Override
    public String toString() {
        return "VideoItem [kind=" + getKind() + ", etag=" + getEtag() + ", id=" + getId() + ", status=" + getStatus()
                + ", statistics=" + getStatistics() + ", snippet=" + getSnippet() + "]";
    }

}
