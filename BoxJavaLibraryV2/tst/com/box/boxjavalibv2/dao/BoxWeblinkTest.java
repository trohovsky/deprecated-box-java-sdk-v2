package com.box.boxjavalibv2.dao;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.box.boxjavalibv2.testutils.TestUtils;
import com.box.restclientv2.exceptions.BoxRestException;

public class BoxWeblinkTest {

    @Test
    public void testParcelRoundTrip() throws BoxRestException, IOException {
        String json = FileUtils.readFileToString(new File("testdata/weblink.json"));
        BoxWebLink webLink = (BoxWebLink) TestUtils.getFromJSON(json, BoxWebLink.class);

        TestParcel parcel = new TestParcel();
        webLink.writeToParcel(parcel, 0);
        BoxWebLink fromParcel = new BoxWebLink(parcel);

        Assert.assertEquals("web_link", fromParcel.getType());
        Assert.assertEquals("2234234", fromParcel.getId());
        Assert.assertEquals("1", fromParcel.getEtag());
        Assert.assertEquals("1", fromParcel.getSequenceId());
        Assert.assertEquals("http://box.com", fromParcel.getUrl());
        Assert.assertEquals("2012-12-12T10:53:43-08:00", fromParcel.getCreatedAt());
        Assert.assertEquals("2012-12-12T11:15:04-08:00", fromParcel.getModifiedAt());
        Assert.assertEquals("Web Link Name", fromParcel.getName());
        Assert.assertEquals("description", fromParcel.getDescription());

        Assert.assertEquals(1, fromParcel.getPathCollection().getTotalCount().intValue());
        Assert.assertEquals("folder",fromParcel.getPathCollection().getEntries().get(0).getType());
        Assert.assertEquals("0",fromParcel.getPathCollection().getEntries().get(0).getId());
        Assert.assertEquals(null,((BoxItem)fromParcel.getPathCollection().getEntries().get(0)).getSequenceId());
        Assert.assertEquals(null,((BoxItem) fromParcel.getPathCollection().getEntries().get(0)).getEtag());
        Assert.assertEquals("All Files", ((BoxItem) fromParcel.getPathCollection().getEntries().get(0)).getName());

        Assert.assertEquals("user",fromParcel.getCreatedBy().getType());
        Assert.assertEquals("17738362",fromParcel.getCreatedBy().getId());
        Assert.assertEquals("sean rose",fromParcel.getCreatedBy().getName());
        Assert.assertEquals("sean@box.com",fromParcel.getCreatedBy().getLogin());

        Assert.assertEquals("user",fromParcel.getModifiedBy().getType());
        Assert.assertEquals("17738362",fromParcel.getModifiedBy().getId());
        Assert.assertEquals("sean rose",fromParcel.getModifiedBy().getName());
        Assert.assertEquals("sean@box.com",fromParcel.getModifiedBy().getLogin());

        Assert.assertEquals("user",fromParcel.getOwnedBy().getType());
        Assert.assertEquals("17738362",fromParcel.getOwnedBy().getId());
        Assert.assertEquals("sean rose",fromParcel.getOwnedBy().getName());
        Assert.assertEquals("sean@box.com",fromParcel.getOwnedBy().getLogin());

        Assert.assertEquals("https://www.box.com/s/b20iy955qokqovjkzkdh",fromParcel.getSharedLink().getUrl());
        Assert.assertEquals(null,fromParcel.getSharedLink().getDownloadUrl());
        Assert.assertEquals(null,fromParcel.getSharedLink().getVanityUrl());
        Assert.assertEquals(false,fromParcel.getSharedLink().isPasswordEnabled());
        Assert.assertEquals(null,fromParcel.getSharedLink().getUnsharedAt());
        Assert.assertEquals(0,fromParcel.getSharedLink().getDownloadCount().intValue());
        Assert.assertEquals(0,fromParcel.getSharedLink().getPreviewCount().intValue());
        Assert.assertEquals("open",fromParcel.getSharedLink().getAccess());
        Assert.assertEquals(true,fromParcel.getSharedLink().getPermissions().isCan_download());
        Assert.assertEquals(true,fromParcel.getSharedLink().getPermissions().canPreview());

        Assert.assertEquals("folder", fromParcel.getParent().getType());
        Assert.assertEquals("0", fromParcel.getParent().getId());
        Assert.assertEquals(null, fromParcel.getParent().getSequenceId());
        Assert.assertEquals(null, fromParcel.getParent().getEtag());
        Assert.assertEquals("All Files", fromParcel.getParent().getName());

        Assert.assertEquals("active", fromParcel.getItemStatus());
    }

    @Test
    public void makeSureWebLinkSizeIsNotNullButZero() throws IOException, BoxRestException {
        String json = FileUtils.readFileToString(new File("testdata/weblink.json"));
        BoxWebLink webLink = (BoxWebLink) TestUtils.getFromJSON(json, BoxWebLink.class);
        Assert.assertEquals(0d, webLink.getSize(), 0.001);
    }
}
