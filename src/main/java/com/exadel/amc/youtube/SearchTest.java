package com.exadel.amc.youtube;

import java.io.IOException;
import java.util.Properties;

import org.nextlets.erc.ERCFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exadel.amc.facebook.FBClientTest;
import com.exadel.amc.youtube.data.channel.ChannelItem;
import com.exadel.amc.youtube.data.channel.ChannelSearchResult;
import com.exadel.amc.youtube.data.video.VideoItem;
import com.exadel.amc.youtube.data.video.VideoSearchResult;

/**
 * Create Google account
 * Go to https://console.developers.google.com/
 * Create new project (click "Enable and manage APIs" link) or choose the existing
 * Go to "APIS & auth -> APIs"
 * Look for "YouTube Data API" link, click it
 * Click "Enable API" button 
 * Go to "APIS & auth -> Credentials"
 * Add credentials -> API key -> Server key
 * Click "Create" button, get API key from opened popup window  
 */
public class SearchTest {

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static final String GOOGLEAPIS_URL = "https://content.googleapis.com/youtube/v3";

	static Logger log = LoggerFactory.getLogger(SearchTest.class);
	
	private static ERCFactory createFactory() throws IOException {
		Properties props = new Properties();
		props.load(FBClientTest.class.getResourceAsStream("/keys/youtube_keys.properties"));

		ERCFactory f = ERCFactory.getInstance(GOOGLEAPIS_URL);
		f.getConfiguration().setDateFormat(DATE_FORMAT);
		f.getAutoParams().put("key", props.getProperty("api.key").trim());
		return f;
	}

	private static YoutubeVideos createYoutubeVideos() throws IOException {
		return createFactory().createRestClient(YoutubeVideos.class);
	}

	private static YoutubeChannels createYoutubeChannels() throws IOException {
		return createFactory().createRestClient(YoutubeChannels.class);
	}

	public static void main(String[] args) throws Exception {
		
		String searches[];
		if (args.length == 0) {
			searches = new String[]{"tVIIgcIqoPw"};
		} else {
			searches = args;
		}
		
		YoutubeVideos gv = createYoutubeVideos();
		YoutubeChannels yc = createYoutubeChannels();
		
		for (String search : searches) {

			log.info(">>> {} <<<", search);
			
			VideoSearchResult vsr = gv.getVideoById(search);
			if (vsr.getPageInfo().getResultsPerPage() == 1) {
				
				for (VideoItem vi : vsr.getItems()) {
					log.info("Title: {}", vi.getSnippet().getTitle());
					log.info("ChannelId: {}", vi.getSnippet().getChannelId());
					log.info("ChannelTitle: {}", vi.getSnippet().getChannelTitle());
					log.info("PublishedAt: {}", vi.getSnippet().getPublishedAt());
					
					log.info("ViewCount: {}", vi.getStatistics().getViewCount());
					log.info("FavoriteCount: {}", vi.getStatistics().getFavoriteCount());
					log.info("LikeCount: {}", vi.getStatistics().getLikeCount());
					log.info("DislikeCount: {}", vi.getStatistics().getDislikeCount());
					log.info("CommentCount: {}", vi.getStatistics().getCommentCount());
					
					log.info("");
					
					ChannelSearchResult csr = yc.getChannelById(vi.getSnippet().getChannelId());
					if (csr.getPageInfo().getTotalResults() > 0) {
						log.info("Channel {} info:", vi.getSnippet().getChannelId());
						for (ChannelItem ci : csr.getItems()) {
							log.info("\tTitle: {}", ci.getSnippet().getTitle());
							log.info("\tDescription: {}", ci.getSnippet().getDescription() == null ? "" : ci.getSnippet().getDescription().replaceAll("\n", " "));
							log.info("\tPublishedAt: {}", ci.getSnippet().getPublishedAt());
							log.info("\tIsLinked: {}", ci.getStatus().getIsLinked());
							log.info("\tLongUploadsStatus: {}", ci.getStatus().getLongUploadsStatus());
							log.info("\tPrivacyStatus: {}", ci.getStatus().getPrivacyStatus());
						}
					}
				}
				
			}
			
			log.info("-----------------------");
		}
	}

}
