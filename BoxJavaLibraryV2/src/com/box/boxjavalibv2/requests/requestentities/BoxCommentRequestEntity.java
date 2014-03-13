package com.box.boxjavalibv2.requests.requestentities;

import com.box.boxjavalibv2.dao.BoxComment;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.dao.IBoxType;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;

public class BoxCommentRequestEntity extends BoxDefaultRequestEntity {

    private BoxCommentRequestEntity() {
    }

    /**
     * A BoxCommentRequestEntity to add a comment.
     * 
     * @param type
     *            type of the item to be commented
     * @param itemId
     *            id of the item
     * @param message
     *            comment message
     * @return BoxCommentRequestObject
     */
    public static BoxCommentRequestEntity addCommentRequestEntity(final IBoxType type, final String itemId, final String message) {
        BoxCommentRequestEntity entity = new BoxCommentRequestEntity();
        entity.setItem(type, itemId);
        entity.setMessage(message);
        return entity;
    }

    /**
     * A BoxCommentRequestEntity to update comment.
     * 
     * @param message
     *            comment message
     * @return BoxCommentRequestObject
     */
    public static BoxCommentRequestEntity updateCommentRequestEntity(final String message) {
        BoxCommentRequestEntity entity = new BoxCommentRequestEntity();
        entity.setMessage(message);
        return entity;
    }

    /**
     * Set the comment message.
     * 
     * @param message
     *            comment message
     * @return BoxCommentRequestObject
     */
    public void setMessage(final String message) {
        put(BoxComment.FIELD_MESSAGE, message);
    }

    /**
     * Set the item to be commented.
     * 
     * @param type
     *            type of the item
     * @param itemId
     *            id of the item
     * @return BoxCommentRequestObject
     */
    public void setItem(final IBoxType type, final String itemId) {
        put(BoxComment.FIELD_ITEM, getItemEntity(type, itemId));
    }

    private static MapJSONStringEntity getItemEntity(final IBoxType type, final String itemId) {
        MapJSONStringEntity entity = new MapJSONStringEntity();
        entity.put("type", type.toString());
        entity.put("id", itemId);
        return entity;
    }
}
