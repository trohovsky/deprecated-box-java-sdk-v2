package com.box.boxjavalibv2.requests.requestentities;

import org.apache.commons.lang.StringUtils;

public class BoxOAuthRequestEntity extends BoxDefaultRequestEntity {

    private final static String GRANT_TYPE = "grant_type";
    private final static String CODE = "code";
    private final static String CLIENT_ID = "client_id";
    private final static String CLIENT_SECRET = "client_secret";
    private final static String REDIRECT_URL = "redirect_url";
    private final static String REFRESH_TOKEN = "refresh_token";
    private final static String AUTHORIZATION_CODE = "authorization_code";
    private final static String REVOKE_TOKEN = "token";
    private final static String DEVICE_ID = "box_device_id";
    private final static String DEVICE_NAME = "box_device_name";

    private BoxOAuthRequestEntity() {
    }

    /**
     * Request object to create OAUth.
     * 
     * @param code
     *            The authorization code you retrieved previously used to create OAuth.
     * @param clientId
     *            client id
     * @param clientSecret
     *            client secret
     * @param redirectUri
     *            optional, required only if a redirect URI is not configured at <a href="http://box.com/developers/services">Box Developers Services</a>, use
     *            null if don't want to supply this field.
     * @return BoxOAuthRequestObject
     */
    public static BoxOAuthRequestEntity createOAuthRequestEntity(final String code, final String clientId, final String clientSecret, final String redirectUrl) {
        BoxOAuthRequestEntity entity = new BoxOAuthRequestEntity();
        entity.setAuthCode(code);
        entity.setClient(clientId, clientSecret);
        entity.setRedirectUrl(redirectUrl);
        return entity;
    }

    public static BoxOAuthRequestEntity refreshOAuthRequestEntity(final String refreshToken, final String clientId, final String clientSecret) {
        BoxOAuthRequestEntity entity = new BoxOAuthRequestEntity();
        entity.setRefreshToken(refreshToken);
        entity.setClient(clientId, clientSecret);
        return entity;
    }

    /**
     * Request object to revoke OAuth.
     * 
     * @param revokeToken
     *            The access_token or refresh_token to be destroyed. Only one is required, though both will be destroyed.
     * @param clientId
     * @param clientSecret
     * @return
     */
    public static BoxOAuthRequestEntity revokeOAuthRequestEntity(final String revokeToken, final String clientId, final String clientSecret) {
        BoxOAuthRequestEntity entity = new BoxOAuthRequestEntity();
        entity.setRevokeToken(revokeToken);
        entity.setClient(clientId, clientSecret);
        return entity;
    }

    /**
     * Set the token to revoke.
     * 
     * @param token
     *            The access_token or refresh_token to be destroyed. Only one is required, though both will be destroyed.
     * @return
     */
    public void setRevokeToken(final String token) {
        put(REVOKE_TOKEN, token);
    }

    public void setRefreshToken(final String refreshToken) {
        put(GRANT_TYPE, REFRESH_TOKEN);
        put(REFRESH_TOKEN, refreshToken);
    }

    /**
     * @param code
     *            The authorization code you retrieved previously used to create OAuth.
     * @return
     */
    public void setAuthCode(String code) {
        put(GRANT_TYPE, AUTHORIZATION_CODE);
        put(CODE, code);
    }

    public void setClient(String clientId, String clientSecret) {
        put(CLIENT_ID, clientId);
        put(CLIENT_SECRET, clientSecret);
    }

    public void setRedirectUrl(String redirectUrl) {
        put(REDIRECT_URL, redirectUrl);
    }

    public void setDevice(String deviceId, String deviceName) {
        if (StringUtils.isNotEmpty(deviceId) && StringUtils.isNotEmpty(deviceName)) {
            put(DEVICE_ID, deviceId);
            put(DEVICE_NAME, deviceName);
        }
    }
}
