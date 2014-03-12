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

public class CreateEnterpriseUserRequestTest extends RequestTestBase {

    @Test
    public void testUri() {
        Assert.assertEquals("/users", CreateEnterpriseUserRequest.getUri());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testRequestIsWellFormed() throws BoxRestException, IllegalStateException, IOException, AuthFatalFailureException, BoxJSONException {
        String name = "testname";
        String login = "testlogin";
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

        BoxUserRequestEntity reqEntity = getUserRequestEntity(login, name, role, language, sync, title, phone, address, space, codes, seeManaged, status,
            exemptLimit, exemptLogin);
        CreateEnterpriseUserRequest request = new CreateEnterpriseUserRequest(CONFIG, JSON_PARSER, BoxEntityRequestObject.getRequestEntity(
            TestUtils.getJsonParser(), reqEntity));
        testRequestIsWellFormed(request, TestUtils.getConfig().getApiUrlAuthority(),
            TestUtils.getConfig().getApiUrlPath().concat(CreateEnterpriseUserRequest.getUri()), HttpStatus.SC_CREATED, RestMethod.POST);

        HttpEntity entity = request.getRequestEntity();
        Assert.assertTrue(entity instanceof StringEntity);
        BoxUserRequestEntity rentity = getUserRequestEntity(login, name, role, language, sync, title, phone, address, space, codes, seeManaged, status,
            exemptLimit, exemptLogin);

        assertEqualStringEntity(reqEntity.getMap(), entity);
    }

    private BoxUserRequestEntity getUserRequestEntity(String login, String name, String role, String language, boolean sync, String title, String phone,
        String address, double space, LinkedHashMap<String, String> codes, boolean seeManaged, String status, boolean exemptLimit, boolean exemptLogin) {
        BoxUserRequestEntity rentity = BoxUserRequestEntity.createEnterpriseUserRequestObject(login, name);
        rentity.setRole(role);
        rentity.setLanguage(language);
        rentity.setSyncEnabled(sync);
        rentity.setJobTitle(title);
        rentity.setPhone(phone);
        rentity.setAddress(address);
        rentity.setSpaceAmount(space);
        rentity.setTrackingCodes(codes);
        rentity.setCanSeeManagedUsers(seeManaged);
        rentity.setStatus(status);
        rentity.setExemptFromDeviceLimits(exemptLimit);
        rentity.setExemptFromLoginVerification(exemptLogin);
        return rentity;
    }
}
