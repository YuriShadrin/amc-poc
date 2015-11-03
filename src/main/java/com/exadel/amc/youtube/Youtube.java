package com.exadel.amc.youtube;

import java.io.IOException;
import java.util.Properties;

import org.nextlets.erc.ERCFactory;

import com.exadel.amc.facebook.FacebookTest;
import com.exadel.amc.youtube.data.channel.ChannelSearchResult;
import com.exadel.amc.youtube.data.video.VideoSearchResult;
import com.exadel.amc.youtube.rest.YoutubeChannelsRestClient;
import com.exadel.amc.youtube.rest.YoutubeVideosRestClient;

public class Youtube {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String GOOGLEAPIS_URL = "https://content.googleapis.com/youtube/v3";

    private YoutubeVideosRestClient videosClient;
    private YoutubeChannelsRestClient channelsClient;

    public Youtube() throws IOException {
        Properties props = new Properties();
        props.load(FacebookTest.class.getResourceAsStream("/keys/youtube_keys.properties"));

        ERCFactory f = ERCFactory.getInstance(GOOGLEAPIS_URL);
        f.getConfiguration().setDateFormat(DATE_FORMAT);
        f.getAutoParams().put("key", props.getProperty("api.key").trim());

        this.videosClient = f.createRestClient(YoutubeVideosRestClient.class);
        this.channelsClient = f.createRestClient(YoutubeChannelsRestClient.class);
    }

    public VideoSearchResult getVideoById(String id) {
        return videosClient.getVideoById(id);
    }

    public String getRating(String id) {
        return videosClient.getRating(id);
    }

    public ChannelSearchResult getChannelById(String id) {
        return channelsClient.getChannelById(id);
    }

}
