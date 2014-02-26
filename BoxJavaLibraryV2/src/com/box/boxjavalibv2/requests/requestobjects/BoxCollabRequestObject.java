package com.box.boxjavalibv2.requests.requestobjects;

import org.apache.commons.lang.StringUtils;

import com.box.boxjavalibv2.dao.BoxCollaboration;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;

/**
 * Entity for adding collaboration request.
 */
public class BoxCollabRequestObject extends BoxDefaultRequestObject {

    /**
     * Constructor.
     * 
     */
    private BoxCollabRequestObject() {
    }

    /**
     * Create an request object used to do create Collaboration request.
     * 
     * @param folderId
     *            id of the folder
     * @param userId
     *            id of the user to collaborate, this is optional, if you don't want to supply a user id, use null.
     * @param login
     *            login email of the collaborator(Can be non-box email.)
     * @param role
     *            role/access level of this collaboration(This is a String defined in {@link com.box.boxjavalibv2.dao.CollaborationRole}
     * @return BoxCollabRequestObject
     */
    public static BoxCollabRequestObject createCollaborationObject(final String folderId, final String userId, final String login, final String role) {
        MapJSONStringEntity item = getItemEntity(folderId);
        MapJSONStringEntity accessibleBy = getAccessibilityEntity(userId, login);
        return (new BoxCollabRequestObject()).setItem(item).setAccessibleBy(accessibleBy).setRole(role);
    }

    /**
     * Create an v used to make update collaboration request.
     * 
     * @param role
     *            role/access level of this collaboration(This is a String defined in {@link com.box.boxjavalibv2.dao.CollaborationRole}
     * @return BoxCollabRequestObject
     */
    public static BoxCollabRequestObject updateCollaborationObject(final String role) {
        return (new BoxCollabRequestObject()).setRole(role);
    }

    /**
     * Create an request object used to make get all collaborations request.
     * 
     * @param status
     *            status of the collaborations requested( This field is required and currently only support
     *            {@link com.box.boxjavalibv2.dao.BoxCollaboration.STATUS_PENDING}
     * @return BoxCollabRequestObject
     */
    public static BoxCollabRequestObject getAllCollaborationsRequestObject(final String status) {
        return (new BoxCollabRequestObject()).setStatus(status);
    }

    /**
	 */
    public BoxCollabRequestObject setStatus(String status) {
        this.addQueryParam(BoxCollaboration.FIELD_STATUS, status);
        return this;
    }

    /** Set the item. */
    private BoxCollabRequestObject setItem(MapJSONStringEntity item) {
        put(BoxCollaboration.FIELD_FOLDER, item);
        return this;
    }

    private BoxCollabRequestObject setAccessibleBy(MapJSONStringEntity accessibleBy) {
        super.put(BoxCollaboration.FIELD_ACCESSIBLE_BY, accessibleBy);
        return this;
    }

    /** Set the role. */
    private BoxCollabRequestObject setRole(String role) {
        put(BoxCollaboration.FIELD_ROLE, role);
        return this;
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
