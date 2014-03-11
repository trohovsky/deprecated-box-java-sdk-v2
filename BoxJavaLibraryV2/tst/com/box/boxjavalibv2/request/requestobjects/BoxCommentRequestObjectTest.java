package com.box.boxjavalibv2.request.requestobjects;

import junit.framework.Assert;

import org.junit.Test;

import com.box.boxjavalibv2.exceptions.BoxJSONException;
import com.box.boxjavalibv2.jsonparsing.BoxJSONParser;
import com.box.boxjavalibv2.jsonparsing.BoxResourceHub;
import com.box.boxjavalibv2.requests.requestobjects.BoxCommentRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;

public class BoxCommentRequestObjectTest {

    private static final String JSON_STR = "{\"message\":\"%s\"}";

    @Test
    public void testJSONHasAllFields() throws BoxRestException, BoxJSONException {
        String message = "testmessage123";
        BoxCommentRequestObject entity = BoxCommentRequestObject.updateCommentRequestObject(message, new BoxJSONParser(new BoxResourceHub()));

        Assert.assertEquals(String.format(JSON_STR, message), entity.getJSONEntity().toJSONString(new BoxJSONParser(new BoxResourceHub())));
    }

}
