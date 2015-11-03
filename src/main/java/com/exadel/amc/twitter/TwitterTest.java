package com.exadel.amc.twitter;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exadel.amc.facebook.FacebookTest;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Register as developer on https://dev.twitter.com/ Go to
 * https://apps.twitter.com/ Create New App or choose the existing Look for
 * Consumer Key, click "manage keys and access tokens" link Generate Customer
 * Key and Secret Generate Access Token and Token Secret
 */
public class TwitterTest {

    static Logger log = LoggerFactory.getLogger(TwitterTest.class);

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.load(FacebookTest.class.getResourceAsStream("/keys/twitter_keys.properties"));

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(props.getProperty("consumer.key").trim())
                .setOAuthConsumerSecret(props.getProperty("consumer.secret").trim())
                .setOAuthAccessToken(props.getProperty("access.token").trim())
                .setOAuthAccessTokenSecret(props.getProperty("access.token.secret").trim());

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        String searches[];
        if (args.length == 0) {
            searches = new String[] { "ladygaga" };
        } else {
            searches = args;
        }

        lookupUsers(twitter, searches);
        log.info("");
        searchUsers(twitter, searches);
    }

    public static void lookupUsers(Twitter twitter, String[] searches) throws TwitterException {
        log.info("Lookup users: {}", Arrays.toString(searches));
        ResponseList<User> rl = twitter.lookupUsers(searches);
        for (int i = 0; i < rl.size(); i++) {
            User u = rl.get(i);

            log.info("");
            log.info("Id: {}", u.getId());
            log.info("Name: {}", u.getName());
            log.info("Screen name: {}", u.getScreenName());
            log.info("Description: {}", u.getDescription());
            log.info("AccessLevel: {}", u.getAccessLevel());
            log.info("CreatedAt: {}", u.getCreatedAt());
            log.info("FavouritesCount: {}", u.getFavouritesCount());
            log.info("FollowersCount: {}", u.getFollowersCount());
            log.info("FriendsCount: {}", u.getFriendsCount());
            log.info("ListedCount: {}", u.getListedCount());
            log.info("StatusesCount: {}", u.getStatusesCount());
            log.info("Lang: {}", u.getLang());
            log.info("Status: {}", u.getStatus());
            log.info("TimeZone: {}", u.getTimeZone());
            log.info("URL: {}", u.getURL());

            Status status = u.getStatus();
            if (status != null) {
                for (UserMentionEntity en : status.getUserMentionEntities()) {
                    log.info("\t" + en.getName());
                }
            }

            log.info("---------------------------------");

        }
    }

    public static void searchUsers(Twitter twitter, String[] searches) throws TwitterException {
        for (String search : searches) {

            Query query = new Query(search);

            Calendar cal = Calendar.getInstance();
            query.setUntil(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONDAY) + 1) + "-"
                    + cal.get(Calendar.DAY_OF_MONTH));
            cal.add(Calendar.DAY_OF_MONTH, -7);
            query.setSince(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONDAY) + 1) + "-"
                    + cal.get(Calendar.DAY_OF_MONTH));
            query.setCount(50);

            log.info("Search tweets: {}", query);

            QueryResult result;
            int n = 0;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    n++;
                    log.info("");
                    log.info("Id: {}", tweet.getId());
                    log.info("CreatedAt: {}", tweet.getCreatedAt());
                    log.info("CurrentUserRetweetId: {}", tweet.getCurrentUserRetweetId());
                    log.info("FavoriteCount: {}", tweet.getFavoriteCount());
                    log.info("GeoLocation: {}", tweet.getGeoLocation());
                    log.info("HashtagEntities: {}", Arrays.toString(tweet.getHashtagEntities()));
                    log.info("InReplyToScreenName: {}", tweet.getInReplyToScreenName());
                    log.info("InReplyToStatusId: {}", tweet.getInReplyToStatusId());
                    log.info("InReplyToUserId: {}", tweet.getInReplyToUserId());
                    log.info("Lang: {}", tweet.getLang());
                    log.info("MediaEntities: {}", Arrays.toString(tweet.getMediaEntities()));
                    log.info("Place: {}", tweet.getPlace());
                    log.info("RetweetCount: {}", tweet.getRetweetCount());
                    log.info("RetweetedStatus: {}", tweet.getRetweetedStatus());
                    log.info("Scopes: {}", tweet.getScopes());
                    log.info("Source: {}", tweet.getSource());
                    log.info("Text: {}", tweet.getText());
                    log.info("User: {}", tweet.getUser());

                    log.info("---------------------------------");
                }
            } while ((query = result.nextQuery()) != null && n < 50);
        }

    }

}
