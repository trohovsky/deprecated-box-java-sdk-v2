package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.jsonentities.BoxSharedLinkRequestEntity;

public class BoxWebLinkRequestObject extends BoxItemRequestObject {

    public BoxWebLinkRequestObject() {
        super();
    }

    public BoxWebLinkRequestObject(BoxSharedLinkRequestEntity sharedLink) {
        super(sharedLink);
    }

    public static BoxWebLinkRequestObject getRequestObject() {
        return new BoxWebLinkRequestObject();
    }

    public static BoxWebLinkRequestObject deleteSharedLinkRequestObject() {
        return new BoxWebLinkRequestObject(null);
    }

    public static BoxWebLinkRequestObject createSharedLinkRequestObject(BoxSharedLinkRequestEntity sharedLink) {
        return new BoxWebLinkRequestObject(sharedLink);
    }
}
