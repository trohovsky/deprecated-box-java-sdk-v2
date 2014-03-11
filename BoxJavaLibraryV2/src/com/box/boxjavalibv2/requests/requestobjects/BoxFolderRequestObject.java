package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;

public class BoxFolderRequestObject extends BoxItemRequestObject {

    protected BoxFolderRequestObject(IBoxJSONParser parser) {
        super(parser);
    }

    public static BoxFolderRequestObject createFolderRequestObject(String name, String parentId, final IBoxJSONParser parser) {
        return (BoxFolderRequestObject) (new BoxFolderRequestObject(parser)).setName(name).setParent(parentId);
    }

}
