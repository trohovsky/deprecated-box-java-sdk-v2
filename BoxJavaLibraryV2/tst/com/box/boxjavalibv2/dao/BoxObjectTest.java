package com.box.boxjavalibv2.dao;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.box.boxjavalibv2.testutils.TestUtils;
import com.box.restclientv2.exceptions.BoxRestException;

public class BoxObjectTest {

    /**
     * Test when parsing box object, if type cannot be recognized, we fall back to the specified base object.
     */
    @Test
    public void testFallBack() throws IOException, BoxRestException {
        String json = FileUtils.readFileToString(new File("testdata/wrongtype.json"));
        BoxTypedObject obj = (BoxTypedObject) TestUtils.getFromJSON(json, BoxTypedObject.class);
        Assert.assertEquals(BoxTypedObject.class, obj.getClass());

        BoxItem obj1 = (BoxItem) TestUtils.getFromJSON(json, BoxItem.class);
        Assert.assertEquals(BoxItem.class, obj1.getClass());

        // Make sure can also parse for string without type.
        json = json.replace("\"type\":\"notype\",", "");
        obj1 = (BoxItem) TestUtils.getFromJSON(json, BoxItem.class);
        Assert.assertEquals(BoxItem.class, obj1.getClass());
    }

    /**
     * Verify that unknown types in json are handled according to canBeHandledAsUnknown() method in BoxObject.
     */
    public void testHandleUnknownTypes() throws IOException, BoxRestException {
        String json = FileUtils.readFileToString(new File("testdata/filewithunknowntype.json"));
        BoxFile obj = (BoxFile) TestUtils.getFromJSON(json, BoxFile.class);
        Assert.assertEquals(BoxTypedObject.class, obj.getClass());

        String unknownStr = (String) obj.getExtraData("unknownstring");
        Assert.assertEquals("unknown string", unknownStr);

        int[] unknownIntArray = (int[]) obj.getExtraData("intarray");
        Assert.assertEquals(2, unknownIntArray.length);
        Assert.assertEquals(1, unknownIntArray[0]);
        Assert.assertEquals(2, unknownIntArray[1]);

        String[] unknownStrArray = (String[]) obj.getExtraData("stringarray");
        Assert.assertEquals(2, unknownStrArray.length);
        Assert.assertEquals("string1", unknownStrArray[0]);
        Assert.assertEquals("string2", unknownStrArray[1]);

        Assert.assertFalse(obj.contains("unparsabletype"));
    }
}
