package com.box.boxjavalibv2.resourcemanagers;

import com.box.boxjavalibv2.dao.BoxCollection;
import com.box.boxjavalibv2.dao.BoxGroup;
import com.box.boxjavalibv2.dao.BoxGroupMembership;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.requests.CreateGroupMembershipRequest;
import com.box.boxjavalibv2.requests.CreateGroupRequest;
import com.box.boxjavalibv2.requests.DeleteGroupMembershipRequest;
import com.box.boxjavalibv2.requests.DeleteGroupRequest;
import com.box.boxjavalibv2.requests.GetAllGroupsRequest;
import com.box.boxjavalibv2.requests.GetGroupCollaborationsRequest;
import com.box.boxjavalibv2.requests.GetGroupMembershipRequest;
import com.box.boxjavalibv2.requests.GetGroupMembershipsRequest;
import com.box.boxjavalibv2.requests.UpdateGroupMembershipRequest;
import com.box.boxjavalibv2.requests.UpdateGroupRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxGroupRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;

public class BoxGroupsManagerImpl extends AbstractBoxResourceManager implements IBoxGroupsManager {

    public BoxGroupsManagerImpl(IBoxConfig config, IBoxResourceHub resourceHub, IBoxJSONParser parser, IBoxRequestAuth auth, IBoxRESTClient restClient) {
        super(config, resourceHub, parser, auth, restClient);
    }

    @Override
    public BoxCollection getAllGroups(BoxDefaultRequestObject requestObject) throws BoxRestException, AuthFatalFailureException, BoxServerException {
        GetAllGroupsRequest request = new GetAllGroupsRequest(getConfig(), getJSONParser(), requestObject);
        return (BoxCollection) getResponseAndParseAndTryCast(request, BoxResourceType.GROUPS, getJSONParser());
    }

    @Override
    public BoxGroup createGroup(BoxGroupRequestObject requestObject) throws BoxRestException, AuthFatalFailureException, BoxServerException {
        CreateGroupRequest request = new CreateGroupRequest(getConfig(), getJSONParser(), requestObject);
        return (BoxGroup) getResponseAndParseAndTryCast(request, BoxResourceType.GROUP, getJSONParser());
    }

    @Override
    public BoxGroup createGroup(String name) throws BoxRestException, AuthFatalFailureException, BoxServerException {
        BoxGroupRequestObject requestObj = BoxGroupRequestObject.createGroupRequestObject(name);
        return createGroup(requestObj);
    }

    @Override
    public BoxGroup updateGroup(String groupId, BoxGroupRequestObject requestObject) throws BoxRestException, AuthFatalFailureException, BoxServerException {
        UpdateGroupRequest request = new UpdateGroupRequest(getConfig(), getJSONParser(), groupId, requestObject);
        return (BoxGroup) getResponseAndParseAndTryCast(request, BoxResourceType.GROUP, getJSONParser());
    }

    @Override
    public void deleteGroup(String groupId, BoxGroupRequestObject requestObject) throws BoxRestException, AuthFatalFailureException, BoxServerException {
        DeleteGroupRequest request = new DeleteGroupRequest(getConfig(), getJSONParser(), groupId, requestObject);
        this.executeRequestWithNoResponseBody(request);
    }

    @Override
    public BoxCollection getMemberships(String groupId, BoxDefaultRequestObject requestObject) throws BoxRestException, AuthFatalFailureException,
        BoxServerException {
        GetGroupMembershipsRequest request = new GetGroupMembershipsRequest(getConfig(), getJSONParser(), groupId, requestObject);
        return (BoxCollection) getResponseAndParseAndTryCast(request, BoxResourceType.GROUP_MEMBERSHIPS, getJSONParser());
    }

    @Override
    public BoxGroupMembership getMembership(String membershipId, BoxDefaultRequestObject requestObject) throws BoxRestException, AuthFatalFailureException,
        BoxServerException {
        GetGroupMembershipRequest request = new GetGroupMembershipRequest(getConfig(), getJSONParser(), membershipId, requestObject);
        return (BoxGroupMembership) getResponseAndParseAndTryCast(request, BoxResourceType.GROUP_MEMBERSHIP, getJSONParser());
    }

    @Override
    public BoxGroupMembership createMembership(BoxGroupRequestObject requestObject) throws BoxRestException, AuthFatalFailureException, BoxServerException {
        CreateGroupMembershipRequest request = new CreateGroupMembershipRequest(getConfig(), getJSONParser(), requestObject);
        return (BoxGroupMembership) getResponseAndParseAndTryCast(request, BoxResourceType.GROUP_MEMBERSHIP, getJSONParser());
    }

    @Override
    public BoxGroupMembership createMembership(String groupId, String userId, String role) throws BoxRestException, AuthFatalFailureException,
        BoxServerException {
        BoxGroupRequestObject obj = BoxGroupRequestObject.addMembershipRequest(groupId, userId, role);
        return createMembership(obj);
    }

    @Override
    public BoxGroupMembership updateMembership(String membershipId, BoxGroupRequestObject requestObject) throws BoxRestException, AuthFatalFailureException,
        BoxServerException {
        UpdateGroupMembershipRequest request = new UpdateGroupMembershipRequest(getConfig(), getJSONParser(), membershipId, requestObject);
        return (BoxGroupMembership) getResponseAndParseAndTryCast(request, BoxResourceType.GROUP_MEMBERSHIP, getJSONParser());
    }

    @Override
    public BoxGroupMembership updateMembership(String membershipId, String role) throws BoxRestException, AuthFatalFailureException, BoxServerException {
        BoxGroupRequestObject obj = BoxGroupRequestObject.updateMembershipRequest(role);
        return updateMembership(membershipId, obj);
    }

    @Override
    public void deleteMembership(String membershipId, BoxGroupRequestObject requestObject) throws BoxRestException, AuthFatalFailureException,
        BoxServerException {
        DeleteGroupMembershipRequest request = new DeleteGroupMembershipRequest(getConfig(), getJSONParser(), membershipId, requestObject);
        this.executeRequestWithNoResponseBody(request);
    }

    @Override
    public BoxCollection getAllCollaborations(String groupId, BoxDefaultRequestObject requestObject) throws BoxRestException, AuthFatalFailureException,
        BoxServerException {
        GetGroupCollaborationsRequest request = new GetGroupCollaborationsRequest(getConfig(), getJSONParser(), groupId, requestObject);
        return (BoxCollection) getResponseAndParseAndTryCast(request, BoxResourceType.COLLABORATIONS, getJSONParser());
    }
}
