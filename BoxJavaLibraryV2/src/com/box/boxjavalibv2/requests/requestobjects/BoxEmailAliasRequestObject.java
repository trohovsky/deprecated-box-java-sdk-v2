package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;

public class BoxEmailAliasRequestObject extends BoxDefaultRequestObject {

    private BoxEmailAliasRequestObject(IBoxJSONParser parser) {
        super(parser);
    }

    /**
     * Request object to add an email alias.
     * 
     * @param email
     * @return
     */
    public static BoxEmailAliasRequestObject addEmailAliasRequestObject(final String email, IBoxJSONParser parser) {
        return (new BoxEmailAliasRequestObject(parser)).setEmailAlias(email);
    }

    /**
     * Set email alias.
     * 
     * @param email
     * @return
     */
    private BoxEmailAliasRequestObject setEmailAlias(final String email) {
        put("email", email);
        return this;
    }
}
