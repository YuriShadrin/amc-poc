package com.exadel.amc.facebook.rest;

import org.nextlets.erc.ERCConfiguration;
import org.nextlets.erc.exception.ERCDeserializationException;
import org.nextlets.erc.handler.ERCResultDeserializer;

public class AccessTokenDeserializer implements ERCResultDeserializer<String> {

    public static final String ACCESS_TOKEN = "access_token";

    @Override
    public String deserealize(ERCConfiguration configuration, int statusCode, String contentType, byte[] responseBody,
            Class<String> resultClass) throws ERCDeserializationException {
        String resp = new String(responseBody);
        if (resp.startsWith(ACCESS_TOKEN)) {
            return resp.split("=")[1];
        }
        return null;
    }
}