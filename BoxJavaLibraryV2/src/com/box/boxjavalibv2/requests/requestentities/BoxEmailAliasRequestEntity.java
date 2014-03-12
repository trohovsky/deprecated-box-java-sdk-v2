package com.box.boxjavalibv2.requests.requestentities;


public class BoxEmailAliasRequestEntity extends BoxDefaultRequestEntity {

    private BoxEmailAliasRequestEntity() {
    }

    /**
     * Request entity to add an email alias.
     * 
     * @param email
     * @return
     */
    public static BoxEmailAliasRequestEntity addEmailAliasRequestEntity(final String email) {
        BoxEmailAliasRequestEntity entity = new BoxEmailAliasRequestEntity();
        entity.setEmailAlias(email);
        return entity;
    }

    /**
     * Set email alias.
     * 
     * @param email
     * @return
     */
    private void setEmailAlias(final String email) {
        put("email", email);
    }
}
