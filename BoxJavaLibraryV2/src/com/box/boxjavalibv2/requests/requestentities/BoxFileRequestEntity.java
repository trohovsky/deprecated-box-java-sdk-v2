package com.box.boxjavalibv2.requests.requestentities;

public class BoxFileRequestEntity extends BoxItemRequestEntity {

    public static BoxFileRequestEntity getRequestEntity() {
        return new BoxFileRequestEntity();
    }

    public static BoxFileRequestEntity deleteSharedLinkRequestEntity() {
        return (BoxFileRequestEntity) BoxItemRequestEntity.deleteSharedLinkRequestEntity();
    }

    public static BoxFileRequestEntity createSharedLinkRequestEntity(BoxSharedLinkRequestEntity sharedLink) {
        return (BoxFileRequestEntity) BoxItemRequestEntity.createSharedLinkRequestEntity(sharedLink);
    }
}
