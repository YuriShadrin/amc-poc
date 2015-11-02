package com.exadel.amc.wikipedia.data;

import org.nextlets.erc.ERCConfiguration;
import org.nextlets.erc.exception.ERCDeserializationException;
import org.nextlets.erc.handler.ERCResultDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResponseDesirializer implements ERCResultDeserializer<ArticleStatistics> {

    @Override
    public ArticleStatistics deserealize(ERCConfiguration configuration, int statusCode, String contentType,
            String responseBody, Class<ArticleStatistics> resultClass) throws ERCDeserializationException {

        Gson gson = new GsonBuilder().create();
        ArticleStatistics as = gson.fromJson(responseBody, ArticleStatistics.class);
        return as;
    }
}
