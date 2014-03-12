package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.IBoxConfig;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.boxjavalibv2.requests.requestentities.BoxItemRestoreRequestEntity;
import com.box.boxjavalibv2.requests.requestobjects.BoxEntityRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.requests.DefaultBoxRequest;

public class RestoreTrashItemRequest extends DefaultBoxRequest {

    private static final String URI = "/%s/%s";

    /**
     * Cosntructor.
     * 
     * @param config
     *            config
     * @param parser
     *            json parser
     * @param id
     *            id of the item
     * @param type
     *            resource type of the item
     * @param requestObject
     *            request object
     * @throws BoxRestException
     */
    public RestoreTrashItemRequest(final IBoxConfig config, final IBoxJSONParser parser, final String id, final BoxResourceType type,
        final BoxEntityRequestObject<BoxItemRestoreRequestEntity> requestObject) throws BoxRestException {
        super(config, parser, getUri(id, type), RestMethod.POST, requestObject);
        this.setExpectedResponseCode(HttpStatus.SC_CREATED);
    }

    /**
     * Get uri.
     * 
     * @param id
     *            id of the item
     * @param itemType
     *            type of the item
     * @return uri
     */
    public static String getUri(final String id, final BoxResourceType type) {
        return String.format(URI, type.toPluralString(), id);
    }
}
