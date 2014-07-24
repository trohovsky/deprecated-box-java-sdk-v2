package com.box.boxjavalibv2.javafxoauth.sample;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javafx.embed.swing.JFXPanel;

import com.box.boxjavalibv2.authorization.IAuthEvent;
import com.box.boxjavalibv2.authorization.IAuthFlowListener;
import com.box.boxjavalibv2.authorization.IAuthFlowMessage;
import com.box.boxjavalibv2.authorization.OAuthDataMessage;
import com.box.boxjavalibv2.events.OAuthEvent;
import com.box.boxjavalibv2.javafxoauth.JavaFxOAuthFlow;

/**
 * A sample app using the javafx OAuth UI. This app itself is on java swing.
 */
public final class SampleOAuthCaller {

    /**
     * Use your own client ID here.
     */
    private static final String CLIENT_ID = "";

    /**
     * Use your own client secret here.
     */
    private static final String CLIENT_SECRET = "";

    /**
     * Private constructor to prevent instantiation.
     */
    private SampleOAuthCaller() {

    }

    /**
     * Main entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final JFrame f = new JFrame();
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        final JFXPanel panel = new JFXPanel();
        f.add(panel);
        f.pack();
        f.setVisible(true);

        JavaFxOAuthFlow oauthflow = new JavaFxOAuthFlow(screenSize.width, screenSize.height, screenSize.width,
            screenSize.height, createAuthFlowListener(f));
        oauthflow.initAuthAndRun(panel, CLIENT_ID, CLIENT_SECRET);
    }

    /**
     * Creates an {@link IAuthFlowListener} that will show a dialog after authentication.
     *
     * @param  parentUIComponent the component that contains the {@link JavaFxOAuthFlow}
     * @return                   the {@link IAuthFlowListener}
     */
    private static IAuthFlowListener createAuthFlowListener(final Component parentUIComponent) {
        return new IAuthFlowListener() {

            @Override
            public void onAuthFlowMessage(IAuthFlowMessage message) {
            }

            @Override
            public void onAuthFlowException(Exception e) {
            }

            @Override
            public void onAuthFlowEvent(IAuthEvent state, IAuthFlowMessage message) {
                if (state == OAuthEvent.OAUTH_CREATED) {
                    OAuthDataMessage msg = (OAuthDataMessage) message;
                    final String display = "tokens: " + msg.getData().getAccessToken() + ","
                        + msg.getData().getRefreshToken();
                    // Note this event is fired on javafx thread, if your app is like this sample trying to do something
                    // on java swing thread on receiving the message, you need to use SwingUtilities.invokeLater.
                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            parentUIComponent.setVisible(false);
                            JOptionPane.showMessageDialog(parentUIComponent, display);
                        }
                    });
                }
            }
        };
    }
}
