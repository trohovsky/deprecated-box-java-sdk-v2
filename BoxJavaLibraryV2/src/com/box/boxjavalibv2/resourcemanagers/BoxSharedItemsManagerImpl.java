package com.box.boxjavalibv2.resourcemanagers;

import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.requests.GetSharedItemRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;

public class BoxSharedItemsManagerImpl extends AbstractBoxResourceManager implements IBoxSharedItemsManager {

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param resourceHub
     *            IResourceHub
     * @param parser
     *            json parser
     * @param auth
     *            auth for api calls
     * @param restClient
     *            REST client to make api calls.
     */
    public BoxSharedItemsManagerImpl(IBoxConfig config, IBoxResourceHub resourceHub, final IBoxJSONParser parser, final IBoxRequestAuth auth,
        final IBoxRESTClient restClient) {
        super(config, resourceHub, parser, auth, restClient);
    }

    @Override
    public BoxItem getSharedItem(final BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        GetSharedItemRequest request = new GetSharedItemRequest(getConfig(), getJSONParser(), requestObject);
        return (BoxItem) getResponseAndParseAndTryCast(request, BoxResourceType.ITEM, getJSONParser());
    }
}
