package com.box.boxjavalibv2.requests;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.junit.Assert;
import org.junit.Test;

import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.dao.BoxSharedLinkAccess;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxJSONException;
import com.box.boxjavalibv2.requests.requestentities.BoxSharedLinkRequestEntity;
import com.box.boxjavalibv2.requests.requestobjects.BoxItemRequestObject;
import com.box.boxjavalibv2.testutils.TestUtils;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;

/**
 * Test CreateSharedLinkRequest.
 */
public class CreateSharedLinkRequestTest extends RequestTestBase {

    // CHECKSTYLE:OFF
    @Test
    public void testUri() {
        Assert.assertEquals("/folders/123", CreateSharedLinkRequest.getUri("123", BoxResourceType.FOLDER));
        Assert.assertEquals("/files/123", CreateSharedLinkRequest.getUri("123", BoxResourceType.FILE));
    }

    @Test
    public void testFileRequestWellFormed() throws BoxRestException, IllegalStateException, IOException, AuthFatalFailureException, BoxJSONException {
        testRequestIsWellFormed(BoxResourceType.FILE);
    }

    @Test
    public void testFolderRequestWellFormed() throws BoxRestException, IllegalStateException, IOException, AuthFatalFailureException, BoxJSONException {
        testRequestIsWellFormed(BoxResourceType.FOLDER);
    }

    private void testRequestIsWellFormed(final BoxResourceType type) throws BoxRestException, IllegalStateException, IOException, AuthFatalFailureException,
        BoxJSONException {
        String id = "testid123";
        String access = BoxSharedLinkAccess.COLLABORATORS;
        Date unsharedAt = new Date();

        BoxSharedLinkRequestEntity sEntity = new BoxSharedLinkRequestEntity(access);
        sEntity.setPermissions(true);
        sEntity.setUnshared_at(unsharedAt);
        BoxItemRequestObject obj = BoxItemRequestObject.createSharedLinkRequestObject(sEntity, TestUtils.getJsonParser());
        CreateSharedLinkRequest request = new CreateSharedLinkRequest(CONFIG, JSON_PARSER, id, obj, type);
        testRequestIsWellFormed(request, TestUtils.getConfig().getApiUrlAuthority(),
            TestUtils.getConfig().getApiUrlPath().concat(CreateSharedLinkRequest.getUri(id, type)), HttpStatus.SC_OK, RestMethod.PUT);
        HttpEntity entity = request.getRequestEntity();
        Assert.assertTrue(entity instanceof StringEntity);

        super.assertEqualStringEntity(obj.getJSONEntity(), entity);
    }
}
