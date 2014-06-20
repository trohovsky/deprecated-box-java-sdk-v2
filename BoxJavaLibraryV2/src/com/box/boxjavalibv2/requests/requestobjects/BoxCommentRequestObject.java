package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxComment;
import com.box.boxjavalibv2.dao.IBoxType;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;
import com.box.restclientv2.requestsbase.BoxDefaultRequestObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoxCommentRequestObject extends BoxDefaultRequestObject {

    private BoxCommentRequestObject() {
    }

    /**
     * A BoxCommentRequestObject to add a comment.
     *
     * @param type
     *            type of the item to be commented
     * @param itemId
     *            id of the item
     * @param message
     *            comment message
     * @return BoxCommentRequestObject
     */
    public static BoxCommentRequestObject addCommentRequestObject(final IBoxType type, final String itemId, final String message) {
        BoxCommentRequestObject obj = new BoxCommentRequestObject();
        Pattern regex = Pattern.compile("@\\[\\d+:(.*?)\\]");
        Matcher matcher = regex.matcher(message);
        if (matcher.find()) {
            return obj.setItem(type, itemId).setTaggedMessage(message);

        }
        return obj.setItem(type, itemId).setMessage(message);
    }

    /**
     * A BoxCommentRequestEntity to update comment.
     *
     * @param message
     *            comment message
     * @return BoxCommentRequestObject
     */
    public static BoxCommentRequestObject updateCommentRequestObject(final String message) {
        BoxCommentRequestObject obj = new BoxCommentRequestObject();
        return obj.setMessage(message);
    }

    /**
     * Set the comment message.
     *
     * @param message
     *            comment message
     * @return BoxCommentRequestObject
     */
    public BoxCommentRequestObject setMessage(final String message) {
        put(BoxComment.FIELD_MESSAGE, message);
        return this;
    }

    /**
     * Set the comment message.
     *
     * @param message
     *            comment message
     * @return BoxCommentRequestObject
     */
    public BoxCommentRequestObject setTaggedMessage(final String message) {
        System.out.println("DBUG++WHOOOO IT WORKD");
        put(BoxComment.FIELD_TAGGED_MESSAGE, message);
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
    public BoxCommentRequestObject setItem(final IBoxType type, final String itemId) {
        put(BoxComment.FIELD_ITEM, getItemEntity(type, itemId));
        return this;
    }

    private static MapJSONStringEntity getItemEntity(final IBoxType type, final String itemId) {
        MapJSONStringEntity entity = new MapJSONStringEntity();
        entity.put("type", type.toString());
        entity.put("id", itemId);
        return entity;
    }
}
