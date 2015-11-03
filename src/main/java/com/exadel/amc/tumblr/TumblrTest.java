package com.exadel.amc.tumblr;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exadel.amc.facebook.FacebookTest;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.User;

/**
 * Goto https://www.tumblr.com/docs/en/api/v2 Look for "register an application" link, click it
 * Register your application
 * On page https://www.tumblr.com/docs/en/api/v2 click link "API Console" (or goto https://api.tumblr.com/console/calls/user/info)
 * On API Console you'll see consumer.key, consumer.secret, access.token and access.token.secret
 * Configure tumblr_keys.properties file
 * 
 * @author yshadrin
 *
 */
public class TumblrTest {

    static Logger log = LoggerFactory.getLogger(TumblrTest.class);

    static JumblrClient makeClient() throws IOException {
        Properties props = new Properties();
        props.load(FacebookTest.class.getResourceAsStream("/keys/tumblr_keys.properties"));

        JumblrClient client = new JumblrClient(
                props.getProperty("consumer.key").trim(),
                props.getProperty("consumer.secret").trim());

        client.setToken(
                props.getProperty("access.token").trim(),
                props.getProperty("access.token.secret").trim());

        return client;
    }

    public static void main(String[] args) throws Exception {

        JumblrClient client = makeClient();

        User user = client.user();
        log.info(">>> {} <<<", user.getName());
        log.info("FollowingCount: {}", user.getFollowingCount());
        log.info("LikeCount: {}", user.getLikeCount());
        
        List<Blog>blogs = user.getBlogs();
        log.info("");
        log.info("BlogsCount: {}", blogs.size());
        for (Blog blog : blogs) {
            log.info("BlogName: {}, title: {}, postCount: {}, followersCount: {}, likeCount: {}",
                    blog.getName(), blog.getTitle(), blog.getPostCount(), blog.getFollowersCount(), blog.getLikeCount());    
        }
        
        
        List<Post> posts = client.userDashboard();
        log.info("");
        log.info("PostsCount: {}", posts.size());
        for (Post post : posts) {
            log.info("BlogName: {}, Type: {}, AuthorId: {}, NoteCount: {}, Tags: {}", post.getBlogName(), post.getType(), post.getAuthorId(), post.getNoteCount(), post.getTags());
        }
        


    }
}
