package com.box.boxjavalibv2.resourcemanagers;

import com.box.boxjavalibv2.IBoxConfig;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.dao.BoxWebLink;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.boxjavalibv2.jsonparsing.IBoxResourceHub;
import com.box.boxjavalibv2.requests.DeleteWebLinkRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxItemCopyRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxSharedLinkRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxWebLinkRequestObject;
import com.box.restclientv2.IBoxRESTClient;
import com.box.restclientv2.authorization.IBoxRequestAuth;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.requestsbase.BoxDefaultRequestObject;

import java.io.UnsupportedEncodingException;

public class BoxWebLinksManagerImpl extends BoxItemsManagerImpl implements IBoxWebLinksManager {

    /**
     * Constructor.
     *
     * @param config
     *            BoxConfig
     * @param resourceHub
     *            resource hub
     * @param parser
     *            json parser
     * @param auth
     *            auth for api calls
     * @param restClient
     *            REST client to make api calls.
     */
    public BoxWebLinksManagerImpl(IBoxConfig config, final IBoxResourceHub resourceHub, final IBoxJSONParser parser, final IBoxRequestAuth auth,
                               final IBoxRESTClient restClient) {
        super(config, resourceHub, parser, auth, restClient);
    }
    @Override
    public BoxWebLink getWebLink(String webLinkId, BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        return (BoxWebLink) super.getItem(webLinkId, requestObject, BoxResourceType.WEB_LINK);
    }

    @Override
    public void deleteWebLink(String webLinkId, BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        DeleteWebLinkRequest request = new DeleteWebLinkRequest(getConfig(), getJSONParser(), webLinkId, requestObject);
        executeRequestWithNoResponseBody(request);
    }

    @Override
    public BoxWebLink copyWebLink(String webLinkId, BoxItemCopyRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        return (BoxWebLink) super.copyItem(webLinkId, requestObject, BoxResourceType.WEB_LINK);
    }

    @Override
    public BoxWebLink updateWebLinkInfo(String webLinkId, BoxWebLinkRequestObject requestObject) throws UnsupportedEncodingException, BoxRestException, BoxServerException, AuthFatalFailureException {
        return (BoxWebLink) super.updateItemInfo(webLinkId, requestObject, BoxResourceType.WEB_LINK);
    }

    @Override
    public BoxWebLink createSharedLink(String webLinkId, BoxSharedLinkRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        return (BoxWebLink) super.createSharedLink(webLinkId, requestObject, BoxResourceType.WEB_LINK);
    }
}
