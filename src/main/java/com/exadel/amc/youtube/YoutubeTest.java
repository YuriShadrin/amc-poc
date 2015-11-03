package com.exadel.amc.youtube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exadel.amc.youtube.data.channel.ChannelItem;
import com.exadel.amc.youtube.data.channel.ChannelSearchResult;
import com.exadel.amc.youtube.data.video.VideoItem;
import com.exadel.amc.youtube.data.video.VideoSearchResult;

/**
 * Create Google account Go to https://console.developers.google.com/ Create new
 * project (click "Enable and manage APIs" link) or choose the existing Go to
 * "APIS & auth -> APIs" Look for "YouTube Data API" link, click it Click
 * "Enable API" button Go to "APIS & auth -> Credentials" Add credentials -> API
 * key -> Server key Click "Create" button, get API key from opened popup window
 */
public class YoutubeTest {

    private static final String SEARCH_STRING = "tVIIgcIqoPw";
    private static Logger log = LoggerFactory.getLogger(YoutubeTest.class);

    public static void main(String[] args) throws Exception {

        String searches[];
        if (args.length == 0) {
            searches = new String[] { SEARCH_STRING };
        } else {
            searches = args;
        }

        Youtube yt = new Youtube();

        for (String search : searches) {

            log.info(">>> {} <<<", search);

            VideoSearchResult vsr = yt.getVideoById(search);
            if (vsr.getPageInfo().getResultsPerPage() == 1) {

                for (VideoItem vi : vsr.getItems()) {

                    print(vi);
                    log.info("");

                    ChannelSearchResult csr = yt.getChannelById(vi.getSnippet().getChannelId());
                    if (csr.getPageInfo().getTotalResults() > 0) {
                        log.info("Channel {} info:", vi.getSnippet().getChannelId());
                        for (ChannelItem ci : csr.getItems()) {
                            print(ci);
                        }
                    }
                }

            }

            log.info("-----------------------");
        }
    }

    private static void print(VideoItem vi) {
        log.info("Title: {}", vi.getSnippet().getTitle());
        log.info("ChannelId: {}", vi.getSnippet().getChannelId());
        log.info("ChannelTitle: {}", vi.getSnippet().getChannelTitle());
        log.info("PublishedAt: {}", vi.getSnippet().getPublishedAt());

        log.info("ViewCount: {}", vi.getStatistics().getViewCount());
        log.info("FavoriteCount: {}", vi.getStatistics().getFavoriteCount());
        log.info("LikeCount: {}", vi.getStatistics().getLikeCount());
        log.info("DislikeCount: {}", vi.getStatistics().getDislikeCount());
        log.info("CommentCount: {}", vi.getStatistics().getCommentCount());
    }

    private static void print(ChannelItem ci) {
        log.info("\tTitle: {}", ci.getSnippet().getTitle());
        log.info("\tDescription: {}",
                ci.getSnippet().getDescription() == null ? "" : ci.getSnippet().getDescription().replaceAll("\n", " "));
        log.info("\tPublishedAt: {}", ci.getSnippet().getPublishedAt());
        log.info("\tIsLinked: {}", ci.getStatus().getIsLinked());
        log.info("\tLongUploadsStatus: {}", ci.getStatus().getLongUploadsStatus());
        log.info("\tPrivacyStatus: {}", ci.getStatus().getPrivacyStatus());
    }

}
