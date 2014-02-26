package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxUser;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;

public class BoxSimpleUserRequestObject extends BoxDefaultRequestObject {

    /**
     * Request object to move a folder to another user.
     * 
     * @param destinationFolderId
     *            the ID of the user who the folder will be transferred to
     * @param notify
     *            whether destination user should receive email notification of the transfer
     * @return
     */
    public static BoxSimpleUserRequestObject moveFolderToAnotherUserRequestObject(final String destinationUserId, final boolean notify) {
        return (new BoxSimpleUserRequestObject()).setNotifyUser(notify).setDestinationUser(destinationUserId);
    }

    /**
     * Set destination user, this is only used in request to move a folder to another user's account.
     * 
     * @param destinationFolderId
     *            the ID of the user who the folder will be transferred to
     * @return
     */
    private BoxSimpleUserRequestObject setDestinationUser(final String destinationUserId) {
        MapJSONStringEntity id = new MapJSONStringEntity();
        id.put(BoxUser.FIELD_ID, destinationUserId);
        put("owned_by", id);
        return this;
    }

    /**
     * Set whether the user should receive an email notification. This applies to the case when they are rolled out of an enterprise or when somebody moves a
     * folder into the user's account.
     * 
     * @param notify
     * @return
     */
    public BoxSimpleUserRequestObject setNotifyUser(final boolean notify) {
        addQueryParam("notify", Boolean.toString(notify));
        return this;
    }
}
