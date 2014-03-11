package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;

public class BoxFileRequestObject extends BoxItemRequestObject {

    private BoxFileRequestObject(IBoxJSONParser parser) {
        super(parser);
    }

    public static BoxFileRequestObject updateFileRequestObject(final IBoxJSONParser parser) {
        return new BoxFileRequestObject(parser);
    }
}
