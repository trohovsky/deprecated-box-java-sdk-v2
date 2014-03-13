package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.requests.requestentities.IBoxRequestEntity;

public class BoxEntityRequestObject<T extends IBoxRequestEntity> extends BoxDefaultRequestObject {

    protected BoxEntityRequestObject(T entity) {
        super();
        entity.applyToRequestObject(this);
    }

    public static <T extends IBoxRequestEntity> BoxEntityRequestObject<T> getRequestEntity(T entity) {
        return new BoxEntityRequestObject<T>(entity);
    }
}
