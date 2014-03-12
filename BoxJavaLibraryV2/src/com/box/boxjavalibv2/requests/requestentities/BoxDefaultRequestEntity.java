package com.box.boxjavalibv2.requests.requestentities;

import org.apache.commons.lang.NotImplementedException;

import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.IBoxRequestObject;

public class BoxDefaultRequestEntity implements IBoxRequestEntity {

    private final MapJSONStringEntity map = new MapJSONStringEntity();

    @Override
    public void applyToRequestObject(IBoxRequestObject obj) {
        if (!(obj instanceof BoxDefaultRequestObject)) {
            throw new NotImplementedException("BoxDefaultRequestEntity can only be applied to BoxDefaultRequestObject");
        }

        BoxDefaultRequestObject requestObj = (BoxDefaultRequestObject) obj;
        requestObj.setJSONEntity(map);
    }

    /**
     * Add a key value pair to the request entity. This is not recommended to use directly. If applicable, setters are always preferable.
     * 
     * @param key
     *            key
     * @param value
     *            value
     */
    public Object put(String key, Object value) {
        return map.put(key, value);
    }
}
