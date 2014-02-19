package com.box.boxjavalibv2.interfaces;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.resourcemanagers.IBoxResourceManager;

/**
 * Resource manager plugin interface. Resource manager classes implementing this can be plugged into BoxClient.
 */
public interface IResourceManagerPlugin {

    IBoxResourceManager plugin(BoxClient client);
}
