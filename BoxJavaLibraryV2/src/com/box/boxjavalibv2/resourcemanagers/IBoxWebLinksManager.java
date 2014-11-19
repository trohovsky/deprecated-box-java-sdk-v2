package com.box.boxjavalibv2.resourcemanagers;

import com.box.boxjavalibv2.dao.BoxWebLink;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.requests.requestobjects.BoxItemCopyRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxSharedLinkRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxWebLinkRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.requestsbase.BoxDefaultRequestObject;

import java.io.UnsupportedEncodingException;

public interface IBoxWebLinksManager extends IBoxResourceManager {

    /**
     * Get web link given a web link id.
     *
     * @param webLinkId
     *            id of the web link
     * @param requestObject
     *            object that goes into request.
     * @return requested box web link
     * @throws com.box.restclientv2.exceptions.BoxRestException
     *             See {@link com.box.restclientv2.exceptions.BoxRestException} for more info.
     * @throws com.box.boxjavalibv2.exceptions.BoxServerException
     *             See {@link com.box.boxjavalibv2.exceptions.BoxServerException} for more info.
     * @throws com.box.boxjavalibv2.exceptions.AuthFatalFailureException
     *             See {@link com.box.boxjavalibv2.exceptions.AuthFatalFailureException} for more info.
     */
    public BoxWebLink getWebLink(String webLinkId, BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException;

    /**
     * Delete a web link.
     *
     * @param webLinkId
     *            id of the web link
     * @param requestObject
     *            object that goes into request.
     * @throws BoxRestException
     *             See {@link com.box.restclientv2.exceptions.BoxRestException} for more info.
     * @throws BoxServerException
     *             See {@link com.box.boxjavalibv2.exceptions.BoxServerException} for more info.
     * @throws AuthFatalFailureException
     *             See {@link com.box.boxjavalibv2.exceptions.AuthFatalFailureException} for more info.
     */
    public void deleteWebLink(String webLinkId, BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException;

    /**
     * Copy a web link.
     *
     * @param webLinkId
     *            id of the web link
     * @param requestObject
     *            request object
     * @return copied web link
     * @throws BoxRestException
     *             See {@link com.box.restclientv2.exceptions.BoxRestException} for more info.
     * @throws BoxServerException
     *             See {@link com.box.boxjavalibv2.exceptions.BoxServerException} for more info.
     * @throws AuthFatalFailureException
     *             See {@link com.box.boxjavalibv2.exceptions.AuthFatalFailureException} for more info.
     */
    public BoxWebLink copyWebLink(String webLinkId, BoxItemCopyRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException;

    /**
     * Update info for a web link.
     *
     * @param webLinkId
     *            id of the web link
     * @param requestObject
     *            request object
     * @return updated web link
     * @throws java.io.UnsupportedEncodingException
     *             exception
     * @throws BoxRestException
     *             See {@link com.box.restclientv2.exceptions.BoxRestException} for more info.
     * @throws BoxServerException
     *             See {@link com.box.boxjavalibv2.exceptions.BoxServerException} for more info.
     * @throws AuthFatalFailureException
     *             See {@link com.box.boxjavalibv2.exceptions.AuthFatalFailureException} for more info.
     */
    public BoxWebLink updateWebLinkInfo(String webLinkId, BoxWebLinkRequestObject requestObject) throws UnsupportedEncodingException, BoxRestException, BoxServerException,
            AuthFatalFailureException;

    /**
     * Create a shared link for a web link, given the id of the web link.
     *
     * @param webLinkId
     *            id of the web link
     * @param requestObject
     *            request object
     * @return the web link, with shared link related fields filled in.
     * @throws BoxRestException
     *             See {@link com.box.restclientv2.exceptions.BoxRestException} for more info.
     * @throws BoxServerException
     *             See {@link com.box.boxjavalibv2.exceptions.BoxServerException} for more info.
     * @throws AuthFatalFailureException
     *             See {@link com.box.boxjavalibv2.exceptions.AuthFatalFailureException} for more info.
     */
    public BoxWebLink createSharedLink(String webLinkId, BoxSharedLinkRequestObject requestObject) throws BoxRestException, BoxServerException,
            AuthFatalFailureException;
}


