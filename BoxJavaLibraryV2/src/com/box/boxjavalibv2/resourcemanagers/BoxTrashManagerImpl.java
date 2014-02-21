package com.box.boxjavalibv2.resourcemanagers;

import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.requests.DeleteTrashItemRequest;
import com.box.boxjavalibv2.requests.GetTrashItemRequest;
import com.box.boxjavalibv2.requests.RestoreTrashItemRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxFileRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxItemRestoreRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;

public class BoxTrashManagerImpl extends AbstractBoxResourceManager implements IBoxTrashManager {

    public BoxTrashManagerImpl(IBoxConfig config, IBoxResourceHub resourceHub, IBoxJSONParser parser, IBoxRequestAuth auth, IBoxRESTClient restClient) {
        super(config, resourceHub, parser, auth, restClient);
    }

    @Override
    public void deleteTrashFile(final String id, final BoxFileRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        deleteTrashItem(id, BoxResourceType.FILE, requestObject);
    }

    @Override
    public BoxFile restoreTrashFile(final String id, final BoxItemRestoreRequestObject requestObject) throws BoxRestException, AuthFatalFailureException,
        BoxServerException {
        return (BoxFile) restoreTrashItem(id, BoxResourceType.FILE, requestObject);
    }

    @Override
    public BoxFile getTrashFile(final String fileId, final BoxDefaultRequestObject requestObject) throws BoxRestException, AuthFatalFailureException,
        BoxServerException {
        return (BoxFile) getTrashItem(fileId, BoxResourceType.FILE, requestObject);
    }

    @Override
    public BoxFolder getTrashFolder(final String folderId, BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        return (BoxFolder) getTrashItem(folderId, BoxResourceType.FOLDER, requestObject);
    }

    @Override
    public void deleteTrashFolder(final String id, final BoxFileRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        deleteTrashItem(id, BoxResourceType.FOLDER, requestObject);
    }

    @Override
    public BoxFolder restoreTrashFolder(final String id, final BoxItemRestoreRequestObject requestObject) throws BoxRestException, AuthFatalFailureException,
        BoxServerException {
        return (BoxFolder) restoreTrashItem(id, BoxResourceType.FOLDER, requestObject);
    }

    private BoxItem getTrashItem(final String itemId, final BoxResourceType type, final BoxDefaultRequestObject requestObject) throws BoxRestException,
        AuthFatalFailureException, BoxServerException {
        GetTrashItemRequest request = new GetTrashItemRequest(getConfig(), getJSONParser(), itemId, type, requestObject);
        Object result = getResponseAndParse(request, type, getJSONParser());
        return (BoxItem) tryCastBoxItem(type, result);
    }

    private void deleteTrashItem(final String id, final BoxResourceType type, final BoxFileRequestObject requestObject) throws BoxRestException,
        BoxServerException, AuthFatalFailureException {
        DeleteTrashItemRequest request = new DeleteTrashItemRequest(getConfig(), getJSONParser(), id, type, requestObject);
        executeRequestWithNoResponseBody(request);
    }

    private BoxItem restoreTrashItem(final String id, final BoxResourceType type, final BoxItemRestoreRequestObject requestObject) throws BoxRestException,
        AuthFatalFailureException, BoxServerException {
        RestoreTrashItemRequest request = new RestoreTrashItemRequest(getConfig(), getJSONParser(), id, type, requestObject);
        return (BoxItem) getResponseAndParseAndTryCast(request, type, getJSONParser());
    }
}
