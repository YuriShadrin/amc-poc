package com.exadel.amc.youtube.rest;

import org.nextlets.erc.ERCEndpoint;
import org.nextlets.erc.ERCParam;

import com.exadel.amc.youtube.data.channel.ChannelSearchResult;

public interface YoutubeChannelsRestClient {

    @ERCEndpoint(endpoint = "/channels?part=id,snippet,statistics,status")
    ChannelSearchResult getChannelById(@ERCParam(name = "id") String id);

}
