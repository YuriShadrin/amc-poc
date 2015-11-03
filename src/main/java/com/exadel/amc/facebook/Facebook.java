package com.exadel.amc.facebook;

import java.io.IOException;
import java.util.Properties;

import org.nextlets.erc.ERCFactory;

import com.exadel.amc.facebook.rest.AccessTokenDeserializer;
import com.exadel.amc.facebook.rest.FacebookRestClient;

public class Facebook {

    private static final String FB_GRAPH_URL = "https://graph.facebook.com";
    private static final String KEY_PROPERTIES = "/keys/facebook_keys.properties";

    private static final String APP_SECRET_KEY = "app.secret";
    private static final String APP_ID_KEY = "app.id";

    private FacebookRestClient client;

    public Facebook() throws IOException {
        // load properties
        Properties props = new Properties();
        props.load(FacebookTest.class.getResourceAsStream(KEY_PROPERTIES));

        // init factory
        ERCFactory factory = ERCFactory.getInstance(FB_GRAPH_URL);

        // create REST client
        this.client = factory.createRestClient(FacebookRestClient.class);

        // get access token from FB
        String token = this.client.getAccessToken(props.getProperty(APP_ID_KEY).trim(),
                props.getProperty(APP_SECRET_KEY).trim());

        // add access token to auto-params
        factory.getAutoParams().put(AccessTokenDeserializer.ACCESS_TOKEN, token);
    }

    private String getFieldsString(String startDate, String endDate, int limit) {
        return "feed.until(" + endDate + ").since(" + startDate + ").limit(" + limit
                + "){likes{id,name,username,link,pic},comments{from,created_time,parent,likes{id,name},is_private,comment_count,like_count,is_hidden},created_time,updated_time,id,caption,from,shares}";
    }

    public String getShortInfo(String objId) {
        return client.getShortInfo(objId);
    }

    public String getLikes(String objId, String startDate, String endDate, int limit) {
        return client.getLikes(objId, getFieldsString(startDate, endDate, limit));
    }
}
