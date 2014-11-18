package com.box.boxjavalibv2.requests;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.testutils.TestUtils;
import com.box.boxjavalibv2.utils.Constants;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.requestsbase.BoxDefaultRequestObject;

public class DeleteWebLinkRequestTest extends RequestTestBase {

    @Test
    public void testUri() {
        Assert.assertEquals("/web_links/123", DeleteWebLinkRequest.getUri("123"));
    }

    @Test
    public void testRequestIsWellFormed() throws BoxRestException, AuthFatalFailureException {
        String id = "testid123";
        String etag = "testetag1456";
        BoxDefaultRequestObject obj = new BoxDefaultRequestObject();
        obj.getRequestExtras().setIfMatch(etag);
        DeleteWebLinkRequest request = new DeleteWebLinkRequest(CONFIG, JSON_PARSER, id, obj);
        testRequestIsWellFormed(request, TestUtils.getConfig().getApiUrlAuthority(),
            TestUtils.getConfig().getApiUrlPath().concat(DeleteWebLinkRequest.getUri(id)), HttpStatus.SC_NO_CONTENT, RestMethod.DELETE);

        Header header = request.getRawRequest().getFirstHeader(Constants.IF_MATCH);
        Assert.assertNotNull(header);
        Assert.assertEquals(etag, header.getValue());

    }

}
