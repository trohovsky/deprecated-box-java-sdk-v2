package com.box.boxjavalibv2.requests.requestentities;

import com.box.boxjavalibv2.dao.BoxGroup;
import com.box.boxjavalibv2.dao.BoxGroupMembership;
import com.box.boxjavalibv2.dao.BoxUser;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;

public class BoxGroupMembershipRequestEntity extends BoxDefaultRequestEntity {

    private BoxGroupMembershipRequestEntity() {
    }

    /**
     * 
     * @param groupId
     *            id of the group
     * @param userId
     *            id of the user to be added.
     * @param role
     *            role of the user.
     * @return
     */
    public static BoxGroupMembershipRequestEntity addMembershipRequestEntity(final String groupId, final String userId, final String role) {
        BoxGroupMembershipRequestEntity entity = new BoxGroupMembershipRequestEntity();
        entity.setGroup(groupId);
        entity.setUser(userId);
        entity.setRole(role);
        return entity;
    }

    public static BoxGroupMembershipRequestEntity updateMembershipRequestEntity(final String role) {
        BoxGroupMembershipRequestEntity entity = new BoxGroupMembershipRequestEntity();
        entity.setRole(role);
        return entity;
    }

    public void setGroup(final String groupId) {
        MapJSONStringEntity groupEntity = new MapJSONStringEntity();
        groupEntity.put(BoxGroup.FIELD_ID, groupId);
        put(BoxGroupMembership.FIELD_GROUP, groupEntity);
    }

    /**
     * @param role
     *            role of the user.
     */
    public void setRole(final String role) {
        put(BoxGroupMembership.FIELD_ROLE, role);
    }

    public void setUser(final String userId) {
        MapJSONStringEntity userEntity = new MapJSONStringEntity();
        userEntity.put(BoxUser.FIELD_ID, userId);
        put(BoxGroupMembership.FIELD_USER, userEntity);
    }
}
