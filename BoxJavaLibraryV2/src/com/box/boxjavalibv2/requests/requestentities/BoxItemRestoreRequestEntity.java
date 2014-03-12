package com.box.boxjavalibv2.requests.requestentities;

import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;

public class BoxItemRestoreRequestEntity extends BoxDefaultRequestEntity {

    private BoxItemRestoreRequestEntity() {
    }

    public static BoxItemRestoreRequestEntity getRequestEntity() {
        return new BoxItemRestoreRequestEntity();
    }

    /**
     * Rename the restored item.
     * 
     * @param newName
     * @return
     */
    public void setNewName(final String newName) {
        put(BoxItem.FIELD_NAME, newName);
    }

    /**
     * Set new parent for the restored item.
     * 
     * @param parentId
     * @return
     */
    public void setNewParent(final String parentId) {
        MapJSONStringEntity id = new MapJSONStringEntity();
        id.put(BoxFolder.FIELD_ID, parentId);
        put(BoxItem.FIELD_PARENT, id);
    }
}
