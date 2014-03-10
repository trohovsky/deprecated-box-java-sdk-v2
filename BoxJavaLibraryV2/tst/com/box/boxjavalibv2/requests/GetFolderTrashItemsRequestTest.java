package com.box.boxjavalibv2.requests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.requests.requestobjects.BoxFolderRequestObject;
import com.box.boxjavalibv2.testutils.TestUtils;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;

public class GetFolderTrashItemsRequestTest extends RequestTestBase {

    @Test
    public void testUri() {
        Assert.assertEquals("/folders/123/trash/items", GetFolderTrashItemsRequest.getUri("123"));
    }

    @Test
    public void testRequestIsWellFormed() throws BoxRestException, AuthFatalFailureException {
        String id = "testid123";
        int limit = 5;
        int offset = 2;
        String fieldA = "fieldA";
        String fieldB = "fieldB";
        List<String> fields = new ArrayList<String>();
        fields.add(fieldA);
        fields.add(fieldB);

        GetFolderTrashItemsRequest request = new GetFolderTrashItemsRequest(CONFIG, JSON_PARSER, id, (BoxFolderRequestObject) BoxFolderRequestObject
            .getTrashItemsRequestObject(limit, offset).addFields(fields));
        testRequestIsWellFormed(request, TestUtils.getConfig().getApiUrlAuthority(),
            TestUtils.getConfig().getApiUrlPath().concat(GetFolderTrashItemsRequest.getUri(id)), HttpStatus.SC_OK, RestMethod.GET);

        Map<String, String> queries = request.getQueryParams();
        Assert.assertEquals(Integer.toString(limit), queries.get("limit"));
        Assert.assertEquals(Integer.toString(offset), queries.get("offset"));
        Assert.assertEquals(fieldA + "," + fieldB, queries.get("fields"));

    }
}
