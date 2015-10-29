package com.exadel.amc.wikipedia;

import org.nextlets.erc.ERCFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exadel.amc.wikipedia.data.ArticleStatistics;
import com.exadel.amc.wikipedia.data.DailyView;

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
		
		for (DailyView dv : as.getDailyViews()) {
			log.info("{} : {}", dv.getDate(), dv.getViewsCount());
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
