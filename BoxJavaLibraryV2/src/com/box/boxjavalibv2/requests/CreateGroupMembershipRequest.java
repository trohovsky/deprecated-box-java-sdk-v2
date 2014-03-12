package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.IBoxConfig;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.boxjavalibv2.requests.requestentities.BoxGroupMembershipRequestEntity;
import com.box.boxjavalibv2.requests.requestobjects.BoxEntityRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.requests.DefaultBoxRequest;

public class CreateGroupMembershipRequest extends DefaultBoxRequest {

    private static final String URI = "/group_memberships";

    public CreateGroupMembershipRequest(final IBoxConfig config, final IBoxJSONParser parser,
        final BoxEntityRequestObject<BoxGroupMembershipRequestEntity> requestObject) throws BoxRestException {
        super(config, parser, getUri(), RestMethod.POST, requestObject);
        setExpectedResponseCode(HttpStatus.SC_CREATED);
    }

    public static String getUri() {
        return URI;
    }
}