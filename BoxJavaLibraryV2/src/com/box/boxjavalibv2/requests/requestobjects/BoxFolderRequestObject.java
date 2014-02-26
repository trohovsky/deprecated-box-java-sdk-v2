package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.jsonentities.BoxSharedLinkRequestEntity;
import com.box.boxjavalibv2.utils.Constants;

public class BoxFolderRequestObject extends BoxItemRequestObject {

    protected BoxFolderRequestObject() {
    }

    public static BoxFolderRequestObject deleteSharedLinkRequestObject() {
        return (BoxFolderRequestObject) (new BoxFolderRequestObject()).setSharedLink(null);
    }

    public static BoxFolderRequestObject createSharedLinkRequestObject(BoxSharedLinkRequestEntity sharedLink) {
        return (BoxFolderRequestObject) (new BoxFolderRequestObject()).setSharedLink(sharedLink);
    }

    public static BoxFolderRequestObject createFolderRequestObject(String name, String parentId) {
        return (BoxFolderRequestObject) (new BoxFolderRequestObject()).setName(name).setParent(parentId);
    }

    public static BoxFolderRequestObject deleteFolderRequestObject(boolean recursive) {
        return (new BoxFolderRequestObject()).setRecursive(recursive);
    }

    public static BoxFolderRequestObject getTrashItemsRequestObject(final int limit, final int offset) {
        return getFolderItemsRequestObject(limit, offset);
    }

    /**
     * BoxFolderRequestObject for get folder items request.
     * 
     * @param limit
     *            the number of items to return. default is 100, max is 1000.
     * @param offset
     *            the item at which to begin the response, default is 0.
     * @return BoxFolderRequestObject
     */
    public static BoxFolderRequestObject getFolderItemsRequestObject(final int limit, final int offset) {
        return (BoxFolderRequestObject) (new BoxFolderRequestObject()).setPage(limit, offset);
    }

    /**
     * BoxFolderRequestObject for copy folder request.
     * 
     * @param parentId
     *            id of destination parent folder.
     * @return BoxFolderRequestObject
     */
    public static BoxFolderRequestObject copyFolderRequestObject(String parentId) {
        return (BoxFolderRequestObject) (new BoxFolderRequestObject()).setParent(parentId);
    }

    /**
     * Set whether operation is done recursively. (For example deleting a folder)
     * 
     * @param recursive
     * @return
     */
    private BoxFolderRequestObject setRecursive(final boolean recursive) {
        addQueryParam(Constants.RECURSIVE, Boolean.toString(recursive));
        return this;
    }
}
