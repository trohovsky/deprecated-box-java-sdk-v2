package com.box.boxjavalibv2.responseparsers;

import java.io.InputStream;

import com.box.boxjavalibv2.dao.BoxBigPayloadObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxResponse;
import com.box.restclientv2.responseparsers.DefaultFileResponseParser;

public class BoxBigPayloadResponseParser extends DefaultFileResponseParser {

    @Override
    public BoxBigPayloadObject parse(IBoxResponse response) throws BoxRestException {
        BoxBigPayloadObject obj = new BoxBigPayloadObject();
        obj.setContent((InputStream) super.parse(response));
        obj.setContentLength(response.getContentLength());
        return obj;
    }
}
