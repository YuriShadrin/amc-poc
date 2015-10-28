package com.exadel.amc.facebook;

import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import org.nextlets.erc.ERCFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;


/**
 * Register as developer on https://developers.facebook.com
 * Go to "My Apps -> Add a New App" or "My Apps -> choose the existing App" 
 * Get App ID, App Secret
 * 
 * If you need to get AUTH TOKEN, goto https://developers.facebook.com/tools/explorer/
 */
public class FBClientTest {

	public static final String FB_GRAPH_URL = "https://graph.facebook.com";
	
	static Logger log = LoggerFactory.getLogger(FBClientTest.class);
	
	private FBClient client;
	
	public FBClientTest() throws IOException {
		init();
	}
	
	private void init() throws IOException {
		Properties props = new Properties();
		props.load(FBClientTest.class.getResourceAsStream("/keys/facebook_keys.properties"));
		ERCFactory factory = ERCFactory.getInstance(FB_GRAPH_URL);
		this.client = factory.createRestClient(FBClient.class);
		String token = client.getAccessToken(props.getProperty("app.id").trim(), props.getProperty("app.secret").trim());
		factory.getAutoParams().put(AccessTokenDeserializer.ACCESS_TOKEN, token);
	}
	
	public String getFieldsString(String startDate, String endDate) {
		return "feed.until(" + endDate + ").since(" + startDate + ").limit(10){likes{id,name,username,link,pic},comments{from,created_time,parent,likes{id,name},is_private,comment_count,like_count,is_hidden},created_time,updated_time,id,caption,from,shares}";
	}
	
	public void showStats(String id, String startDate, String endDate) {
		log.info(">>> {} <<<", id);
		log.info("{}", client.getShortInfo(id));
		log.info("");
		
		log.info("Likes and comments of {}:", id);
		String jsonString = client.getLikes(id, getFieldsString(startDate, endDate));
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonString);
			log.info("{}", jsonObject.toString(4));
		} catch (JSONException e) {
			log.info("{}", jsonString);
		}
	}
	
	public static void main(String[] args) throws Exception {
		FBClientTest r = new FBClientTest();
		
		Calendar cal = Calendar.getInstance();
		String endDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONDAY)+1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
		cal.add(Calendar.DAY_OF_MONTH, -7);
		String startDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONDAY)+1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
		
		String searches[];
		if (args.length == 0) {
			searches = new String[]{"ledzeppelin"};
		} else {
			searches = args;
		}
		
		for (String search: searches) {
			r.showStats(search, startDate, endDate);
		}
	}
}
