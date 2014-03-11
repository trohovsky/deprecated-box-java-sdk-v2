package com.box.boxjavalibv2.requests;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxJSONException;
import com.box.boxjavalibv2.requests.requestobjects.BoxFolderRequestObject;
import com.box.boxjavalibv2.testutils.TestUtils;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;

public class CreateNewFolderRequestTest extends RequestTestBase {

    @Test
    public void uriTest() {
        Assert.assertEquals("/folders", CreateNewFolderRequest.getUri());
    }

    @Test
    public void testRequestIsWellFormed() throws BoxRestException, IllegalStateException, IOException, AuthFatalFailureException, BoxJSONException {
        String name = "foldername123";
        String parentId = "parentid456";

        CreateNewFolderRequest request = new CreateNewFolderRequest(CONFIG, JSON_PARSER, BoxFolderRequestObject.createFolderRequestObject(name, parentId,
            TestUtils.getJsonParser()));
        testRequestIsWellFormed(request, TestUtils.getConfig().getApiUrlAuthority(),
            TestUtils.getConfig().getApiUrlPath().concat(CreateNewFolderRequest.getUri()), HttpStatus.SC_CREATED, RestMethod.POST);
        HttpEntity entity = request.getRequestEntity();
        Assert.assertTrue(entity instanceof StringEntity);
        assertEqualStringEntity(BoxFolderRequestObject.createFolderRequestObject(name, parentId, TestUtils.getJsonParser()).getJSONEntity(), entity);
    }
}
