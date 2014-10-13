[![Project Status](http://opensource.box.com/badges/active.svg)](http://opensource.box.com/badges)

Box Java SDK
=============

Getting Started
---------------

Maven and gradle are the easiest ways to add the SDK to your project.

### Maven

```xml
<dependency>
  <groupId>net.box</groupId>
  <artifactId>boxjavalibv2</artifactId>
  <version>{version}</version>
</dependency>
```

### Gradle

```groovy
dependencies {
    compile 'net.box:boxjavalibv2:{version}'
}
```

Quickstart
----------

### Authenticate

Authenticate the client with OAuth 2. For more information on OAuth2, see the [Box Platform documentation](https://developers.box.com/docs/#oauth-2).

```java
boxClient.authenticate(boxOAuthToken);
```

The SDK will auto refresh OAuth access tokens when they expire. If you're storing the access token somewhere (for example, to keep the user logged in after they exit your application), you'll want to listen to the refresh event so you can update the stored token.

```java
boxClient.addOAuthRefreshListener(new OAuthRefreshListener() {
        @Override
        public void onRefresh(IAuthData newAuthData) {
            // TODO: Update the stored access token.
        }
});
```

The SDK also provides a JavaFX WebView UI that handles authentication for you. More details about it can be found [here](https://github.com/box/box-java-sdk-v2/tree/master/BoxJavaFxOAuth).

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

Get 30 child items, starting from the 20th one, requiring etag, description, and name to be included.

```java
BoxPagingRequestObject requestObj = BoxPagingRequestObject.pagingRequestObject(30, 20);

requestObj.getRequestExtras().addField(BoxFolder.FIELD_NAME);
requestObj.getRequestExtras().addField(BoxFolder.FIELD_DESCRIPTION);
requestObj.getRequestExtras().addField(BoxFolder.FIELD_ETAG);

BoxCollection collection = boxClient.getFoldersManager().getFolderItems(folderId, requestObj);
```

### Upload a New File

```java
BoxFileUploadRequestObject requestObj = BoxFileUploadRequestObject.uploadFileRequestObject(parent, "name", file);
BoxFile bFile = boxClient.getFilesManager().uploadFile(requestObj);
```

### Upload a File with a Progress Listener

```java
BoxFileUploadRequestObject requestObj = BoxFileUploadRequestObject.uploadFileRequestObject(parent, "name", file);
requestObj.setListener(listener);
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

### Use a Custom HttpClient (e.g., to set proxy)

Simply override the `getRawHttpClient()` method in `BoxRESTClient`.

```java
    IBoxRESTClient restClient = new BoxRESTClient() {
        @Override
        public HttpClient getRawHttpClient() {
            HttpClient client = super.getRawHttpClient();
            HttpHost proxy = new HttpHost("{proxy ip/url}", {proxy port}, "{proxy scheme, e.g. http}");
            client.getParams().setParameter(ConnRouteNames.DEFAULT_PROXY, proxy);

            return client; 
        }
    };
        
    BoxClient client = new BoxClient(clientId, clientSecret, null, null, restClient, BoxConfigBuilder.build());
```

Versioning
----------

Major version bumps in the SDK may introduce some breaking changes. We provide a lists of these changes in our migration docs.

* [v3 Migration Guide](https://github.com/box/box-java-sdk-v2/tree/master/docs/migration-to-v3.md)

Copyright and License
---------------------

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
