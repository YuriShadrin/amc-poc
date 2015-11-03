package com.exadel.amc.instagram.rest;

import org.nextlets.erc.defaults.handler.ERCHttpErrorHandlerDefaultImpl;
import org.nextlets.erc.exception.ERCHttpResponseException;

public class InstagramErrorHandler extends ERCHttpErrorHandlerDefaultImpl {

    @Override
    public void handleError(int statusCode, String reasonPhrase, byte[] responseBody) throws ERCHttpResponseException {
        if (statusCode > 401) {
            super.handleError(statusCode, reasonPhrase, responseBody);
        }
    }

}
