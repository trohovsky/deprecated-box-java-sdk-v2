package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxUser;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;

public class BoxUserUpdateLoginRequestObject extends BoxDefaultRequestObject {

    public BoxUserUpdateLoginRequestObject() {
        super();
        setJSONEntity(new MapJSONStringEntity());
    }

    /**
     * Request object to update user's primary login.
     * 
     * @param login
     * @return
     */
    public static BoxUserUpdateLoginRequestObject updateUserPrimaryLoginRequestObject(final String login) {
        return (new BoxUserUpdateLoginRequestObject()).setLogin(login);
    }

    /**
     * Set Login(email) of the user.
     * 
     * @param login
     *            login
     * @return
     */
    private BoxUserUpdateLoginRequestObject setLogin(final String login) {
        put(BoxUser.FIELD_LOGIN, login);
        return this;
    }
}
