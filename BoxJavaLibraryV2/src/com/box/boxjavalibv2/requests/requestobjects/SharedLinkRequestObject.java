package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.jsonentities.BoxSharedLinkRequestEntity;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;

public class SharedLinkRequestObject extends BoxDefaultRequestObject {

    public SharedLinkRequestObject(IBoxJSONParser parser) {
        super(parser);
    }

    public static SharedLinkRequestObject deleteSharedLinkRequestObject(final IBoxJSONParser parser) {
        return (new SharedLinkRequestObject(parser)).setSharedLink(null);
    }

    public static SharedLinkRequestObject createSharedLinkRequestObject(BoxSharedLinkRequestEntity sharedLinkObject, final IBoxJSONParser parser) {
        return (new SharedLinkRequestObject(parser)).setSharedLink(sharedLinkObject);
    }

    /**
     * Set shared link. You can set this to null in a update file/folder info request in order to delete shared link in the file object.
     * 
     * @param sharedLink
     * @return
     */
    protected SharedLinkRequestObject setSharedLink(BoxSharedLinkRequestEntity sharedLink) {
        put(BoxFile.FIELD_SHARED_LINK, sharedLink);
        return this;
    }
}
