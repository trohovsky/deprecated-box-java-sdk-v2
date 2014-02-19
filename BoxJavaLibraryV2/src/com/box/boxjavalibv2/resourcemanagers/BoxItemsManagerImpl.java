package com.box.boxjavalibv2.resourcemanagers;

import java.io.UnsupportedEncodingException;

import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.requests.CopyItemRequest;
import com.box.boxjavalibv2.requests.CreateSharedLinkRequest;
import com.box.boxjavalibv2.requests.DeleteTrashItemRequest;
import com.box.boxjavalibv2.requests.GetItemRequest;
import com.box.boxjavalibv2.requests.GetTrashItemRequest;
import com.box.boxjavalibv2.requests.RestoreTrashItemRequest;
import com.box.boxjavalibv2.requests.UpdateItemInfoRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxFileRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxItemRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxItemRestoreRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;

/**
 * Use this class to execute requests <b>synchronously</b> against the Box REST API(V2). Full details about the Box API can be found at {@see <a
 * href="http://developers.box.com/docs">http://developers.box.com/docs</a>} . You must have an OpenBox application with a valid API key to use the Box API. All
 * methods in this class are executed in the invoking thread, and therefore are NOT safe to execute in the UI thread of your application. You should only use
 * this class if you already have worker threads or AsyncTasks that you want to incorporate the Box API into.
 */
public class BoxItemsManagerImpl extends AbstractBoxResourceManager implements IBoxItemsManager {

    /**
     * Constructor.
     * 
     * @param config
     *            BoxConfig
     * @param resourceHub
     *            IResourceHub
     * @param parser
     *            json parser
     * @param auth
     *            auth for api calls
     * @param restClient
     *            REST client to make api calls.
     */
    public BoxItemsManagerImpl(IBoxConfig config, IBoxResourceHub resourceHub, final IBoxJSONParser parser, final IBoxRequestAuth auth,
        final IBoxRESTClient restClient) {
        super(config, resourceHub, parser, auth, restClient);
    }

    @Override
    public BoxItem getItem(final String id, BoxDefaultRequestObject requestObject, final BoxResourceType type) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        GetItemRequest request = new GetItemRequest(getConfig(), getJSONParser(), id, type, requestObject);
        Object result = getResponseAndParse(request, type, getJSONParser());
        return (BoxItem) tryCastBoxItem(type, result);
    }

    @Override
    public BoxItem getTrashItem(final String itemId, final BoxResourceType type, final BoxDefaultRequestObject requestObject) throws BoxRestException,
        AuthFatalFailureException, BoxServerException {
        GetTrashItemRequest request = new GetTrashItemRequest(getConfig(), getJSONParser(), itemId, type, requestObject);
        Object result = getResponseAndParse(request, type, getJSONParser());
        return (BoxItem) tryCastBoxItem(type, result);
    }

    @Override
    public BoxItem copyItem(final String id, BoxItemRequestObject requestObject, final BoxResourceType type) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        CopyItemRequest request = new CopyItemRequest(getConfig(), getJSONParser(), id, requestObject, type);
        return (BoxItem) getResponseAndParseAndTryCast(request, type, getJSONParser());
    }

    @Override
    public BoxItem updateItemInfo(final String id, BoxItemRequestObject requestObject, final BoxResourceType type) throws UnsupportedEncodingException,
        BoxRestException, BoxServerException, AuthFatalFailureException {
        UpdateItemInfoRequest request = new UpdateItemInfoRequest(getConfig(), getJSONParser(), id, requestObject, type);
        return (BoxItem) getResponseAndParseAndTryCast(request, type, getJSONParser());
    }

    @Override
    public BoxItem createSharedLink(final String id, BoxItemRequestObject requestObject, final BoxResourceType type) throws BoxRestException,
        BoxServerException, AuthFatalFailureException {
        CreateSharedLinkRequest request = new CreateSharedLinkRequest(getConfig(), getJSONParser(), id, requestObject, type);

        return (BoxItem) getResponseAndParseAndTryCast(request, type, getJSONParser());
    }

    @Override
    public void deleteTrashItem(final String id, final BoxResourceType type, final BoxFileRequestObject requestObject) throws BoxRestException,
        BoxServerException, AuthFatalFailureException {
        DeleteTrashItemRequest request = new DeleteTrashItemRequest(getConfig(), getJSONParser(), id, type, requestObject);
        executeRequestWithNoResponseBody(request);
    }

    @Override
    public BoxItem restoreTrashItem(final String id, final BoxResourceType type, final BoxItemRestoreRequestObject requestObject) throws BoxRestException,
        AuthFatalFailureException, BoxServerException {
        RestoreTrashItemRequest request = new RestoreTrashItemRequest(getConfig(), getJSONParser(), id, type, requestObject);
        return (BoxItem) getResponseAndParseAndTryCast(request, type, getJSONParser());
    }
}
