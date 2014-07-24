# Migration to v3

v3 of the SDK introduced a number of method and type changes that may require you to update your code.

## Breaking Changes

### Resource manager interfaces

The `getManager()` methods in `BoxClient` now return interfaces instead of concrete classes.

#### Old

```java
BoxFilesManager filesManager = boxClient.getFilesManager();
filesManager.doSomething(...);
```

#### New

```java
IBoxFilesManager filesManager = boxClient.getFilesManager();
filesManager.doSomething(...);
```

### Made certain methods more convenient

For example, the OAuth API methods no longer require a separate `BoxOAuthRequestObject`.

#### Old

```java
BoxOAuthRequestObject obj = BoxOAuthRequestObject.crateOAuthRequestObject(code, clientId, clientSecret, redirectUrl);
BoxOAuthData oauth = oauthManager.createOAuth(obj);
```

#### New

```java
BoxOAuthData oauth = oauthManager.createOAuth(code, clientId, clientSecret, redirectUrl);
```

### BoxFilesManager and BoxFoldersManager method changes

Methods acting upon `BoxItems` are moved to `BoxItemsManager` to avoid confusion. For example, when getting a file by specifying the resource type.

#### Old

```java
boxClient.getFilesManager.getFile(fileId, null);
// or
boxClient.getFilesManager.getItem(fileId, null, BoxResourceType.FILE);
```

#### New

```java
boxClient.getFilesManager.getFile(fileId, null);
// or
boxClient.getBoxItemsManager.getItem(fileId, null, BoxResourceType.FILE);
```

### Trash Manager

Methods for trashed files and folders have been moved from `FilesManager` and `FoldersManager` into a new `TrashManager`.

#### Old

```java
boxClient.getFilesManager.getTrashFile(fileId, null);
```

#### New

```java
boxClient.getTrashManager.getTrashFile(fileId, null);
```

### More specific request object types

To avoid confusion, request objects are now more API specific. For example, `BoxFileRequestObject.createSharedLinkRequestObject()` now returns a `BoxSharedLinkRequestObject` instead of a `BoxFileRequestObject`.

#### Old

```java
BoxFileRequestObject obj = BoxFileRequestObject.createSharedLinkRequestObject(...);
filesManager.createSharedLink(fileId, obj);
```

#### New

```java
BoxSharedLinkRequestObject obj = BoxSharedLinkRequestObject.createSharedLinkRequestObject(sharedLinkEntity);
filesManager.createSharedLink(fileId, obj);
```

Also in order to provide cleaner interface, we moved the setters for basic HTTP requests to a `BoxRequestExtras` object.

#### Old

```java
requestObject.addField("some field");
```

#### New

```java
requestObject.getRequestExtras().addField("some field");
```

### Thumbnails now have their own BoxThumbnail type

#### Old

```java
InputStream is = filesManager.downloadThumbnail(fileId, extension, null);
```

#### New

```java
BoxThumbnail thumbnail = filesManager.downloadThumbnail(fileId, extension, null);
```

## Deprecated Methods

### Utility methods in the resource managers

The utility methods in resource managers for filtering specific items from a collection have been deprecated and moved to a `Utils` class.

#### Old

```java
List<BoxFile> filesInCollection = BoxFilesManager.getFiles(collection);
```

#### New

```java
List<BoxFile> filesInCollection = Utils.getTypedObjects(collection, BoxFile.class);
```
