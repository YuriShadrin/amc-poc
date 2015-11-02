package com.exadel.amc.youtube.data.channel;

import com.exadel.amc.youtube.data.AbstractItem;

public class ChannelItem extends AbstractItem<ChannelStatus, ChannelStatistics, ChannelSnippet> {

    @Override
    public String toString() {
        return "ChannelItem [id=" + getId() + ", kind=" + getKind() + ", etag=" + getEtag() + ", status=" + getStatus()
                + ", statistics=" + getStatistics() + ", snippet=" + getSnippet() + "]";
    }

}
