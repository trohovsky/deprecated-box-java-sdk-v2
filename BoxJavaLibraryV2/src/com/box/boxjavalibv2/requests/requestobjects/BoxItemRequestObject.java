package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.jsonentities.BoxSharedLinkRequestEntity;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;

public class BoxItemRequestObject extends BoxDefaultRequestObject {

    protected BoxItemRequestObject() {
    }

    public static BoxItemRequestObject deleteSharedLinkRequestObject() {
        return (new BoxItemRequestObject()).setSharedLink(null);
    }

    public static BoxItemRequestObject createSharedLinkRequestObject(BoxSharedLinkRequestEntity sharedLink) {
        return (new BoxItemRequestObject()).setSharedLink(sharedLink);
    }

    /**
     * Set shared link. You can set this to null in a update file/folder info request in order to delete shared link in the file object.
     * 
     * @param sharedLink
     * @return
     */
    public BoxItemRequestObject setSharedLink(BoxSharedLinkRequestEntity sharedLink) {
        put(BoxFile.FIELD_SHARED_LINK, sharedLink);
        return this;
    }

    /**
     * Set parent folder of the file.
     * 
     * @param parentId
     *            id of parent
     * @return
     */
    public BoxItemRequestObject setParent(String parentId) {
        MapJSONStringEntity entity = new MapJSONStringEntity();
        entity.put(BoxFolder.FIELD_ID, parentId);
        put(BoxItem.FIELD_PARENT, entity);
        return this;
    }

    /**
     * Set etag. This can be set when making a delete file/folder request. If this is set and if-match fails, the deletion api call will fail.
     * 
     * @param etag
     *            etag
     * @return BoxFileRequestObject
     */
    public BoxItemRequestObject setIfMatch(String etag) {
        addHeader("If-Match", etag);
        return this;
    }

    /**
     * Set name of the file.
     * 
     * @param name
     *            name
     * @return
     */
    public BoxItemRequestObject setName(String name) {
        put(BoxFile.FIELD_NAME, name);
        return this;
    }

    /**
     * Set description of the file
     * 
     * @param description
     *            description
     * @return
     */
    public BoxItemRequestObject setDescription(String description) {
        put(BoxFile.FIELD_DESCRIPTION, description);
        return this;
    }

    public BoxItemRequestObject setTags(String[] tags) {
        put(BoxFile.FIELD_TAGS, tags);
        return this;
    }
}
