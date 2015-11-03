package com.exadel.amc.youtube.rest;

import org.nextlets.erc.ERCEndpoint;
import org.nextlets.erc.ERCParam;

import com.exadel.amc.youtube.data.video.VideoSearchResult;

public interface YoutubeVideosRestClient {

    @ERCEndpoint(endpoint = "/videos?part=id,snippet,statistics,status")
    VideoSearchResult getVideoById(@ERCParam(name = "id") String id);

    @ERCEndpoint(endpoint = "/videos/getRating")
    String getRating(@ERCParam(name = "id") String id);
}
