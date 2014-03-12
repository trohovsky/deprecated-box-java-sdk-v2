package com.box.boxjavalibv2.requests.requestentities;

import org.apache.commons.lang.StringUtils;

import com.box.boxjavalibv2.dao.BoxCollaboration;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.IBoxRequestObject;

public class BoxCollabRequestEntity extends BoxDefaultRequestEntity {

    private String status;

    private BoxCollabRequestEntity() {
    }

    /**
     * Entity to create a collaboration.
     * 
     * @param folderId
     *            id of the folder
     * @param userId
     *            id of the user to collaborate, this is optional, if you don't want to supply a user id, use null.
     * @param login
     *            login email of the collaborator(Can be non-box email.)
     * @param role
     *            role/access level of this collaboration(This is a String defined in {@link com.box.boxjavalibv2.dao.CollaborationRole}
     * @return BoxCollabRequestEntity
     */
    public static BoxCollabRequestEntity createCollabEntity(final String folderId, final String userId, final String login, final String role) {
        BoxCollabRequestEntity entity = new BoxCollabRequestEntity();
        MapJSONStringEntity item = getItemEntity(folderId);
        MapJSONStringEntity accessibleBy = getAccessibilityEntity(userId, login);
        entity.setItem(item);
        entity.setAccessibleBy(accessibleBy);
        entity.setRole(role);
        return entity;
    }

    /**
     * Entity to update a collaboration.
     * 
     * @param role
     *            role/access level of this collaboration(This is a String defined in {@link com.box.boxjavalibv2.dao.CollaborationRole}.
     * @return
     */
    public static BoxCollabRequestEntity updateCollabEntity(final String role) {
        BoxCollabRequestEntity entity = new BoxCollabRequestEntity();
        entity.setRole(role);
        return entity;
    }

    /** Set the item. */
    public void setItem(MapJSONStringEntity item) {
        put(BoxCollaboration.FIELD_FOLDER, item);
    }

    @Override
    public void applyToRequestObject(IBoxRequestObject obj) {
        super.applyToRequestObject(obj);
        if (StringUtils.isNotEmpty(status)) {
            ((BoxDefaultRequestObject) obj).addQueryParam(BoxCollaboration.FIELD_STATUS, status);
        }
    }

    public void setAccessibleBy(MapJSONStringEntity accessibleBy) {
        super.put(BoxCollaboration.FIELD_ACCESSIBLE_BY, accessibleBy);
    }

    /** Set the role. */
    public void setRole(String role) {
        put(BoxCollaboration.FIELD_ROLE, role);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private static MapJSONStringEntity getItemEntity(String folderId) {
        MapJSONStringEntity entity = new MapJSONStringEntity();
        entity.put("id", folderId);
        entity.put("type", BoxResourceType.FOLDER.toString());
        return entity;
    }

    private static MapJSONStringEntity getAccessibilityEntity(final String userId, final String login) {
        MapJSONStringEntity entity = new MapJSONStringEntity();
        if (StringUtils.isNotEmpty(userId)) {
            entity.put("id", userId);
        }
        entity.put("login", login);
        return entity;
    }
}
