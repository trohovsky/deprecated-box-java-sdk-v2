package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.IBoxConfig;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.requestsbase.BoxDefaultRequestObject;
import com.box.restclientv2.requestsbase.DefaultBoxRequest;

public class DeleteFileVersionRequest extends DefaultBoxRequest {
	 public static final String URI = "/files/%s/versions/%s";

	/**
	 * @param config
	 * @param parser
	 * @param uriPath
	 * @param restMethod
	 * @param requestObject
	 * @throws BoxRestException
	 */
	public DeleteFileVersionRequest(IBoxConfig config, IBoxJSONParser parser, String fileId, String versionId, 
			BoxDefaultRequestObject requestObject) throws BoxRestException {
		super(config, parser, getUri(fileId, versionId), RestMethod.DELETE, requestObject);
		setExpectedResponseCode(HttpStatus.SC_NO_CONTENT);
	}
	
    public static String getUri(final String fileId, final String versionId) {
        return String.format(URI, fileId, versionId);
    }

}
