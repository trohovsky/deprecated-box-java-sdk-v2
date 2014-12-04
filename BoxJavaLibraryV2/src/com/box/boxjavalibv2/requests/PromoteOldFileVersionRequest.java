package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.IBoxConfig;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.requestsbase.BoxDefaultRequestObject;
import com.box.restclientv2.requestsbase.DefaultBoxRequest;

public class PromoteOldFileVersionRequest extends DefaultBoxRequest {
	public static final String URI = "/files/%s/versions/current";
	
	private static final String TYPE = "type";
	private static final String FILE_VERSION = "file_version";
	private static final String ID = "id";

	private PromoteOldFileVersionRequest(IBoxConfig config, IBoxJSONParser parser, String fileId,
			BoxDefaultRequestObject requestObject) throws BoxRestException {
		super(config, parser, getUri(fileId), RestMethod.POST, requestObject);
		setExpectedResponseCode(HttpStatus.SC_CREATED);
	}
	
	/**
	 * @param config
	 * @param parser
	 * @param fileId - id of the file
	 * @param versionId - version id of the file version
	 * @return PromoteOldFileVersionRequest
	 * @throws BoxRestException
	 */
	public static PromoteOldFileVersionRequest getRequestObject(IBoxConfig config, IBoxJSONParser parser,
			String fileId, String versionId) throws BoxRestException {
		BoxDefaultRequestObject requestObject = new BoxDefaultRequestObject();
		requestObject.put(TYPE, FILE_VERSION);
		requestObject.put(ID, versionId);
		return new PromoteOldFileVersionRequest(config, parser, fileId, requestObject);
	}
	
	
	private static String getUri(final String fileId) {
        return String.format(URI, fileId);
    }

}
