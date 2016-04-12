package com.dita.dev.memoapp.ui.activity;

import android.content.ContentResolver;
import android.net.Uri;

/**
 * Created by WHITE on 4/8/2016.
 */
public class MemoContract {
    public static final String AUTHORITY = "com.dev.dita.memoprovider.MemoProvider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class Memos {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath("memos").build();

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                + "/memoItem";

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/memoItem";
    }

    public static final class Connections {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath("connections").build();

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                + "/indvidual";

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/individual";
    }
}
