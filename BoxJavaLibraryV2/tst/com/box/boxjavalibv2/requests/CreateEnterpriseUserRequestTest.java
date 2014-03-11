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
import com.box.boxjavalibv2.requests.requestobjects.BoxUserRequestObject;
import com.box.boxjavalibv2.testutils.TestUtils;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;

public class CreateEnterpriseUserRequestTest extends RequestTestBase {

    @Test
    public void testUri() {
        Assert.assertEquals("/users", CreateEnterpriseUserRequest.getUri());
    }

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

        CreateEnterpriseUserRequest request = new CreateEnterpriseUserRequest(CONFIG, JSON_PARSER, BoxUserRequestObject
            .createEnterpriseUserRequestObject(login, name, TestUtils.getJsonParser()).setRole(role).setLanguage(language).setSyncEnabled(sync)
            .setJobTitle(title).setPhone(phone).setAddress(address).setSpaceAmount(space).setTrackingCodes(codes).setCanSeeManagedUsers(seeManaged)
            .setStatus(status).setExemptFromDeviceLimits(exemptLimit).setExemptFromLoginVerification(exemptLogin));
        testRequestIsWellFormed(request, TestUtils.getConfig().getApiUrlAuthority(),
            TestUtils.getConfig().getApiUrlPath().concat(CreateEnterpriseUserRequest.getUri()), HttpStatus.SC_CREATED, RestMethod.POST);

        HttpEntity entity = request.getRequestEntity();
        Assert.assertTrue(entity instanceof StringEntity);

        BoxUserRequestObject userEntity = BoxUserRequestObject.createEnterpriseUserRequestObject(login, name, TestUtils.getJsonParser()).setRole(role)
            .setLanguage(language).setSyncEnabled(sync).setJobTitle(title).setPhone(phone).setAddress(address).setSpaceAmount(space).setTrackingCodes(codes)
            .setCanSeeManagedUsers(seeManaged).setStatus(status).setExemptFromDeviceLimits(exemptLimit).setExemptFromLoginVerification(exemptLogin);
        assertEqualStringEntity(userEntity.getJSONEntity(), entity);
    }
}
