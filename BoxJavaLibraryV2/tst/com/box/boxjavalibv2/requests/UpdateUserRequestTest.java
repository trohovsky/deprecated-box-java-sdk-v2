package com.box.boxjavalibv2.requests;

import java.io.IOException;
import java.util.LinkedHashMap;

import junit.framework.Assert;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxJSONException;
import com.box.boxjavalibv2.requests.requestentities.BoxUserRequestEntity;
import com.box.boxjavalibv2.requests.requestobjects.BoxEntityRequestObject;
import com.box.boxjavalibv2.testutils.TestUtils;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;

public class UpdateUserRequestTest extends RequestTestBase {

    @Test
    public void testUri() {
        Assert.assertEquals("/users/123", UpdateUserRequest.getUri("123"));
    }

    @Test
    public void testRequestIsWellFormed() throws BoxRestException, IllegalStateException, IOException, AuthFatalFailureException, BoxJSONException {
        boolean removeEnterprise = true;
        boolean notify = true;
        String userId = "tstuserid";
        String name = "testname";
        String role = "testrole";
        String language = "tetstlan";
        String title = "testtitle";
        String phone = "testphone";
        String address = "testaddress";
        String status = "teststatus";
        boolean sync = true;
        int space = 123;
        boolean seeManaged = true;
        boolean exemptLimit = true;
        boolean exemptLogin = true;
        String key1 = "testkey1";
        String value1 = "testvalue1";
        String key2 = "testkey2";
        String value2 = "testvalue2";
        LinkedHashMap<String, String> codes = new LinkedHashMap<String, String>();
        codes.put(key1, value1);
        codes.put(key2, value2);
        BoxUserRequestEntity entity = this.getUserRequestEntity(notify, name, role, language, sync, title, phone, address, space, codes, seeManaged,
            exemptLimit, exemptLogin);
        if (removeEnterprise) {
            entity.setEnterprise(null);
        }
        UpdateUserRequest request = new UpdateUserRequest(CONFIG, JSON_PARSER, userId, BoxEntityRequestObject.getRequestEntity(TestUtils.getJsonParser(),
            entity));
        testRequestIsWellFormed(request, TestUtils.getConfig().getApiUrlAuthority(),
            TestUtils.getConfig().getApiUrlPath().concat(UpdateUserRequest.getUri(userId)), HttpStatus.SC_OK, RestMethod.PUT);

        Assert.assertEquals(Boolean.toString(notify), request.getQueryParams().get("notify"));

        HttpEntity en = request.getRequestEntity();
        Assert.assertTrue(en instanceof StringEntity);

        assertEqualStringEntity(entity.getMap(), en);
    }

    private BoxUserRequestEntity getUserRequestEntity(boolean notify, String name, String role, String language, boolean sync, String title, String phone,
        String address, double space, LinkedHashMap<String, String> codes, boolean seeManaged, boolean exemptLimit, boolean exemptLogin) {
        BoxUserRequestEntity rentity = BoxUserRequestEntity.updateUserInfoRequestObject(notify);
        rentity.setRole(role);
        rentity.setLanguage(language);
        rentity.setSyncEnabled(sync);
        rentity.setJobTitle(title);
        rentity.setPhone(phone);
        rentity.setAddress(address);
        rentity.setSpaceAmount(space);
        rentity.setTrackingCodes(codes);
        rentity.setCanSeeManagedUsers(seeManaged);
        rentity.setExemptFromDeviceLimits(exemptLimit);
        rentity.setExemptFromLoginVerification(exemptLogin);
        return rentity;
    }
}
