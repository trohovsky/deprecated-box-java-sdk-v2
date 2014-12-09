package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.IBoxConfig;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.requestsbase.BoxDefaultRequestObject;
import com.box.restclientv2.requestsbase.DefaultBoxRequest;

public class LockUnlockFileRequest extends DefaultBoxRequest {
	private static final String URI = "/files/%s";
	private static final String LOCK = "lock";
	private static final String TYPE = "type";
	private static final String IS_DOWNLOAD_RESTRICTED = "is_download_prevented";
	
	private LockUnlockFileRequest(IBoxConfig config, IBoxJSONParser parser, String id, BoxDefaultRequestObject requestObject)
			throws BoxRestException {
		super(config, parser, getUri(id), RestMethod.PUT, requestObject);
	}

	/**
	 * Get request object to lock a file.
	 * @param config
	 * @param parser
	 * @param id
	 * @param isDownloadRestricted
	 * @return LockUnlockFileRequest
	 * @throws BoxRestException
	 */
	public static LockUnlockFileRequest getLockFileRequest(IBoxConfig config, IBoxJSONParser parser, String id, Boolean isDownloadRestricted) 
			throws BoxRestException {
		BoxDefaultRequestObject requestObject = new BoxDefaultRequestObject();
		MapJSONStringEntity jsonEntity = new MapJSONStringEntity();
		jsonEntity.put(TYPE, "lock");
		jsonEntity.put(IS_DOWNLOAD_RESTRICTED, isDownloadRestricted);
		requestObject.put(LOCK, jsonEntity);
		return new LockUnlockFileRequest(config, parser, id, requestObject);
	}

	
	/**
	 * Get request object to unlock a file.
	 * @param config
	 * @param parser
	 * @param id
	 * @return LockUnlockFileRequest
	 * @throws BoxRestException
	 */
	public static LockUnlockFileRequest getUnlockFileRequest(IBoxConfig config, IBoxJSONParser parser, String id) 
			throws BoxRestException {
		BoxDefaultRequestObject requestObject = new BoxDefaultRequestObject();
		requestObject.put(LOCK, null);
		return new LockUnlockFileRequest(config, parser, id, requestObject);
	}
	
	
    private static String getUri(final String fileId) {
        return String.format(URI, fileId);
    }
}
