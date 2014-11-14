package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.IBoxConfig;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.requestsbase.BoxDefaultRequestObject;
import com.box.restclientv2.requestsbase.DefaultBoxRequest;

/**
 * Request to delete a web link.
 */
public class DeleteWebLinkRequest extends DefaultBoxRequest {

    public static final String URI = "/web_links/%s";

    /**
     * Constructor.
     *
     * @param config
     *            config
     * @param parser
     *            json parser
     * @param webLinkId
     *            id of the web link
     * @param requestObject
     *            request object
     * @throws com.box.restclientv2.exceptions.BoxRestException
     *             exception
     */
    public DeleteWebLinkRequest(final IBoxConfig config, final IBoxJSONParser parser, final String webLinkId, BoxDefaultRequestObject requestObject)
        throws BoxRestException {
        super(config, parser, getUri(webLinkId), RestMethod.DELETE, requestObject);
        setExpectedResponseCode(HttpStatus.SC_NO_CONTENT);
    }

    /**
     * Get uri.
     * 
     * @param webLinkId
     *            id of the web link
     * @return uri
     */
    public static String getUri(final String webLinkId) {
        return String.format(URI, webLinkId);
    }
}
