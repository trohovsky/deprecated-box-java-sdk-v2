package com.box.restclientv2.authorization.oauthmultithread;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.resourcemanagers.IBoxOAuthManager;
import com.box.restclientv2.interfaces.IBoxRESTClient;

public class MockBoxClient extends BoxClient {

    public MockBoxClient() {
        super("", "");
    }

    @Override
    protected IBoxRESTClient createRestClient() {
        return new MockRestClient();
    }

    @Override
    public IBoxOAuthManager getOAuthManager() {
        return new MockOAuthManager();
    }

}
