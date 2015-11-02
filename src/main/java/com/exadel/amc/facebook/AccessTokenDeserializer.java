package com.exadel.amc.facebook;

import org.nextlets.erc.ERCConfiguration;
import org.nextlets.erc.exception.ERCDeserializationException;
import org.nextlets.erc.handler.ERCResultDeserializer;

public class AccessTokenDeserializer implements ERCResultDeserializer<String> {

    public static final String ACCESS_TOKEN = "access_token";

    @Override
    public String deserealize(ERCConfiguration configuration, int statusCode, String contentType, String responseBody,
            Class<String> resultClass) throws ERCDeserializationException {
        if (responseBody.startsWith(ACCESS_TOKEN)) {
            return responseBody.split("=")[1];
        }
        return null;
    }
}