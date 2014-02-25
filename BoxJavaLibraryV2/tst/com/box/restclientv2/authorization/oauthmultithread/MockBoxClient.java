package com.box.restclientv2.authorization.oauthmultithread;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.resourcemanagers.BoxOAuthManager;
import com.box.restclientv2.interfaces.IBoxRESTClient;

public class MockBoxClient extends BoxClient {

    public MockBoxClient() {
        super("", "", null, null, new MockRestClient());
    }

    @Override
    public BoxOAuthManager getOAuthManager() {
        return new MockOAuthManager();
    }

}
