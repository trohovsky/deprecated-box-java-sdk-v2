package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.jsonentities.BoxSharedLinkEntity;

public class BoxSharedLinkRequestObject extends BoxDefaultRequestObject {

    protected BoxSharedLinkRequestObject() {
    }

    protected BoxSharedLinkRequestObject(BoxSharedLinkEntity sharedLink) {
        setSharedLink(sharedLink);
    }

    public static BoxSharedLinkRequestObject deleteSharedLinkRequestObject() {
        return new BoxSharedLinkRequestObject(null);
    }

    public static BoxSharedLinkRequestObject createSharedLinkRequestObject(BoxSharedLinkEntity sharedLink) {
        return new BoxSharedLinkRequestObject(sharedLink);
    }

    /**
     * Set shared link. You can set this to null in a update file/folder info request in order to delete shared link in the file object.
     * 
     * @param sharedLink
     * @return
     */
    protected BoxSharedLinkRequestObject setSharedLink(BoxSharedLinkEntity sharedLink) {
        put(BoxFile.FIELD_SHARED_LINK, sharedLink);
        return this;
    }
}
