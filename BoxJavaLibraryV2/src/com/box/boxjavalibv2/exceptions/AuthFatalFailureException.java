package com.box.boxjavalibv2.exceptions;

import com.box.restclientv2.exceptions.BoxSDKException;

/**
 * Exception indicating fatal error in authentication. May need to re-authenticate.
 */
public class AuthFatalFailureException extends BoxSDKException {

    private static final long serialVersionUID = 1L;
    private boolean callerResponsibleForFix;
    private String partialRefreshToken; // may be null

    /**
     * Constructor.
     */
    public AuthFatalFailureException() {
        super();
    }

    @Deprecated
    public AuthFatalFailureException(boolean callerResponsibleForFix) {
        this();
        this.callerResponsibleForFix = callerResponsibleForFix;
    }

    public AuthFatalFailureException(Exception e) {
        super(e);
    }

    public AuthFatalFailureException(Exception e, String refreshToken) {
        super(e);
        if (refreshToken != null) {
            int len = refreshToken.length();
            this.partialRefreshToken = "****" + refreshToken.substring(len-4);
        } else {
            this.partialRefreshToken = null;
        }
    }

    /**
     * Constructor.
     */
    public AuthFatalFailureException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (partialRefreshToken != null) {
            message += "; refresh_token=" + partialRefreshToken;
        }
        return message;
    }

    /**
     * @return whether the caller is responsible to fix this issue.
     */
    @Deprecated
    public boolean isCallerResponsibleForFix() {
        return callerResponsibleForFix;
    }
}
