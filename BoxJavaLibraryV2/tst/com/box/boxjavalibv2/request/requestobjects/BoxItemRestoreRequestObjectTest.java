package com.box.boxjavalibv2.request.requestobjects;

import junit.framework.Assert;

import org.junit.Test;

import com.box.boxjavalibv2.exceptions.BoxJSONException;
import com.box.boxjavalibv2.jsonparsing.BoxJSONParser;
import com.box.boxjavalibv2.jsonparsing.BoxResourceHub;
import com.box.boxjavalibv2.requests.requestobjects.BoxItemRestoreRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;

public class BoxItemRestoreRequestObjectTest {

    private static final String NAME = "\"name\":\"%s\"";
    private static final String PARENT = "\"parent\":{\"id\":\"%s\"}";

    @Test
    public void testDefaultObjectHasNoField() {
        BoxItemRestoreRequestObject obj = BoxItemRestoreRequestObject.restoreItemRequestObject(new BoxJSONParser(new BoxResourceHub()));
        Assert.assertTrue(obj.getJSONEntity().isEmpty());
    }

    @Test
    public void testNameInObject() throws BoxRestException, BoxJSONException {
        String name = "testname";
        BoxItemRestoreRequestObject obj = BoxItemRestoreRequestObject.restoreItemRequestObject(new BoxJSONParser(new BoxResourceHub())).setNewName(name);
        Assert.assertEquals("{" + String.format(NAME, name) + "}", obj.getJSONEntity().toJSONString(new BoxJSONParser(new BoxResourceHub())));
    }

    @Test
    public void testParentInObject() throws BoxRestException, BoxJSONException {
        String parentid = "testid";
        BoxItemRestoreRequestObject obj = BoxItemRestoreRequestObject.restoreItemRequestObject(new BoxJSONParser(new BoxResourceHub())).setNewParent(parentid);
        Assert.assertEquals("{" + String.format(PARENT, parentid) + "}", obj.getJSONEntity().toJSONString(new BoxJSONParser(new BoxResourceHub())));
    }

    @Test
    public void testBothParentAndNameInObject() throws BoxRestException, BoxJSONException {
        String name = "testname";
        String parentid = "testid";
        BoxItemRestoreRequestObject obj = BoxItemRestoreRequestObject.restoreItemRequestObject(new BoxJSONParser(new BoxResourceHub())).setNewName(name)
            .setNewParent(parentid);
        String json = obj.getJSONEntity().toJSONString(new BoxJSONParser(new BoxResourceHub()));
        Assert.assertTrue(json.contains(String.format(NAME, name)));
        Assert.assertTrue(json.contains(String.format(PARENT, parentid)));
    }
}
