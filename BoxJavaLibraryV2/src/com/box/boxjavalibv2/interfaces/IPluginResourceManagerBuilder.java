package com.box.boxjavalibv2.interfaces;

import com.box.boxjavalibv2.resourcemanagers.IBoxResourceManager;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;

public interface IPluginResourceManagerBuilder {

    IBoxResourceManager build(IBoxConfig config, final IBoxResourceHub resourceHub, final IBoxJSONParser parser, final IBoxRequestAuth auth,
        final IBoxRESTClient restClient);
}
