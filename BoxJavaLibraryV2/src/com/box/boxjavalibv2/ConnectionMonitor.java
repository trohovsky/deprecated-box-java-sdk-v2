package com.box.boxjavalibv2;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

@SuppressWarnings("deprecation")
public class ConnectionMonitor {

    private static ClientConnectionManager connManager;
    private static int maxConnectionPerRoute = 50;
    private static int maxConnection = 1000;
    private static long timePeriodCleanUpIdleConnection = 300000;
    private static long idleTimeThreshold = 60000;

    public synchronized static ClientConnectionManager getConnectionManagerInstance() {
        if (connManager == null) {
            SchemeRegistry schemeReg = new SchemeRegistry();
            schemeReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
            connManager = new ThreadSafeClientConnManager(getHttpParams(), schemeReg);
            monitorConnection(connManager);
        }

        return connManager;
    }

    public static HttpParams getHttpParams() {
        HttpParams params = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(params, maxConnection);
        ConnManagerParams.setMaxConnectionsPerRoute(params, new ConnPerRoute() {

            @Override
            public int getMaxForRoute(HttpRoute httpRoute) {
                return maxConnectionPerRoute;
            }
        });
        return params;
    }

    private static void monitorConnection(ClientConnectionManager connManager) {
        final WeakReference<ClientConnectionManager> ref = new WeakReference<ClientConnectionManager>(connManager);
        connManager = null;
        Thread monitorThread = new Thread() {

            @Override
            public void run() {
                try {
                    while (true) {
                        synchronized (this) {
                            ClientConnectionManager connMan = ref.get();
                            if (connMan == null) {
                                return;
                            }

                            wait(timePeriodCleanUpIdleConnection);
                            // Close expired connections
                            connMan.closeExpiredConnections();
                            connMan.closeIdleConnections(idleTimeThreshold, TimeUnit.SECONDS);
                        }
                    }
                }
                catch (InterruptedException ex) {
                    // terminate
                }
            }
        };
        monitorThread.start();
    }

    public static void setMaxConnectionPerRoute(int maxConnectionPerRoute) {
        ConnectionMonitor.maxConnectionPerRoute = maxConnectionPerRoute;
    }

    public static void setMaxConnection(int maxConnection) {
        ConnectionMonitor.maxConnection = maxConnection;
    }

    /**
     * @param timePeriodCleanUpIdleConnection
     *            clean up idle connection every such period of time. in miliseconds.
     */
    public static void setTimePeriodCleanUpIdleConnection(long timePeriodCleanUpIdleConnection) {
        ConnectionMonitor.timePeriodCleanUpIdleConnection = timePeriodCleanUpIdleConnection;
    }

    /**
     * @param idleTimeThreshold
     *            an idle connection will be closed if idled above this threshold of time. in miliseconds.
     */
    public static void setIdleTimeThreshold(long idleTimeThreshold) {
        ConnectionMonitor.idleTimeThreshold = idleTimeThreshold;
    }
}
