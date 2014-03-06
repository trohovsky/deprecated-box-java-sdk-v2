package com.box.boxjavalibv2.authorization;

/**
 * Interface for UI to generate auth.
 */
public interface IAuthFlowUI {

    /**
     * Authenticate.
     * 
     * @param listener
     *            listener listening to events/messages fired during authentication process.
     */
    void authenticate(IAuthFlowListener listener);

    /**
     * Initialize the UI for OAuth flow. This needs to be called everytime before starting an auth flow.
     * 
     * @param activity
     * @param clientId
     * @param clientSecret
     */
    void initializeAuthFlow(final Object applicationContext, String clientId, String clientSecret);

    /**
     * Initialize the UI for OAuth flow. This needs to be called everytime before starting an auth flow.
     * 
     * @param activity
     * @param clientId
     * @param clientSecret
     * @param redirectUrl
     */
    void initializeAuthFlow(final Object applicationContext, String clientId, String clientSecret, String redirectUrl);

}
