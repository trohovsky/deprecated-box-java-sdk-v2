/*******************************************************************************
 * Copyright 2011 Box.net.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 ******************************************************************************/
package com.box.boxjavalibv2;

import java.util.Locale;

import com.box.restclientv2.interfaces.IBoxConfig;

/**
 * Class for retrieving configuration parameters. This is a singleton and should be the same across the whole app.
 */
public class BoxConfig implements IBoxConfig {

    /** Default API url scheme. */
    private static final String API_URL_SCHEME = "https";
    /** Default API url authority. */
    private static final String API_URL_AUTHORITY = "api.box.com";
    /** Default API url path. */
    private static final String API_URL_PATH = "/2.0";

    /** Default Upload url scheme. */
    private static final String UPLOAD_URL_SCHEME = "https";
    /** Default Upload url authority. */
    private static final String UPLOAD_URL_AUTHORITY = "upload.box.com";
    /** Default upload API url path. */
    private static final String UPLOAD_URL_PATH = "/api/2.0";

    /** Default OAuth url scheme. */
    private static final String OAUTH_URL_SCHEME = "https";
    /** Default OAuth url authority. */
    private static final String OAUTH_URL_AUTHORITY = "www.box.com";
    /** Default OAuth API url path. */
    private static final String OAUTH_API_URL_PATH = "/api";
    /** Default OAuth url path. */
    private static final String OAUTH_WEB_URL_PATH = "/api/oauth2/authorize";

    /** Default Download url scheme. */
    private static final String DOWNLOAD_URL_SCHEME = "https";
    /** Default Download url authority. */
    private static final String DOWNLOAD_URL_AUTHORITY = "api.box.com";
    /** Default Download url authority. */
    private static final String DOWNLOAD_URL_PATH = "/2.0";
    /** Default User-Agent String. */
    private static final String USER_AGENT = "BoxJavaLibraryV2";

    /** OAUTH url scheme. */
    private String mOAuthUrlScheme = OAUTH_URL_SCHEME;

    /** OAUTH url authority. */
    private String mOAuthUrlAuthority = OAUTH_URL_AUTHORITY;
    /** OAuth api url path. */
    private String mOAuthApiUrlPath = OAUTH_API_URL_PATH;
    /** OAUTH web url path. */
    private String mOAuthWebUrlPath = OAUTH_WEB_URL_PATH;

    /** API url scheme. */
    private String mApiUrlScheme = API_URL_SCHEME;
    /** API url authority. */
    private String mApiUrlAuthority = API_URL_AUTHORITY;
    /** API url path. */
    private String mApiUrlPath = API_URL_PATH;

    /** Upload url scheme. */
    private String mUploadUrlScheme = UPLOAD_URL_SCHEME;
    /** Upload url authority. */
    private String mUploadUrlAuthority = UPLOAD_URL_AUTHORITY;
    /** Upload url PATH. */
    private String mUploadUrlPath = UPLOAD_URL_PATH;

    /** Download url path. */
    private String mDownloadUrlScheme = DOWNLOAD_URL_SCHEME;
    /** Download url authority. */
    private String mDownloadUrlAuthority = DOWNLOAD_URL_AUTHORITY;
    /** Download url path. */
    private String mDownloadUrlPath = DOWNLOAD_URL_PATH;
    /** User-Agent String to use. */
    private String mUserAgent = USER_AGENT;
    /** Enable Http Logging Flag NEVER ENABLE HTTP LOGGIN FOR PRODUCTION BUILDS. */
    private boolean mEnableHttpLogging = false;

    /** Time to wait before connection timeout. */
    private static int mConnectionTimout = 0;

    /** Singleton instance. */
    private static BoxConfig mInstance;

    /**
     * Private constructor.
     */
    protected BoxConfig() {
    }

    /**
     * Get a singleton instance.
     * 
     * @return instance of BoxConfig.
     */
    public synchronized static BoxConfig getInstance() {
        if (mInstance == null) {
            mInstance = new BoxConfig();
        }
        return mInstance;
    }

    /**
     * Set a custom API URL scheme.
     * 
     * @param scheme
     *            Custom scheme
     */
    public void setApiUrlScheme(final String scheme) {
        mApiUrlScheme = scheme;
    }

    /**
     * Get the API URL scheme.
     * 
     * @return API URL scheme
     */
    @Override
    public String getApiUrlScheme() {
        return mApiUrlScheme;
    }

    /**
     * Set a custom API URL Authority.
     * 
     * @param authority
     *            Custom Authority
     */
    public void setApiUrlAuthority(final String authority) {
        mApiUrlAuthority = authority;
    }

    /**
     * Get the API URL Authority.
     * 
     * @return API URL Authority
     */
    @Override
    public String getApiUrlAuthority() {
        return mApiUrlAuthority;
    }

    /**
     * Set a custom API URL path.
     * 
     * @param path
     *            Custom path
     */
    public void setApiUrlPath(final String path) {
        mApiUrlPath = path;
    }

    /**
     * Get the API URL path.
     * 
     * @return API URL path
     */
    @Override
    public String getApiUrlPath() {
        return mApiUrlPath;
    }

    /**
     * Set a custom Upload URL scheme.
     * 
     * @param scheme
     *            Custom scheme
     */
    public void setUploadUrlScheme(final String scheme) {
        mUploadUrlScheme = scheme;
    }

    /**
     * Get the Upload URL scheme.
     * 
     * @return Upload URL scheme
     */
    @Override
    public String getUploadUrlScheme() {
        return mUploadUrlScheme;
    }

    /**
     * Set a custom Upload URL Authority.
     * 
     * @param authority
     *            Custom Authority
     */
    public void setUploadUrlAuthority(final String authority) {
        mUploadUrlAuthority = authority;
    }

    /**
     * Get the Upload URL Authority.
     * 
     * @return Upload URL Authority
     */
    @Override
    public String getUploadUrlAuthority() {
        return mUploadUrlAuthority;
    }

    /**
     * Set a custom Download URL scheme.
     * 
     * @param scheme
     *            Custom scheme
     */
    public void setDownloadUrlScheme(final String scheme) {
        mDownloadUrlScheme = scheme;
    }

    /**
     * Get the Download URL scheme.
     * 
     * @return Download URL scheme
     */
    @Override
    public String getDownloadUrlScheme() {
        return mDownloadUrlScheme;
    }

    /**
     * Set a custom Download URL Authority.
     * 
     * @param authority
     *            Custom Authority
     */
    public void setDownloadUrlAuthority(final String authority) {
        mDownloadUrlAuthority = authority;
    }

    /**
     * Get the Download URL Authority.
     * 
     * @return Download URL Authority
     */
    @Override
    public String getDownloadUrlAuthority() {
        return mDownloadUrlAuthority;
    }

    /**
     * Set the amount of time in milliseconds that calls to the server should wait before timing out. Default is 0 which stands for infinite timeout.
     * 
     * @param timeout
     *            Desired connection timeout.
     */
    public void setConnectionTimeOut(final int timeout) {
        mConnectionTimout = timeout;
    }

    /**
     * Get the amount of time in milliseconds that calls to the server should wait before timing out.
     * 
     * @return The current connection timeout set.
     */
    @Override
    public int getConnectionTimeOut() {
        return mConnectionTimout;
    }

    /**
     * Set the String to use as the User-Agent HTTP header.
     * 
     * @param agent
     *            User-Agent String
     */
    public void setUserAgent(final String agent) {
        mUserAgent = agent;
    }

    /**
     * Get the User-Agent String to apply to the HTTP(S) calls.
     * 
     * @return String to use for User-Agent.
     */
    @Override
    public String getUserAgent() {
        return mUserAgent;
    }

    /**
     * @return the OAuthUrlScheme
     */
    @Override
    public String getOAuthUrlScheme() {
        return mOAuthUrlScheme;
    }

    /**
     * @param OAuthUrlScheme
     *            the OAuthUrlScheme to set
     */
    public void setAuthUrlScheme(String oAuthUrlScheme) {
        this.mOAuthUrlScheme = oAuthUrlScheme;
    }

    /**
     * @return the OAuthUrlAuthority
     */
    @Override
    public String getOAuthUrlAuthority() {
        return mOAuthUrlAuthority;
    }

    /**
     * @param OAuthUrlAuthority
     *            the OAuthUrlAuthority to set
     */
    public void setOAuthUrlAuthority(String oAuthUrlAuthority) {
        this.mOAuthUrlAuthority = oAuthUrlAuthority;
    }

    /**
     * @return the OAuthUrlPath
     */
    @Override
    public String getOAuthWebUrlPath() {
        return mOAuthWebUrlPath;
    }

    /**
     * @param OAuthUrlPath
     *            the OAuthUrlPath to set
     */
    public void setOAuthUrlPath(String oAuthUrlPath) {
        this.mOAuthWebUrlPath = oAuthUrlPath;
    }

    /**
     * SHOULD NEVER BE ENABLED FOR PRODUCTION BUILDS
     * 
     * Set the flag to enable HTTP Logging.
     * 
     * @param flag
     *            boolean to Enable Http Logging
     * 
     *            SHOULD NEVER BE ENABLED FOR PRODUCTION BUILDS
     */
    public void setEnableHttpLogging(final boolean flag) {
        mEnableHttpLogging = flag;
    }

    /**
     * Get the flag indicating whether Http Logging is enabled.
     * 
     * @return boolean.
     */
    public boolean getHttpLoggingEnabled() {
        return mEnableHttpLogging;
    }

    /** Accept-Language header value for English (US). */
    private static final String ENGLISH_ACCEPT_LANGUAGE = "en-us";

    /**
     * Get the Accept-Language HTTP header that we should set.
     * 
     * @return Accept-Language HTTP header.
     */
    @Override
    public String getAcceptLanguage() {
        if (Locale.getDefault() == null) {
            return ENGLISH_ACCEPT_LANGUAGE;
        }
        StringBuffer sb = new StringBuffer();
        String language = Locale.getDefault().getLanguage().toLowerCase().trim();
        if (language.length() > 0) {
            sb.append(language);
            String country = Locale.getDefault().getCountry().toLowerCase().trim();
            if (country.length() > 0) {
                sb.append("-");
                sb.append(country);
            }
        }
        if (sb.length() == 0) {
            sb.append(ENGLISH_ACCEPT_LANGUAGE);
        }
        else if (!sb.toString().equals(ENGLISH_ACCEPT_LANGUAGE)) {
            sb.append(", ");
            sb.append(ENGLISH_ACCEPT_LANGUAGE);
        }
        return sb.toString();
    }

    /**
     * @return the mOAuthApiUrlPath
     */
    @Override
    public String getOAuthApiUrlPath() {
        return mOAuthApiUrlPath;
    }

    /**
     * @param mOAuthApiUrlPath
     *            the mOAuthApiUrlPath to set
     */
    public void setOAuthApiUrlPath(String oAuthApiUrlPath) {
        this.mOAuthApiUrlPath = oAuthApiUrlPath;
    }

    @Override
    public String getUploadUrlPath() {
        return mUploadUrlPath;
    }

    /**
     * @param mUploadUrlPath
     *            the mUploadUrlPath to set
     */
    public void setUploadUrlPath(String uploadUrlPath) {
        this.mUploadUrlPath = uploadUrlPath;
    }

    @Override
    public String getDownloadUrlPath() {
        return mDownloadUrlPath;
    }

    /**
     * @param downloadUrlPath
     *            the downloadUrlPath to set
     */
    public void setDownloadUrlPath(String downloadUrlPath) {
        this.mDownloadUrlPath = downloadUrlPath;
    }
}
