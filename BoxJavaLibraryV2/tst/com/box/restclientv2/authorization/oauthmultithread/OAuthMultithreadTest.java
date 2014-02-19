package com.box.restclientv2.authorization.oauthmultithread;

import java.util.Random;

import junit.framework.Assert;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.box.boxjavalibv2.authorization.OAuthAuthorization;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.responses.DefaultBoxResponse;

public class OAuthMultithreadTest {

    private MockBoxClient client;

    @Test
    public void testMultipleThreadUsingOAuth() throws BoxRestException, BoxServerException, AuthFatalFailureException {
        // System.out.println("Main: Init access token, refresh should succeed");
        MockOAuthManager.refreshShouldFail = false;
        MockRestClient.accessToken = "testa";
        createOAuthDataController(true);

        int numThreads = 1;
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = aThreadMakesCallsRandomly();
        }

        for (int i = 0; i < numThreads; i++) {
            threads[i].start();
        }

        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
        }
        // System.out.println("Main: change access token, refresh should succeed");
        MockRestClient.accessToken = "testb";

        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
        }

        // System.out.println("Main: change access token, refresh should fail");
        MockOAuthManager.refreshShouldFail = true;
        MockRestClient.accessToken = "testc";
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
        }
        for (int i = 0; i < numThreads; i++) {
            threads[i].interrupt();
        }

    }

    private Thread aThreadMakesCallsRandomly() {
        return new Thread() {

            @Override
            public void run() {
                Random random = new Random();
                while (true) {
                    try {
                        int statusCode = makeRequestAndGetStatusCode();
                        // System.out.println("Thread: status code:" + statusCode);
                        Assert.assertEquals(HttpStatus.SC_OK, statusCode);
                    }
                    catch (Exception e) {
                        // System.out.println("Thread: fail:" + e.getClass().getCanonicalName());
                        Assert.assertEquals(true, MockOAuthManager.refreshShouldFail);
                    }

                    try {
                        Thread.sleep(random.nextInt(50));
                    }
                    catch (InterruptedException e) {
                    }
                }

            }
        };
    }

    private int makeRequestAndGetStatusCode() throws BoxRestException, AuthFatalFailureException {
        MockRequest request = new MockRequest();
        request.setAuth(new OAuthAuthorization(client.getOAuthDataController()));
        MockRestClient client = new MockRestClient();
        DefaultBoxResponse response = (DefaultBoxResponse) client.execute(request);
        return response.getResponseStatusCode();
    }

    private void createOAuthDataController(boolean autoRefresh) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        client = new MockBoxClient();
        client.getOAuthDataController().setOAuthData(client.getOAuthManager().createOAuth("", "", "", ""));
    }
}
