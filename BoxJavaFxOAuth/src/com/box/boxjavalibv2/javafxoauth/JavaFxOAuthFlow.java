package com.box.boxjavalibv2.javafxoauth;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.BoxConfigBuilder;
import com.box.boxjavalibv2.authorization.IAuthFlowListener;
import com.box.boxjavalibv2.authorization.IAuthFlowUI;
import com.box.boxjavalibv2.authorization.OAuthDataMessage;
import com.box.boxjavalibv2.authorization.OAuthWebViewData;
import com.box.boxjavalibv2.dao.BoxOAuthToken;
import com.box.boxjavalibv2.events.OAuthEvent;
import com.box.restclientv2.httpclientsupport.HttpClientURIBuilder;

/**
 * A class that implements IAuthFlowUI using a JavaFX WebView.
 */
public class JavaFxOAuthFlow implements IAuthFlowUI {

    /**
     * Indicates whether or not we've already completed authentication.
     */
    private final AtomicBoolean oauthCreated = new AtomicBoolean(false);

    /**
     * Used to get and set data in a WebView URL.
     */
    private OAuthWebViewData mWebViewData;

    /**
     * Client for authenticating with the API and retrieving items.
     */
    private BoxClient client;

    /**
     * The engine of {@link webView webView}.
     */
    private WebEngine webEngine;

    /**
     * WebView used to authenticate with the API via OAuth2.
     */
    private WebView webView;

    /**
     * Listener where OAuth events will be sent.
     */
    private final IAuthFlowListener oauthListener;

    /**
     * Maximum allowable width for {@link webView}.
     */
    private final double maxWidth;

    /**
     * Maximum allowable height for {@link webView}.
     */
    private final double maxHeight;

    /**
     * Minimum allowable width for {@link webView}.
     */
    private final double minWidth;

    /**
     * Minimum allowable height for {@link webView}.
     */
    private final double minHeight;

    /**
     * Constructs a JavaFxOAuthFlow with a minimum and a maximum {@link WebView} size that will send
     * {@link OAuthEvent OAuthEvents} to a provided {@link IAuthFlowListener}.
     *
     * @param  webViewMaxWidth  maximum allowable width for the {@link WebView}
     * @param  webViewMaxHeight maximum allowable height for the {@link WebView}
     * @param  webViewMinWidth  minimum allowable width for the {@link WebView}
     * @param  webViewMinHeight minimum allowable height for the {@link WebView}
     * @param  listener         a listener that will receive {@link OAuthEvent OAuthEvents}
     */
    public JavaFxOAuthFlow(double webViewMaxWidth, double webViewMaxHeight, double webViewMinWidth,
        double webViewMinHeight, IAuthFlowListener listener) {

        this.oauthListener = listener;
        this.maxWidth = webViewMaxWidth;
        this.maxHeight = webViewMaxHeight;
        this.minWidth = webViewMinWidth;
        this.minHeight = webViewMinHeight;
    }

    /**
     * Gets the {@link WebView} used by this auth flow.
     *
     * @return the {@link WebView}
     */
    public WebView getWebView() {
        return webView;
    }

    /**
     * Initializes the JavaFX UI on its own thread.
     *
     * @param panel        the panel where the WebView will be placed
     * @param clientId     client ID to use when authenticating with the API
     * @param clientSecret client secret to use when authenticating with the API
     */
    public void initAuthAndRun(final JFXPanel panel, final String clientId, final String clientSecret) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                initializeAuthFlow(null, clientId, clientSecret);
                Group group = new Group();
                Scene scene = new Scene(group);

                panel.setScene(scene);
                group.getChildren().add(getWebView());

                authenticate(oauthListener);
            }
        });
    }

    /**
     * Intializes the WebView.
     *
     * @param applicationContext not used
     * @param clientId           client ID to use when authenticating with the API
     * @param clientSecret       client secret to use when authenticating with the API
     */
    @Override
    public void initializeAuthFlow(final Object applicationContext, String clientId, String clientSecret) {
        initializeAuthFlow(applicationContext, clientId, clientSecret, null);
    }

    /**
     * Intializes the WebView with a specific redirect URL and client.
     *
     * @param applicationContext not used
     * @param clientId           client ID to use when authenticating with the API
     * @param clientSecret       client secret to use when authenticating with the API
     * @param redirectUrl        redirect URL to use with OAuth
     * @param boxClient          client to authenticate
     */
    @Override
    public void initializeAuthFlow(Object applicationContext, String clientId, String clientSecret, String redirectUrl,
        BoxClient boxClient) {

        mWebViewData = new OAuthWebViewData(boxClient.getOAuthDataController());
        if (StringUtils.isNotEmpty(redirectUrl)) {
            mWebViewData.setRedirectUrl(redirectUrl);
        }
        webView = new WebView();
        webView.setMinSize(minWidth, minHeight);
        webView.setMaxSize(maxWidth, maxHeight);
        webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.setOnStatusChanged(createEventHandler());
    }

    /**
     * Intializes the WebView with a specific redirect URL.
     *
     * @param activity           not used
     * @param clientId           client ID to use when authenticating with the API
     * @param clientSecret       client secret to use when authenticating with the API
     * @param redirectUrl        redirect URL to use with OAuth
     */
    @Override
    public void initializeAuthFlow(Object activity, String clientId, String clientSecret, String redirectUrl) {
        client = new BoxClient(clientId, clientSecret, null, null, (new BoxConfigBuilder()).build());
        initializeAuthFlow(activity, clientId, clientSecret, redirectUrl, client);
    }

    /**
     * Authenticate the client.
     *
     * @param listener listener that will receive {@link OAuthEvent OAuthEvents}
     */
    @Override
    public void authenticate(IAuthFlowListener listener) {
        try {
            webEngine.load(mWebViewData.buildUrl().toString());
        } catch (URISyntaxException e) {
            oauthListener.onAuthFlowException(e);
        }
    }

    /**
     * Called when authentication succeeds.
     *
     * @param token the auth token
     */
    private void exitSuccess(final BoxOAuthToken token) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                try {
                    oauthListener.onAuthFlowEvent(OAuthEvent.OAUTH_CREATED,
                        new OAuthDataMessage(token, client.getJSONParser(), client.getResourceHub()));
                } catch (Exception e) {
                    oauthListener.onAuthFlowException(e);
                }
                Platform.exit();
            }
        });

    }

    /**
     * Called when authentication fails.
     *
     * @param e an exception indicating why authentication failed
     */
    private void exitException(final Exception e) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                oauthListener.onAuthFlowException(e);
                Platform.exit();
            }
        });
    }

    /**
     * Creates an EventHandler for {@link webEngine}.
     *
     * @return the created EventHandler
     */
    private EventHandler<WebEvent<String>> createEventHandler() {
        return new EventHandler<WebEvent<String>>() {

            @Override
            public void handle(WebEvent<String> event) {
                if (event.getSource() instanceof WebEngine) {
                    WebEngine engine = (WebEngine) event.getSource();
                    String url = engine.getLocation();
                    String code = getResponseValueFromUrl(url);
                    if (StringUtils.isNotEmpty(code)) {
                        webEngine.getLoadWorker().cancel();
                        startCreateOAuth(code);
                    }
                }
            }
        };
    }

    /**
     * Begins creating the OAuth token.
     *
     * @param code the code to use to obtain the OAuth token
     */
    private void startCreateOAuth(final String code) {
        if (oauthCreated.getAndSet(true)) {
            return;
        }

        Thread t = new Thread() {

            @Override
            public void run() {
                BoxOAuthToken oauth = null;
                try {
                    oauth = client.getOAuthManager().createOAuth(code, mWebViewData.getClientId(),
                        mWebViewData.getClientSecret(), mWebViewData.getRedirectUrl());
                    exitSuccess(oauth);
                } catch (Exception e) {
                    exitException(e);
                }
            }
        };
        t.start();
    }

    /**
     * Get the response value.
     *
     * @param  url the URL to get the response from
     * @return     the response value
     */
    private String getResponseValueFromUrl(final String url) {
        HttpClientURIBuilder builder;
        try {
            builder = new HttpClientURIBuilder(url);
        } catch (URISyntaxException e) {
            return null;
        }

        List<NameValuePair> query = builder.getQueryParams();
        for (NameValuePair pair : query) {
            if (pair.getName().equalsIgnoreCase(mWebViewData.getResponseType())) {
                return pair.getValue();
            }
        }
        return null;
    }

    /**
     * Not used.
     *
     * @param listener not used
     */
    @Override
    public void addAuthFlowListener(IAuthFlowListener listener) {
    }
}
