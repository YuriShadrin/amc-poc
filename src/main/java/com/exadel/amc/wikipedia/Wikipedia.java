package com.exadel.amc.wikipedia;

import org.nextlets.erc.ERCFactory;

import com.exadel.amc.wikipedia.data.ArticleStatistics;
import com.exadel.amc.wikipedia.rest.WikipediaRestClient;

public class Wikipedia {

    private WikipediaRestClient client;

    public Wikipedia() {
        ERCFactory f = ERCFactory.getInstance("http://stats.grok.se");
        this.client = f.createRestClient(WikipediaRestClient.class);
    }

    public ArticleStatistics getArticleStatisticsLatest30(String acticle) {
        return client.getArticleStatisticsLatest30(acticle);
    }

}
