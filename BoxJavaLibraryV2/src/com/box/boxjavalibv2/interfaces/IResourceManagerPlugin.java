package com.box.boxjavalibv2.interfaces;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.resourcemanagers.BoxResourceManager;

/**
 * Resource manager plugin interface. Resource manager classes implementing this can be plugged into BoxClient.
 */
public interface IResourceManagerPlugin {

    BoxResourceManager plugin(BoxClient client);
}
