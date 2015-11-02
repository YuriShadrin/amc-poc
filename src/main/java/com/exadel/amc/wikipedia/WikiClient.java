package com.exadel.amc.wikipedia;

import org.nextlets.erc.ERCEndpoint;
import org.nextlets.erc.ERCParam;

import com.exadel.amc.wikipedia.data.ArticleStatistics;
import com.exadel.amc.wikipedia.data.ResponseDesirializer;

public interface WikiClient {

    @ERCEndpoint(endpoint = "/json/en/latest30/{acticle}", deserializer = ResponseDesirializer.class)
    ArticleStatistics getArticleStatisticsLatest30(@ERCParam(name = "acticle") String acticle);

}
