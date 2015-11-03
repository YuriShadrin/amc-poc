package com.exadel.amc.instagram;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exadel.amc.instagram.data.User;
import com.google.gson.Gson;

import facebook4j.internal.org.json.JSONObject;

/**
 * Create an account in Instagram (via mobile app)
 * Goto https://instagram.com/developer
 * Register a new client
 * Generate access token: follow instruction on https://instagram.com/developer/authentication
 */
public class InstagramTest {

    private static final String SEARCH_STRING = "ladygaga";
    static Logger log = LoggerFactory.getLogger(InstagramTest.class);

    public static void main(String[] args) throws Exception {

        String searches[];
        if (args.length == 0) {
            searches = new String[] { SEARCH_STRING };
        } else {
            searches = args;
        }

        Instagram inst = new Instagram();
        Gson gson = new Gson();

        for (String search : searches) {

            log.info(">>> {} <<<", search);
            List<User> users = inst.searchUsers(search);
            int count = 0;
            for (User usr : users) {
                try {
                    usr = inst.getUser(usr.getId());
                    String json = gson.toJson(usr);
                    log.info(new JSONObject(json).toString(4));
                    log.info("");
                    if (count++ > 5) {
                        break;
                    }
                } catch (Exception ex) {
                    log.error("ERROR: Could not get user {}: {}.", usr.getUserName(), ex.getMessage());
                    log.info("");
                }
            }
            log.info("-------------------------------------------");
        }
    }
}
