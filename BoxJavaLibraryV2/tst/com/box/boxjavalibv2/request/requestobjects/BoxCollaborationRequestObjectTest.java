package com.box.boxjavalibv2.request.requestobjects;

import junit.framework.Assert;

import org.junit.Test;

import com.box.boxjavalibv2.exceptions.BoxJSONException;
import com.box.boxjavalibv2.jsonparsing.BoxJSONParser;
import com.box.boxjavalibv2.jsonparsing.BoxResourceHub;
import com.box.boxjavalibv2.requests.requestobjects.BoxCollabRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;

public class BoxCollaborationRequestObjectTest {

    private static final String ITEM_STR = "\"item\":";
    private static final String ACCESSIBLE_STR = "\"accessible_by\":";
    private static final String ROLE_STR = "\"role\":\"%s\"";

    @Test
    public void testJSONHasAllFields() throws BoxRestException, BoxJSONException {
        String folderId = "testfolderid123";
        String userId = "testuserid456";
        String login = "abc@box.com";
        String role = "testrole789";

        BoxCollabRequestObject entity = BoxCollabRequestObject
            .createCollaborationObject(folderId, userId, login, role, new BoxJSONParser(new BoxResourceHub()));
        String jsonStr = entity.getJSONEntity().toJSONString(new BoxJSONParser(new BoxResourceHub()));
        Assert.assertTrue(jsonStr.contains(ITEM_STR));
        Assert.assertTrue(jsonStr.contains(ACCESSIBLE_STR));
        Assert.assertTrue(jsonStr.contains(String.format(ROLE_STR, role)));
    }
}
