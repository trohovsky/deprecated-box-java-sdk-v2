package com.box.boxjavalibv2.requests.requestobjects;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.CharEncoding;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import com.box.boxjavalibv2.exceptions.BoxJSONException;
import com.box.boxjavalibv2.jsonentities.IBoxJSONStringEntity;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.boxjavalibv2.requests.requestentities.IBoxRequestEntity;
import com.box.restclientv2.exceptions.BoxRestException;

/**
 * A request object with entity and fields.
 */
public class BoxDefaultRequestObject implements IBoxRequestObject {

    private MapJSONStringEntity jsonEntity;
    private final List<String> fields = new ArrayList<String>();
    private final Map<String, String> queryParams = new HashMap<String, String>();
    private final Map<String, String> headers = new HashMap<String, String>();

    public BoxDefaultRequestObject() {
    }

    @Override
    public HttpEntity getEntity(IBoxJSONParser parser) throws BoxRestException, BoxJSONException, UnsupportedEncodingException {
        MapJSONStringEntity en = getJSONEntity();
        if (en == null) {
            return null;
        }
        try {
            return new StringEntity(en.toJSONString(parser), CharEncoding.UTF_8);
        }
        catch (UnsupportedEncodingException e) {
            throw new BoxRestException(e);
        }
    }

    public MapJSONStringEntity getJSONEntity() {
        return jsonEntity;
    }

    public void setJSONEntity(MapJSONStringEntity entity) {
        this.jsonEntity = entity;
    }

    protected void takeRequestEntity(IBoxRequestEntity entity) {
        entity.applyToRequestObject(this);
    }

    @Override
    public List<String> getFields() {
        return fields;
    }

    /**
     * Add a field in the request, these fields (Please check "Fields" part in <a href="http://developers.box.com/docs/">developer doc</a> will end up as fields
     * query parameter in the url.
     * 
     * @param field
     *            field to add. Currently supported fields are the Strings defined in {@link com.box.boxjavalibv2.dao#BoxCollaboration}, for example:
     *            {@link com.box.boxjavalibv2.dao.BoxCollaboration#FIELD_ROLE}, {@link com.box.boxjavalibv2.dao.BoxCollaboration#FIELD_CREATED_BY}...
     */
    public BoxDefaultRequestObject addField(String field) {
        getFields().add(field);
        return this;
    }

    /**
     * Add fields in the request, these fields (Please check "Fields" part in <a href="http://developers.box.com/docs/">developer doc</a> will end up as fields
     * query parameter in the url.
     * 
     * @param fields
     *            fields to add. Currently supported fields are the Strings defined in {@link com.box.boxjavalibv2.dao#BoxCollaboration}, for example:
     *            {@link com.box.boxjavalibv2.dao.BoxCollaboration#FIELD_ROLE}, {@link com.box.boxjavalibv2.dao.BoxCollaboration#FIELD_CREATED_BY}...
     */
    public BoxDefaultRequestObject addFields(List<String> fields) {
        getFields().addAll(fields);
        return this;
    }

    /**
     * Add a query parameter. Which eventually will go into url.
     * 
     * @param key
     *            key
     * @param value
     *            value
     */
    public BoxDefaultRequestObject addQueryParam(String key, String value) {
        queryParams.put(key, value);
        return this;
    }

    /**
     * Add a header.
     * 
     * @param key
     *            key
     * @param value
     *            value
     */
    public BoxDefaultRequestObject addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    // TODO: remove these put's
    /**
     * Add a key value pair to the request body.
     * 
     * @param key
     *            key
     * @param value
     *            value
     */
    public IBoxJSONStringEntity put(String key, IBoxJSONStringEntity value) {
        return (IBoxJSONStringEntity) getJSONEntity().put(key, value);
    }

    /**
     * Add a key value string pair to the request body.
     * 
     * @param key
     *            key
     * @param value
     *            value
     */
    public String put(String key, String value) {
        return (String) getJSONEntity().put(key, value);
    }

    /**
     * Add a key value string pair to the request body.
     * 
     * @param key
     *            key
     * @param value
     *            value
     */
    public String[] put(String key, String[] value) {
        return (String[]) getJSONEntity().put(key, value);
    }

    /**
     * @param limit
     *            the number of items to return. default is 100, max is 1000.
     * @param offset
     *            the item at which to begin the response, default is 0.
     * @return BoxFolderRequestObject
     */
    public BoxDefaultRequestObject setPage(final int limit, final int offset) {
        addQueryParam("limit", Integer.toString(limit));
        addQueryParam("offset", Integer.toString(offset));
        return this;
    }

    /**
     * Set etag.
     * 
     * @param etag
     *            etag
     * @return BoxFileRequestObject
     */
    public BoxDefaultRequestObject setIfMatch(String etag) {
        addHeader("If-Match", etag);
        return this;
    }
}
