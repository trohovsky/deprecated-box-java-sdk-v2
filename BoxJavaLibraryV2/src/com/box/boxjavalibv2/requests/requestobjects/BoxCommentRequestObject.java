package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxComment;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;

/**
 * Entity for a comment message.
 */
public class BoxCommentRequestObject extends BoxDefaultRequestObject {

    private static final String MESSAGE = BoxComment.FIELD_MESSAGE;

    private BoxCommentRequestObject(IBoxJSONParser parser) {
        super(parser);
    }

    /**
     * A BoxCommentRequestObject for AddCommentRequest.
     * 
     * @param type
     *            type of the item to be commented
     * @param itemId
     *            id of the item
     * @param message
     *            comment message
     * @return BoxCommentRequestObject
     */
    public static BoxCommentRequestObject addCommentRequestObject(final BoxResourceType type, final String itemId, final String message,
        final IBoxJSONParser parser) {
        return (new BoxCommentRequestObject(parser)).setItem(type, itemId).setMessage(message);
    }

    /**
     * A BoxCommentRequestObject for UpdateCommentRequest.
     * 
     * @param message
     *            comment message
     * @return BoxCommentRequestObject
     */
    public static BoxCommentRequestObject updateCommentRequestObject(final String message, final IBoxJSONParser parser) {
        return (new BoxCommentRequestObject(parser)).setMessage(message);
    }

    /**
     * Set the comment message.
     * 
     * @param message
     *            comment message
     * @return BoxCommentRequestObject
     */
    public BoxCommentRequestObject setMessage(final String message) {
        put(MESSAGE, message);
        return this;
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
    public BoxCommentRequestObject setItem(final BoxResourceType type, final String itemId) {
        put(BoxComment.FIELD_ITEM, getItemEntity(type, itemId));
        return this;
    }

    private static MapJSONStringEntity getItemEntity(final BoxResourceType type, final String itemId) {
        MapJSONStringEntity entity = new MapJSONStringEntity();
        entity.put("type", type.toString());
        entity.put("id", itemId);
        return entity;
    }
}
