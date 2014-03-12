package com.box.boxjavalibv2.requests.requestentities;

import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;

public class BoxItemRequestEntity extends BoxSharedLinkRequestEntity {

    public static BoxItemRequestEntity getRequestEntity() {
        return new BoxItemRequestEntity();
    }

    public static BoxItemRequestEntity deleteSharedLinkRequestEntity() {
        return (BoxItemRequestEntity) BoxSharedLinkRequestEntity.deleteSharedLinkRequestEntity();
    }

    public static BoxItemRequestEntity createSharedLinkRequestEntity(BoxSharedLinkRequestEntity sharedLink) {
        return (BoxItemRequestEntity) BoxSharedLinkRequestEntity.createSharedLinkRequestEntity(sharedLink);
    }

    /**
     * Set parent folder of the file.
     * 
     * @param parentId
     *            id of parent
     * @return
     */
    public void setParent(String parentId) {
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

    /**
     * Set description of the file
     * 
     * @param description
     *            description
     * @return
     */
    public void setDescription(String description) {
        put(BoxFile.FIELD_DESCRIPTION, description);
    }

    public void setTags(String[] tags) {
        put(BoxFile.FIELD_TAGS, tags);
    }
}
