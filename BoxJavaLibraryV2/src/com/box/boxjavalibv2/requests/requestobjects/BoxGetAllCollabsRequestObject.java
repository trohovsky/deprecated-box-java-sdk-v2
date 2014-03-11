package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxCollaboration;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;

public class BoxGetAllCollabsRequestObject extends BoxDefaultRequestObject {

    private BoxGetAllCollabsRequestObject(IBoxJSONParser parser) {
        super(parser);
    }

    /**
     * Create an request object used to make get all collaborations request.
     * 
     * @param status
     *            status of the collaborations requested( This field is required and currently only support
     *            {@link com.box.boxjavalibv2.dao.BoxCollaboration.STATUS_PENDING}
     * @return BoxCollabRequestObject
     */
    public static BoxGetAllCollabsRequestObject getAllCollaborationsRequestObject(final String status, final IBoxJSONParser parser) {
        return (new BoxGetAllCollabsRequestObject(parser)).setStatus(status);
    }

    private BoxGetAllCollabsRequestObject setStatus(String status) {
        this.addQueryParam(BoxCollaboration.FIELD_STATUS, status);
        return this;
    }
}
