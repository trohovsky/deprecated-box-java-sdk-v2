package com.box.boxjavalibv2.requests.requestobjects;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;

import com.box.boxjavalibv2.exceptions.BoxJSONException;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.restclientv2.exceptions.BoxRestException;

/**
 * Interface for objects to be put into api requests.
 */
public interface IBoxRequestObject {

    /**
     * Get entity, which will be put into request body.
     * 
     * @return HttpEntity.
     * @throws BoxRestException
     * @throws BoxJSONException
     * @throws UnsupportedEncodingException
     */
    public HttpEntity getEntity(IBoxJSONParser parser) throws BoxRestException, BoxJSONException, UnsupportedEncodingException;
}
