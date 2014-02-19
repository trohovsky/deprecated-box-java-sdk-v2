package com.box.boxjavalibv2.resourcemanagers;

import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRequestAuth;

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