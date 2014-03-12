package com.box.boxjavalibv2.requests.requestentities;

import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.dao.BoxUser;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.IBoxRequestObject;

public class BoxSimpleUserRequestEntity extends BoxDefaultRequestEntity {

    private final static String NOTIFY = "notify";
    private Boolean notify = null;

    protected BoxSimpleUserRequestEntity() {
    }

    /**
     * Request entity to move a folder to another user.
     * 
     * @param destinationFolderId
     *            the ID of the user who the folder will be transferred to
     * @param notify
     *            whether destination user should receive email notification of the transfer
     * @return
     */
    public static BoxSimpleUserRequestEntity moveFolderToAnotherUserRequestEntity(final String destinationUserId, final boolean notify) {
        BoxSimpleUserRequestEntity entity = new BoxSimpleUserRequestEntity();
        entity.setNotifyUser(notify);
        entity.setDestinationUser(destinationUserId);
        return entity;
    }

    @Override
    public void applyToRequestObject(IBoxRequestObject obj) {
        super.applyToRequestObject(obj);
        if (notify != null) {
            ((BoxDefaultRequestObject) obj).addQueryParam(NOTIFY, Boolean.toString(notify));
        }
    }

    /**
     * Set destination user, this is only used in request to move a folder to another user's account.
     * 
     * @param destinationFolderId
     *            the ID of the user who the folder will be transferred to
     * @return
     */
    private void setDestinationUser(final String destinationUserId) {
        MapJSONStringEntity id = new MapJSONStringEntity();
        id.put(BoxUser.FIELD_ID, destinationUserId);
        put(BoxItem.FIELD_OWNED_BY, id);
    }

    /**
     * Set whether the user should receive an email notification. This applies to the case when they are rolled out of an enterprise or when somebody moves a
     * folder into the user's account.
     * 
     * @param notify
     * @return
     */
    public void setNotifyUser(final boolean notify) {
        this.notify = notify;
    }
}
