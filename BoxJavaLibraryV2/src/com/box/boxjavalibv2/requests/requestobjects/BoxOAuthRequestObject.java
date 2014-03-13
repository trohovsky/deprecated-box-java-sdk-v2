package com.box.boxjavalibv2.requests.requestobjects;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.boxjavalibv2.requests.requestentities.BoxOAuthRequestEntity;
import com.box.restclientv2.exceptions.BoxRestException;

public class BoxOAuthRequestObject extends BoxEntityRequestObject<BoxOAuthRequestEntity> {

    private BoxOAuthRequestObject(BoxOAuthRequestEntity entity) {
        super(entity);
    }

    public static BoxOAuthRequestObject getRequestObject(BoxOAuthRequestEntity entity) {
        return new BoxOAuthRequestObject(entity);
    }

    @Override
    public UrlEncodedFormEntity getEntity(IBoxJSONParser parser) throws BoxRestException {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : getJSONEntity().entrySet()) {
            Object value = entry.getValue();
            if (value != null && value instanceof String) {
                String strValue = (String) value;
                if (StringUtils.isNotEmpty(strValue)) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), strValue));
                }
            }
        }

        try {
            return new UrlEncodedFormEntity(pairs, CharEncoding.UTF_8);
        }
        catch (UnsupportedEncodingException e) {
            throw new BoxRestException(e);
        }
    }
}
