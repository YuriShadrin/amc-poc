package com.exadel.amc.instagram.rest;

import org.nextlets.erc.ERCEndpoint;
import org.nextlets.erc.ERCParam;
import org.nextlets.erc.ERClient;

import com.exadel.amc.instagram.data.UserGetResult;
import com.exadel.amc.instagram.data.UsersSearchResult;

@ERClient(errorHandler=InstagramErrorHandler.class)
public interface InstagramRestClient {

    @ERCEndpoint(endpoint="/users/search")
    UsersSearchResult searchUsers(@ERCParam(name="q") String query);
    
    @ERCEndpoint(endpoint="/users/{user-id}")
    UserGetResult getUser(@ERCParam(name="user-id") String id);
    
}
