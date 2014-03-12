package com.box.boxjavalibv2.requests.requestentities;

import com.box.boxjavalibv2.dao.BoxEmail;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;

public class BoxFolderRequestEntity extends BoxItemRequestEntity {

    /**
     * Set the email-to-upload address for this folder.
     * 
     * @param access
     *            access level
     * @param email
     *            email address
     * @return
     */
    public void setUploadEmail(String access, String email) {
        MapJSONStringEntity entity = new MapJSONStringEntity();
        entity.put(BoxEmail.FIELD_ACCESS, access);
        entity.put(BoxEmail.FIELD_EMAIL, email);
        put(BoxFolder.FIELD_FOLDER_UPLOAD_EMAIL, entity);
    }

    public static BoxFolderRequestEntity getRequestEntity() {
        return (new BoxFolderRequestEntity());
    }

    public static BoxFolderRequestEntity createFolderRequestEntity(String name, String parentId, final IBoxJSONParser parser) {
        BoxFolderRequestEntity entity = new BoxFolderRequestEntity();
        entity.setName(name);
        entity.setParent(parentId);
        return entity;
    }
}
