package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.boxjavalibv2.requests.requestentities.IBoxRequestEntity;

public class BoxEntityRequestObject<T extends IBoxRequestEntity> extends BoxDefaultRequestObject {

    protected BoxEntityRequestObject(IBoxJSONParser parser, T entity) {
        super(parser);
        entity.applyToRequestObject(this);
    }

    public static <T extends IBoxRequestEntity> BoxEntityRequestObject<T> getRequestEntity(IBoxJSONParser parser, T entity) {
        return new BoxEntityRequestObject<T>(parser, entity);
    }
}
