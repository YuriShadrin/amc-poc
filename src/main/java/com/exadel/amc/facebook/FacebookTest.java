package com.exadel.amc.facebook;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * Register as developer on https://developers.facebook.com Go to
 * "My Apps -> Add a New App" or "My Apps -> choose the existing App" Get App
 * ID, App Secret
 * 
 * If you need to get AUTH TOKEN, goto
 * https://developers.facebook.com/tools/explorer/
 */
public class FacebookTest {

    private static final int LIMIT = 10;
    private static final String SEARCH_STRING = "ledzeppelin";

    private static Logger log = LoggerFactory.getLogger(FacebookTest.class);

    public static void main(String[] args) throws Exception {
        Facebook fb = new Facebook();

        Calendar cal = Calendar.getInstance();
        String endDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONDAY) + 1) + "-"
                + cal.get(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.DAY_OF_MONTH, -7);
        String startDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONDAY) + 1) + "-"
                + cal.get(Calendar.DAY_OF_MONTH);

        String searches[];
        if (args.length == 0) {
            searches = new String[] { SEARCH_STRING };
        } else {
            searches = args;
        }

        for (String search : searches) {
            showStats(fb, search, startDate, endDate);
        }
    }

    private static void showStats(Facebook fb, String id, String startDate, String endDate) {
        log.info(">>> {} <<<", id);
        log.info("{}", fb.getShortInfo(id));
        log.info("");

        log.info("Likes and comments of {}:", id);
        String jsonString = fb.getLikes(id, startDate, endDate, LIMIT);
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonString);
            log.info("{}", jsonObject.toString(4));
        } catch (JSONException e) {
            log.info("{}", jsonString);
        }
    }

}
