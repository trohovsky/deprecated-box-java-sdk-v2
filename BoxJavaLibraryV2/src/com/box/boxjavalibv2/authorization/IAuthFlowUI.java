package com.box.boxjavalibv2.authorization;

import com.box.boxjavalibv2.BoxClient;

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
     */
    void initializeAuthFlow(final Object applicationContext, String clientId, String clientSecret);

    /**
     * Initialize the UI for OAuth flow. This needs to be called everytime before starting an auth flow.
     */
    void initializeAuthFlow(final Object applicationContext, String clientId, String clientSecret, String redirectUrl);

    void initializeAuthFlow(Object activity, String clientId, String clientSecret, String redirectUrl, BoxClient boxClient);

}
