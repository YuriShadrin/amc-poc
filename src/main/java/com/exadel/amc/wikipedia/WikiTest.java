package com.exadel.amc.wikipedia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nextlets.erc.ERCFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exadel.amc.wikipedia.data.ArticleStatistics;

public class WikiTest {

	static Logger log = LoggerFactory.getLogger(WikiTest.class);
	
	static WikiClient createWikiClient() {
		ERCFactory f = ERCFactory.getInstance("http://stats.grok.se");
		WikiClient client = f.createRestClient(WikiClient.class);
		return client;
	}
	
	private static void printArticleStatistics(ArticleStatistics as) {
		log.info("Title: {}", as.getTitle());
		log.info("Project: {}", as.getProject());
		log.info("Month: {}", as.getMonth());
		log.info("Rank: {}", as.getRank());
		
		log.info("");
		log.info("Views:");

		List<Map.Entry<String, Integer>>list = new ArrayList<Map.Entry<String, Integer>>();
		list.addAll(as.getDailyViews().entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                return e2.getKey().compareTo(e1.getKey());
            }
		});

		for (Map.Entry<String, Integer> entry : list) {
		    log.info("{}: {}", entry.getKey(), entry.getValue());
		}
	}
	
	
	public static void main(String[] args) {
		WikiClient client = createWikiClient();
		
		String[] searches;
		
		if(args.length > 0) {
			searches = args;
		} else {
			searches = new String[]{"Britney_Spears"};
		}

		for (String search : searches) {
			log.info(">>> {} <<<", search);
			printArticleStatistics(client.getArticleStatisticsLatest30(search));
			log.info("");
		}
	}
	
}
