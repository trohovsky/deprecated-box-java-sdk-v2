package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxGroup;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;

public class BoxGroupRequestObject extends BoxDefaultRequestObject {

    private BoxGroupRequestObject(IBoxJSONParser parser) {
        super(parser);
    }

    public static BoxGroupRequestObject createGroupRequestObject(final String name, final IBoxJSONParser parser) {
        return updateGroupRequestObject(name, parser);
    }

    public static BoxGroupRequestObject updateGroupRequestObject(final String name, final IBoxJSONParser parser) {
        BoxGroupRequestObject obj = new BoxGroupRequestObject(parser);
        obj.put(BoxGroup.FIELD_NAME, name);
        return obj;
    }

}
