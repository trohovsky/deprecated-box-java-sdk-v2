Box Java SDK
=============

Building
--------

### Eclipse

To build from Eclipse, simply import the project into your workspace
as an existing project.

### Ant

The easiest way of building with Ant is by running `ant` from the
BoxJavaLibraryV2 directory. This will output a JAR to
`dist/debug/BoxJavaLibraryV2.jar`. You can see a full list of additional targets
by running `ant -p`.

	$ ant -p

	Main targets:

	clean    Removes any built files.
	debug    Performs a debug build.
	release  Performs a release build.
	test     Performs a debug build and then runs tests.
	Default target: debug

### Gradle (Experimental)

There is also experimental support for Gradle, allowing you to use the SDK with
Android Studio. You must have [Gradle 1.6](http://www.gradle.org/downloads)
installed.

Running `gradle build` will build the SDK and run its tests. A JAR will be
placed in `build/libs/BoxJavaLibraryV2-1.0.jar`. Alternatively, you can run
`gradle install` which will install the SDK to you local Maven repository. It
can then be referenced from other projects with the dependency string
`com.box.boxjavalibv2:BoxJavaLibraryV2:1.0`.

**Note for Android users:** You might get a warning that says "WARNING:
Dependency commons-logging :commons-logging:1.1.1 is ignored for the default
configuration as it may be conflicting with the internal version provided by
Android." This is expected and shouldn't affect your build.

API Calls Quickstart
--------------------

### Hello World

You can find a hello world example [here][hello-world].
For migration to V3 from earlier version, please see "Migration to V3" section at the end of this README

### Authenticate

Authenticate the client with OAuth. For more details about the authentication flow(UI), please see the Authentication section.

```java
boxClient.authenticate(boxOAuthToken);
```

Our sdk auto refreshes OAuth access token when it expires. You will want to listen to the refresh events and update your stored token after refreshing.
```java
boxClient.addOAuthRefreshListener(new OAuthRefreshListener() {
        @Override
        public void onRefresh(IAuthData newAuthData) {
            // TODO: save the auth data.
        }
});
```

After you exit the app and return back, you can use the stored oauth data to authenticate:
```java
boxClient.authenticate(loadStoredAuthData);
``` 


For more details please see the [hello world example][hello-world].

### Get Default File Info

```java
BoxFile boxFile = boxClient.getFilesManager().getFile(fileId, null);
```

### Get Additional File Info

Get default file info plus its description and SHA1.

```java
BoxDefaultRequestObject requestObj = new BoxDefaultRequestObject();
requestObj.getRequestExtras().addField(BoxFile.FIELD_SHA1);
requestObj.getRequestExtras().addField(BoxFile.FIELD_DESCRIPTION);
BoxFile boxFile = boxClient.getFilesManager().getFile(fileId, requestObj);
```

### Get Folder Children

Get 30 child items, starting from the 20th one, requiring etag, description, and
name to be included.

```java
BoxPagingRequestObject requestObj = BoxPagingRequestObject.BpagingRequestObject(30, 20);
requestObj.getRequestExtras().addField(BoxFolder.FIELD_NAME);
requestObj.getRequestExtras().addField(BoxFolder.FIELD_DESCRIPTION);
requestObj.getRequestExtras().addField(BoxFolder.FIELD_ETAG);
BoxCollection collection = 
	boxClient.getFoldersManager().getFolderItems(folderId, requestObj);
```

### Upload a New File

```java
BoxFileUploadRequestObject requestObj = 
	BoxFileUploadRequestObject.uploadFileRequestObject(parent, "name", file);
BoxFile bFile = boxClient.getFilesManager().uploadFile(requestObj);
```

### Upload a File with a Progress Listener

```java
BoxFileUploadRequestObject requestObj = 
	BoxFileUploadRequestObject.uploadFileRequestObject(parent, "name", file)
		.setListener(listener));
BoxFile bFile = boxClient.getFilesManager().uploadFile(requestObj);
```

### Download a File

```java
boxClient.getFilesManager().downloadFile(fileId, null);
```

### Delete a File

Delete a file, but only if the etag matches.

```java
BoxDefaultRequestObject requestObj = new BoxDefaultRequestObject();
requestObject.getRequestExtras.setIfMatch(etag);
boxClient.getFilesManager().deleteFile(fileId, requestObj);
```

### Configure raw httpclient (e.g., set proxy)
You need to supply a customized IBoxRESTClient to construct a BoxClient.
```java
	IBoxRESTClient restClient = new BoxRESTClient() {
            @Override
            public HttpClient getRawHttpClient() {
                HttpClient client = super.getRawHttpClient();
                // Now do the configure settings.
                HttpHost proxy = new HttpHost("{proxy ip/url}",{proxy port}, "{proxy scheme, e.g. http}";
                client.getParams().setParameter(ConnRouteNames.DEFAULT_PROXY, proxy);
                return client; 
            }
        };
        
	BoxClient client = new BoxClient(clientId, clientSecret, null, null, restClient BoxConfigBuilder.build());

```


Authentication
------------
The SDK provides an OAuth UI using javafx, this is a seperate java project in our github due to the fact that this UI requires javafx sdk and not everybody wants it.
To install javafx, you can either follow [javafx instruction](http://www.oracle.com/technetwork/java/javafx/overview/index.html) or install the [javafx eclipse plugin](http://www.eclipse.org/efxclipse/install.html)

You can find a sample using this oauth UI [here](https://github.com/box/box-java-sdk-v2/blob/master/BoxJavaFxOAuth/src/com/box/boxjavalibv2/javafxoauth/sample/SampleOAuthCaller.java). One thing to pay extra attention is that the call backs in the IAuthFlowListener() will all run on javafx thread, they cannot trigger java swing data change directly, the trigger need to be done in java swing thread. In the sample, this is done by SwingUtilities.invokeLater(runnable) method.

Migration to V3
------------

- Resource manager interfaces.
pre-v3, our boxClient.get***Manager() method returns concrete class of resource managers. For the purpose of a cleaner interface, in v3, they return resource manager interfaces.
```java
old code:
BoxFilesManager filesManager = boxClient.getFilesManager();
filesManager.doSomething(...);
new code:
IBoxFilesManager filesManager = boxClient.getFilesManager();
filesManager.doSomething(...);
```
- Made certain methods more convenient, e.g., OAuth api related methods:
```java
old code:
BoxOAuthRequestObject obj = BoxOAuthRequestObject.crateOAuthRequestObject(code, clientId, clientSecret, redirectUrl);
BoxOAuthData oauth = oauthManager.createOAuth(obj);
new Code:
BoxOAuthData oauth = oauthManager.createOAuth(code, clientId, clientSecret, redirectUrl);
```
- BoxFilesManager/BoxFoldersManager. Methods acting upon BoxItems are moved to BoxItemsManager to avoid confusion.
Example, get a BoxFile.
```java
Old code:
Two ways to get it:
1. boxClient.getFilesManager.getFile(fileId, null);
2. boxClient.getFilesManager.getItem(fileId, null, BoxResourceType.FILE);
New code:
Two ways to get it:
1. same as old code.
2. boxClient.getBoxItemsManager.getItem(fileId, null, BoxResourceType.FILE);
```
- Trash Manager: old code has methods for trashed files/folders in FilesManager/FoldersManager, new code moved them into a trash manager.
Example:
```java
old code:
boxClient.getFilesManager.getTrashFile(fileId, null);
new code:
boxClient.getTrashManager.getTrashFile(fileId, null);
```
- request objects: To avoid confusion, request objects now are more api specific. There are some type changes, however the way you used to write the code remain the same. One example:
```java
Old code of create a shared link.
BoxFileRequestObject  obj = BoxFileRequestObject. createSharedLinkRequestObject(......);
filesManager.createSharedLink(fileId, obj);
New code:
BoxSharedLinkRequestObject obj = 
   BoxSharedLinkRequestObject.
   createSharedLinkRequestObject(sharedLinkEntity);
filesManager.createSharedLink(fileId, obj);
```
Also in order to provide cleaner interface, we moved the setters for basic http requests in the request objects to a "requestExtra".
```java
Old code:
requestObject.addField("some field");
new code:
requestObject.getRequestExtras().addField("some field");
```
(Optional/Deprecated)
- utils methods in resource managers.
In case you were using the utils methods in resource managers to filter for specific items from collection, they are now deprecated and moved to util methods.
```java
old code:
List<BoxFile> filesInCollection = BoxFilesManager.getFiles(collection);
new code:
List<BoxFile> filesInCollection = Utils.getTypedObjects(collection, BoxFile.class);
```
- get thumbnail.
```java
old code: 
InputStream is = filesManager.downloadThumbnail(fileId, extension, null);
new code:
BoxThumbnail thumbnail = filesManager.downloadThumbnail(fileId, extension, null);
```




[hello-world]: https://github.com/box/box-java-sdk-private/wiki/HelloWorld


## Copyright and License

Copyright 2014 Box, Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
