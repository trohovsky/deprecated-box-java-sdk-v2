package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxEmail;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;

public class BoxFolderUpdateRequestObject extends BoxFolderRequestObject {

    private BoxFolderUpdateRequestObject(IBoxJSONParser parser) {
        super(parser);
    }

    /**
     * Set the email-to-upload address for this folder.
     * 
     * @param access
     *            access level
     * @param email
     *            email address
     * @return
     */
    public BoxFolderRequestObject setUploadEmail(String access, String email) {
        MapJSONStringEntity entity = new MapJSONStringEntity();
        entity.put(BoxEmail.FIELD_ACCESS, access);
        entity.put(BoxEmail.FIELD_EMAIL, email);
        put(BoxFolder.FIELD_FOLDER_UPLOAD_EMAIL, entity);
        return this;
    }

    public static BoxFolderUpdateRequestObject updateFolderInfoRequestObject(final IBoxJSONParser parser) {
        return (new BoxFolderUpdateRequestObject(parser));
    }
}
