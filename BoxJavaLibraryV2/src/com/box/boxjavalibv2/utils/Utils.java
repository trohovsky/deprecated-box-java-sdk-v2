package com.box.boxjavalibv2.utils;

import java.util.ArrayList;
import java.util.List;

import com.box.boxjavalibv2.dao.BoxCollection;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.dao.BoxTypedObject;

/**
 * Utils class.
 */
public final class Utils {

    /**
     * Private constructor so the class cannot be instantiated.
     */
    private Utils() {
    }

    /**
     * Given a resource type, get the string for it's REST API container. For example, given a {@link BoxResourceType#FILE}, it it's container would be "files".
     * 
     * @param type
     *            type
     * @return container string
     */
    public static String getContainerString(final BoxResourceType type) {
        switch (type) {
            case FILE_VERSION:
                return "versions";
            default:
                return type.toPluralString();
        }
    }

    /**
     * Filter out a list of specified type BoxTypedObject's from a Collection
     * 
     * @param collection
     *            collection to be filtered.
     * @param cls
     *            class of the type to be filtered. e.g. BoxFile.class.
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends BoxTypedObject> List<T> getTypedObjects(BoxCollection collection, Class<T> cls) {
        List<T> objects = new ArrayList<T>();
        try {
            T instance = cls.newInstance();
            List<BoxTypedObject> list = collection.getEntries();
            for (BoxTypedObject object : list) {
                if (object.getClass().isInstance(instance)) {
                    objects.add((T) object);
                }
            }
        }
        catch (InstantiationException e) {
            // No default constructor, not a BoxTypedObject.
        }
        catch (IllegalAccessException e) {
            // No default constructor, not a BoxTypedObject.
        }

        return objects;
    }
}
