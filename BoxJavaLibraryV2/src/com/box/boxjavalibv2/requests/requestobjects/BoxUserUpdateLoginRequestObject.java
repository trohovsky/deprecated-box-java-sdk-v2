package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxUser;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;

public class BoxUserUpdateLoginRequestObject extends BoxDefaultRequestObject {

    public BoxUserUpdateLoginRequestObject(IBoxJSONParser parser) {
        super(parser);
        setJSONEntity(new MapJSONStringEntity());
    }

    /**
     * Request object to update user's primary login.
     * 
     * @param login
     * @return
     */
    public static BoxUserUpdateLoginRequestObject updateUserPrimaryLoginRequestObject(final String login, final IBoxJSONParser parser) {
        return (new BoxUserUpdateLoginRequestObject(parser)).setLogin(login);
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
