package com.exadel.amc.youtube.data.channel;

import java.util.List;

import com.exadel.amc.youtube.data.AbstractSearchResult;

public class ChannelSearchResult extends AbstractSearchResult<ChannelItem> {

    private List<ChannelItem> items;

    @Override
    public List<ChannelItem> getItems() {
        return items;
    }

    public void setItems(List<ChannelItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ChannelSearchResult [kind=" + getKind() + ", etag=" + getEtag() + ", pageInfo=" + getPageInfo()
                + ", items=" + getItems() + "]";
    }

}
