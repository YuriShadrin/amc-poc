package com.exadel.amc.facebook.rest;

import org.nextlets.erc.ERCEndpoint;
import org.nextlets.erc.ERCParam;

public interface FacebookRestClient {

    @ERCEndpoint(endpoint = "/oauth/access_token?grant_type=client_credentials", deserializer = AccessTokenDeserializer.class)
    String getAccessToken(@ERCParam(name = "client_id") String appId, @ERCParam(name = "client_secret") String appsecret);

    @ERCEndpoint(endpoint = "/{obj_id}")
    String getShortInfo(@ERCParam(name = "obj_id") String objId);

    @ERCEndpoint(endpoint = "/{obj_id}")
    String getLikes(@ERCParam(name = "obj_id") String objId, @ERCParam(name = "fields") String fields);

}
