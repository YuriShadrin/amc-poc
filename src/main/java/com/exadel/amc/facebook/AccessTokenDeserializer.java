package com.exadel.amc.facebook;

import org.nextlets.erc.ERCConfiguration;
import org.nextlets.erc.exception.ERCDeserializationException;
import org.nextlets.erc.handler.ERCResultDeserializer;

public class AccessTokenDeserializer implements ERCResultDeserializer<String> {

    public static final String ACCESS_TOKEN = "access_token";

    @Override
    public String deserealize(ERCConfiguration configuration, int statusCode, String contentType, byte[] body,
            Class<String> resultClass) throws ERCDeserializationException {
        String responseBody = new String(body);
        if (responseBody.startsWith(ACCESS_TOKEN)) {
            return responseBody.split("=")[1];
        }
        return null;
    }
}