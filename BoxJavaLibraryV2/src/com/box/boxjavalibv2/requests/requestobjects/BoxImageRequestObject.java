package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxPreview;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;

public class BoxImageRequestObject extends BoxDefaultRequestObject {

    private BoxImageRequestObject(IBoxJSONParser parser) {
        super(parser);
    }

    /**
     * Get BoxImageRequestObject for a preview request with pages.
     * 
     * @param page
     *            requested preview page number(For example, 2 if you want to get the 2nd page of the preview if the preview has multiple pages.)
     * @param minWidth
     *            minimum width of the preview image requested
     * @param maxWidth
     *            maximum width of the preview image requested
     * @param minHeight
     *            minimum height of the preview image requested
     * @param maxHeight
     *            maximum height of the preview image requested
     * @return BoxPreviewRequestObject
     */
    public static BoxImageRequestObject pagePreviewRequestObject(final int page, final int minWidth, final int maxWidth, final int minHeight,
        final int maxHeight, final IBoxJSONParser parser) {
        return (new BoxImageRequestObject(parser)).setPage(page).setMinHeight(minHeight).setMaxHeight(maxHeight).setMinWidth(minWidth).setMaxWidth(maxWidth);
    }

    /**
     * Get BoxImageRequestObject for an image or preview request without pages.
     * 
     * @return BoxPreviewRequestObject
     */
    public static BoxImageRequestObject previewRequestObject(final IBoxJSONParser parser) {
        return new BoxImageRequestObject(parser);
    }

    public BoxImageRequestObject setMinWidth(int minWidth) {
        addQueryParam(BoxPreview.MIN_WIDTH, Integer.toString(minWidth));
        return this;
    }

    public BoxImageRequestObject setMaxWidth(int maxWidth) {
        addQueryParam(BoxPreview.MAX_WIDTH, Integer.toString(maxWidth));
        return this;
    }

    public BoxImageRequestObject setMinHeight(int minHeight) {
        addQueryParam(BoxPreview.MIN_HEIGHT, Integer.toString(minHeight));
        return this;
    }

    public BoxImageRequestObject setMaxHeight(int maxHeight) {
        addQueryParam(BoxPreview.MAX_HEIGHT, Integer.toString(maxHeight));
        return this;
    }

    public BoxImageRequestObject setPage(int page) {
        addQueryParam(BoxPreview.PAGE, Integer.toString(page));
        return this;
    }
}
