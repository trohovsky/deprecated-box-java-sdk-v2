package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;

public class BoxItemRestoreRequestObject extends BoxDefaultRequestObject {

    private BoxItemRestoreRequestObject(IBoxJSONParser parser) {
        super(parser);
    }

    /**
     * Default request object for restoring a trashed item.
     * 
     * @return
     */
    public static BoxItemRestoreRequestObject restoreItemRequestObject(final IBoxJSONParser parser) {
        return new BoxItemRestoreRequestObject(parser);
    }

    /**
     * Rename the restored item.
     * 
     * @param newName
     * @return
     */
    public BoxItemRestoreRequestObject setNewName(final String newName) {
        put(BoxItem.FIELD_NAME, newName);
        return this;
    }

    /**
     * Set new parent for the restored item.
     * 
     * @param parentId
     * @return
     */
    public BoxItemRestoreRequestObject setNewParent(final String parentId) {
        MapJSONStringEntity id = new MapJSONStringEntity();
        id.put(BoxFolder.FIELD_ID, parentId);
        put(BoxItem.FIELD_PARENT, id);
        return this;
    }
}
