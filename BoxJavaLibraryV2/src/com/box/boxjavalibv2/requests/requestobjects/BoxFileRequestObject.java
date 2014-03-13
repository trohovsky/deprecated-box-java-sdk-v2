package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.jsonentities.BoxSharedLinkEntity;

public class BoxFileRequestObject extends BoxItemRequestObject {

    public BoxFileRequestObject() {
        super();
    }

    public BoxFileRequestObject(BoxSharedLinkEntity sharedLink) {
        super(sharedLink);
    }

    public static BoxFileRequestObject getRequestObject() {
        return new BoxFileRequestObject();
    }

    public static BoxFileRequestObject deleteSharedLinkRequestObject() {
        return new BoxFileRequestObject(null);
    }

    public static BoxFileRequestObject createSharedLinkRequestObject(BoxSharedLinkEntity sharedLink) {
        return new BoxFileRequestObject(sharedLink);
    }
}
