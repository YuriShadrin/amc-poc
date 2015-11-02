package com.exadel.amc.instagram;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.nextlets.erc.ERCFactory;

import com.exadel.amc.facebook.FBClientTest;
import com.exadel.amc.instagram.data.User;
import com.exadel.amc.instagram.data.UserGetResult;
import com.exadel.amc.instagram.data.UsersSearchResult;
import com.exadel.amc.instagram.rest.InstagramRestClient;

public class Instagram {

    private InstagramRestClient client;

    private Instagram() {
    }

    public static Instagram getInstance() throws IOException {
        Properties props = new Properties();
        props.load(FBClientTest.class.getResourceAsStream("/keys/instagram_keys.properties"));
        Instagram inst = new Instagram();
        ERCFactory f = ERCFactory.getInstance("https://api.instagram.com/v1");
        f.getAutoParams().put("access_token", props.getProperty("access.token").trim());
        inst.client = f.createRestClient(InstagramRestClient.class);
        return inst;
    }

    public List<User> searchUsers(String query) {
        UsersSearchResult usr = client.searchUsers(query);
        if (usr.getMeta().getCode() == 200) {
            return usr.getData();
        } else {
            throw new RuntimeException(usr.getMeta().toString());
        }
    }

    public User getUser(String id) {
        UserGetResult usr = client.getUser(id);
        if (usr.getMeta().getCode() == 200) {
            return usr.getData();
        } else {
            throw new RuntimeException(usr.getMeta().toString());
        }
    }

}
