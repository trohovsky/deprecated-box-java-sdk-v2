package com.box.boxjavalibv2.resourcemanagers;

import java.util.List;

import com.box.boxjavalibv2.dao.BoxCollaboration;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.requests.requestentities.BoxCollabRequestEntity;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxEntityRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxGetAllCollabsRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;

public interface IBoxCollaborationsManager extends IBoxResourceManager {

    /**
     * Get a collaboration.
     * 
     * @param collabId
     *            id of the collaboration
     * @param requestObject
     *            object that goes into request.
     * @return collaboration (Errors may occur if the IDs are invalid or if the user does not have permissions to see the collaboration.)
     * @throws BoxRestException
     *             See {@link com.box.restclientv2.exceptions.BoxRestException} for more info.
     * @throws BoxServerException
     *             See {@link com.box.restclientv2.exceptions.BoxServerException} for more info.
     * @throws AuthFatalFailureException
     *             See {@link com.box.restclientv2.exceptions.AuthFatalFailureException} for more info.
     */
    public BoxCollaboration getCollaboration(String collabId, BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException;

    /**
     * Add a collaboration for a single user to a folder.
     * 
     * @param folderId
     *            id of the folder
     * @param collabObject
     *            object that goes into request body.
     * @return collaboration
     * @throws BoxRestException
     *             See {@link com.box.restclientv2.exceptions.BoxRestException} for more info.
     * @throws BoxServerException
     *             See {@link com.box.restclientv2.exceptions.BoxServerException} for more info.
     * @throws AuthFatalFailureException
     *             See {@link com.box.restclientv2.exceptions.AuthFatalFailureException} for more info.
     */
    public BoxCollaboration createCollaboration(String folderId, final BoxEntityRequestObject<BoxCollabRequestEntity> collabObject) throws BoxRestException,
        BoxServerException, AuthFatalFailureException;

    /**
     * Get all collaborations. (Currently only support getting all pending collaborations.)
     * 
     * @param collabObject
     *            object that goes into request.
     * @return collaborations
     * @throws BoxRestException
     *             See {@link com.box.restclientv2.exceptions.BoxRestException} for more info.
     * @throws BoxServerException
     *             See {@link com.box.restclientv2.exceptions.BoxServerException} for more info.
     * @throws AuthFatalFailureException
     *             See {@link com.box.restclientv2.exceptions.AuthFatalFailureException} for more info.
     */
    public List<BoxCollaboration> getAllCollaborations(BoxGetAllCollabsRequestObject collabObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException;

    /**
     * Delete a collaboration.
     * 
     * @param collabId
     *            id of the collaboration
     * @param collabObject
     *            object that goes into request.
     * @throws BoxRestException
     *             See {@link com.box.restclientv2.exceptions.BoxRestException} for more info.
     * @throws BoxServerException
     *             See {@link com.box.restclientv2.exceptions.BoxServerException} for more info.
     * @throws AuthFatalFailureException
     *             See {@link com.box.restclientv2.exceptions.AuthFatalFailureException} for more info.
     */
    public void deleteCollaboration(String collabId, BoxDefaultRequestObject requestObject) throws BoxServerException, BoxRestException,
        AuthFatalFailureException;

    /**
     * Update a collaboration.
     * 
     * @param collabId
     *            id of the collaboration
     * @param requestObject
     *            request object. Note the you can set the status in this object to 'accepted' or 'rejected' if you are the 'accessible_by' user and the current
     *            status is 'pending'
     * @return updated BoxCollaboration
     * @throws BoxRestException
     *             See {@link com.box.restclientv2.exceptions.BoxRestException} for more info.
     * @throws BoxServerException
     *             See {@link com.box.restclientv2.exceptions.BoxServerException} for more info.
     * @throws AuthFatalFailureException
     *             See {@link com.box.restclientv2.exceptions.AuthFatalFailureException} for more info.
     */
    public BoxCollaboration updateCollaboration(String collabId, BoxEntityRequestObject<BoxCollabRequestEntity> requestObject) throws BoxRestException,
        AuthFatalFailureException, BoxServerException;
}