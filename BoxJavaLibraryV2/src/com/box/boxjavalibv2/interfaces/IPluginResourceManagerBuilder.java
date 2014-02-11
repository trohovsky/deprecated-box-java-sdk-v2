package com.box.boxjavalibv2.interfaces;

import com.box.boxjavalibv2.resourcemanagers.BoxResourceManager;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;

public interface IPluginResourceManagerBuilder {

    BoxResourceManager build(IBoxConfig config, final IBoxResourceHub resourceHub, final IBoxJSONParser parser, final IBoxRequestAuth auth,
        final IBoxRESTClient restClient);
}
