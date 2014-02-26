package com.box.boxjavalibv2.requests.requestobjects;

public class BoxEmailAliasRequestObject extends BoxDefaultRequestObject {

    /**
     * Request object to add an email alias.
     * 
     * @param email
     * @return
     */
    public static BoxEmailAliasRequestObject addEmailAliasRequestObject(final String email) {
        return (new BoxEmailAliasRequestObject()).setEmailAlias(email);
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
