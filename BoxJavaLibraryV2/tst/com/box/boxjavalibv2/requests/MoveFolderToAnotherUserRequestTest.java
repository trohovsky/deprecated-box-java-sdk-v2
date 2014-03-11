package com.box.boxjavalibv2.requests;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxJSONException;
import com.box.boxjavalibv2.requests.requestobjects.BoxUserRequestObject;
import com.box.boxjavalibv2.testutils.TestUtils;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;

public class MoveFolderToAnotherUserRequestTest extends RequestTestBase {

    @Test
    public void testUri() {
        Assert.assertEquals("/users/123/folders/456", MoveFolderToAnotherUserRequest.getUri("123", "456"));
    }

    @Test
    public void testRequestIsWellFormed() throws BoxRestException, IllegalStateException, IOException, AuthFatalFailureException, BoxJSONException {
        String userId = "testuserid";
        String folderId = "testfolderid";
        boolean notify = true;
        MoveFolderToAnotherUserRequest request = new MoveFolderToAnotherUserRequest(CONFIG, JSON_PARSER, userId, folderId,
            BoxUserRequestObject.moveFolderToAnotherUserRequestObject(folderId, notify, TestUtils.getJsonParser()));
        testRequestIsWellFormed(request, TestUtils.getConfig().getApiUrlAuthority(),
            TestUtils.getConfig().getApiUrlPath().concat(MoveFolderToAnotherUserRequest.getUri(userId, folderId)), HttpStatus.SC_OK, RestMethod.PUT);
        Assert.assertEquals(Boolean.toString(notify), request.getQueryParams().get("notify"));

        HttpEntity entity = request.getRequestEntity();
        Assert.assertTrue(entity instanceof StringEntity);
        assertEqualStringEntity(BoxUserRequestObject.moveFolderToAnotherUserRequestObject(folderId, notify, TestUtils.getJsonParser()).getJSONEntity(), entity);
    }
}
