package com.box.boxjavalibv2.resourcemanagers;

import com.box.boxjavalibv2.IBoxConfig;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.boxjavalibv2.jsonparsing.IBoxResourceHub;
import com.box.restclientv2.authorization.IBoxRequestAuth;

public interface IBoxResourceManager {

    /**
     * Get auth.
     * 
     * @return auth
     */
    public IBoxRequestAuth getAuth();

    /**
     * @return the IResourceHub
     */
    public IBoxResourceHub getResourceHub();

    public IBoxJSONParser getJSONParser();

    /** Get config. */
    public IBoxConfig getConfig();

}