BoxJavaFxOAuth
==============

The [Box API uses OAuth2 for authentication](https://developers.box.com/docs/#oauth-2). Since OAuth is a little tricky, the SDK provides an OAuth WebView UI using JavaFX. This WebView must be built separately because it has an extra dependency on the JavaFX JDK.

JavaFX is included by default starting with JDK 7u6. You can find more information about it [here](http://www.oracle.com/technetwork/java/javase/overview/javafx-overview-2158620.html). There is also a [JavaFX Eclipse plugin](http://www.eclipse.org/efxclipse/install.html) if you're using Eclipse as your IDE.

JavaFX Example
--------------

You can find a sample [here](https://github.com/box/box-java-sdk-v2/blob/master/BoxJavaFxOAuth/src/com/box/boxjavalibv2/javafxoauth/sample/SampleOAuthCaller.java). One thing to pay extra attention to is that the callbacks in the `IAuthFlowListener` must use `SwingUtilities.invokeLater(runnable)` when interacting with the UI.
