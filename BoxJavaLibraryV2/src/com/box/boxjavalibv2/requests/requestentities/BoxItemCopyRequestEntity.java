package com.box.boxjavalibv2.requests.requestentities;

import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;

public class BoxItemCopyRequestEntity extends BoxDefaultRequestEntity {

    private BoxItemCopyRequestEntity() {
    }

    public static BoxItemCopyRequestEntity getRequestEntity(String parentId) {
        BoxItemCopyRequestEntity entity = new BoxItemCopyRequestEntity();
        entity.setParent(parentId);
        return entity;
    }

    /**
     * Set parent folder of the file.
     * 
     * @param parentId
     *            id of parent
     * @return
     */
    private void setParent(String parentId) {
        MapJSONStringEntity entity = new MapJSONStringEntity();
        entity.put(BoxFolder.FIELD_ID, parentId);
        put(BoxItem.FIELD_PARENT, entity);
    }

    /**
     * Set name of the file.
     * 
     * @param name
     *            name
     * @return
     */
    public void setName(String name) {
        put(BoxFile.FIELD_NAME, name);
    }
}
