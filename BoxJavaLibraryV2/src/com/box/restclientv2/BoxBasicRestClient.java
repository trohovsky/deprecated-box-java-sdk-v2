package com.box.restclientv2;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import com.box.boxjavalibv2.ConnectionMonitor;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.requests.IBoxRequest;
import com.box.restclientv2.responses.DefaultBoxResponse;
import com.box.restclientv2.responses.IBoxResponse;

/**
 * Basic implementation of the {@link IBoxRESTClient}.
 */
@SuppressWarnings("deprecation")
public class BoxBasicRestClient implements IBoxRESTClient {

    private final DefaultHttpClient mHttpClient;

    public HttpClient getRawHttpClient() {
        return mHttpClient;
    }

    /**
     * 
     * @param maxConnection
     * @param maxConnectionPerRoute
     * @param timePeriodCleanUpIdleConnection
     *            clean up idle connection every such period of time.
     * @param idleTimeThreshold
     *            time threshold, an idle connection will be closed if idled above this threshold of time.
     */
    public BoxBasicRestClient(final int maxConnection, final int maxConnectionPerRoute, final long timePeriodCleanUpIdleConnection, final long idleTimeThreshold) {
        ConnectionMonitor.setIdleTimeThreshold(idleTimeThreshold);
        ConnectionMonitor.setMaxConnection(maxConnection);
        ConnectionMonitor.setMaxConnectionPerRoute(maxConnectionPerRoute);
        ConnectionMonitor.setTimePeriodCleanUpIdleConnection(timePeriodCleanUpIdleConnection);
        ClientConnectionManager connectionManager = ConnectionMonitor.getConnectionManagerInstance();
        mHttpClient = new DefaultHttpClient(connectionManager, ConnectionMonitor.getHttpParams());
    }

    /**
     * Constructor.
     */
    public BoxBasicRestClient() {
        HttpParams params = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(params, 5000);
        ConnManagerParams.setMaxConnectionsPerRoute(params, new ConnPerRoute() {

            @Override
            public int getMaxForRoute(HttpRoute httpRoute) {
                return 100;
            }
        });
        SchemeRegistry schemeReg = new SchemeRegistry();
        schemeReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ThreadSafeClientConnManager connectionManager = new ThreadSafeClientConnManager(params, schemeReg);
        mHttpClient = new DefaultHttpClient(connectionManager, params);
    }

    @Override
    public IBoxResponse execute(final IBoxRequest boxRequest) throws BoxRestException, AuthFatalFailureException {
        HttpUriRequest httpRequest = boxRequest.prepareRequest();
        HttpResponse response;

        try {
            response = getRawHttpClient().execute(httpRequest);
        }
        catch (IOException e) {
            throw new BoxRestException(e);
        }
        DefaultBoxResponse boxResponse = new DefaultBoxResponse(response);
        boxResponse.setExpectedResponseCode(boxRequest.getExpectedResponseCode());
        return boxResponse;
    }

}
