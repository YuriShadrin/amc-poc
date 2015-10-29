package com.exadel.amc.wikipedia.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.nextlets.erc.ERCConfiguration;
import org.nextlets.erc.exception.ERCDeserializationException;
import org.nextlets.erc.handler.ERCResultDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

public class ResponseDesirializer implements ERCResultDeserializer<ArticleStatistics> {

	private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static Logger log = LoggerFactory.getLogger(ResponseDesirializer.class);
	
	
	private List<DailyView> parseDailyViews(String responseBody) throws ParseException, JSONException {
        JSONObject jo = new JSONObject(responseBody);
        String dvs = jo.getString("daily_views");
        String[]dva = dvs.replaceAll("[{}\"]", "").split(",");
        List<DailyView>dvl = new ArrayList<DailyView>();
        for (String dvas : dva) {
        	String[]dvpair = dvas.split(":");
        	if (dvpair.length == 2) {
        		DailyView dv = new DailyView();
        		dv.setDate(sdf.parse(dvpair[0]));
        		dv.setViewsCount(new Integer(dvpair[1]));
        		dvl.add(dv);
        	}
        }
        Collections.sort(dvl, new Comparator<DailyView>() {
			@Override
			public int compare(DailyView o1, DailyView o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
        });
        return dvl;
	}
	
	@Override
	public ArticleStatistics deserealize(
			ERCConfiguration configuration,
			int statusCode,
			String contentType,
			String responseBody,
			Class<ArticleStatistics> resultClass) throws ERCDeserializationException {
		
        Gson gson = new GsonBuilder().create();
        ArticleStatistics as = gson.fromJson(responseBody, ArticleStatistics.class);
		try {
	        List<DailyView> dvl = parseDailyViews(responseBody);
	        as.setDailyViews(dvl);
		} catch (Exception e) {
			log.error("Could not parse response.", e);
		}
		return as;
	}
}
