package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.IBoxConfig;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.requests.DefaultBoxRequest;

/**
 * Request to get collaborations on a given folder. Can also request for collaborations of a certain status. Currently only
 * {@link com.box.boxjavalibv2.dao.CollaborationV2.STATUS_PENDING} is allowed.
 */
public class GetFolderCollaborationsRequest extends DefaultBoxRequest {

    private static final String URI = "/folders/%s/collaborations";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param parser
     *            json parser
     * @param folderId
     *            id of the folder
     * @param status
     *            status of the collaborations requested (Currently only support {@link com.box.boxjavalibv2.dao.CollaborationV2.STATUS_PENDING}, use null if
     *            want to ignore this field and get all collaborations)
     * @throws BoxRestException
     *             exception
     */
    public GetFolderCollaborationsRequest(final IBoxConfig config, final IBoxJSONParser parser, String folderId, BoxDefaultRequestObject requestObject)
        throws BoxRestException {
        super(config, parser, getUri(folderId), RestMethod.GET, requestObject);
    }

    /**
     * Get uri.
     * 
     * @param folderId
     *            id of the collaborated folder
     * @return uri
     */
    public static String getUri(final String folderId) {
        return String.format(URI, folderId);
    }
}
