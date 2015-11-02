package com.exadel.amc.youtube.data.channel;

import com.exadel.amc.youtube.data.AbstractSnippet;

public class ChannelSnippet extends AbstractSnippet {

    @Override
    public String toString() {
        return "ChannelSnippet [title=" + getTitle() + ", description=" + getDescription() + ", publishedAt="
                + getPublishedAt() + ", localized=" + getLocalized() + ", thumbnails=" + getThumbnails() + ", tags="
                + getTags() + "]";
    }

}
