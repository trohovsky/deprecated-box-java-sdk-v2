package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.boxjavalibv2.utils.Constants;

public class BoxFolderDeleteRequestObject extends BoxDefaultRequestObject {

    public BoxFolderDeleteRequestObject(IBoxJSONParser parser) {
        super(parser);
    }

    public static BoxFolderDeleteRequestObject deleteFolderRequestObject(boolean recursive, final IBoxJSONParser parser) {
        return (new BoxFolderDeleteRequestObject(parser)).setRecursive(recursive);
    }

    /**
     * Set whether operation is done recursively. (For example deleting a folder)
     * 
     * @param recursive
     * @return
     */
    private BoxFolderDeleteRequestObject setRecursive(final boolean recursive) {
        addQueryParam(Constants.RECURSIVE, Boolean.toString(recursive));
        return this;
    }
}
