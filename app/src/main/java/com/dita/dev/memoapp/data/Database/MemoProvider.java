package com.dita.dev.memoapp.data.database;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;


public class MemoProvider extends ContentProvider {
    public static final String AUTHORITY = "com.dev.dita.memoprovider.MemoProvider";

    private static final int MEMOS_CONTENT_URI = 0;

    private static final int MEMOS_MEMO_ID = 1;

    private static final int CONNECTIONS_CONTENT_URI = 2;

    private static final int CONNECTIONS_CONNECTION_USERNAME = 3;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, "memos", MEMOS_CONTENT_URI);
        MATCHER.addURI(AUTHORITY, "memos/#", MEMOS_MEMO_ID);
        MATCHER.addURI(AUTHORITY, "connections", CONNECTIONS_CONTENT_URI);
        MATCHER.addURI(AUTHORITY, "connections/#", CONNECTIONS_CONNECTION_USERNAME);
    }

    private SQLiteOpenHelper database;

    @Override
    public boolean onCreate() {
        database = MemoDatabase.getInstance(getContext());
        return true;
    }

    private SelectionBuilder getBuilder(String table) {
        SelectionBuilder builder = new SelectionBuilder();
        return builder;
    }

    private long[] insertValues(SQLiteDatabase db, String table, ContentValues[] values) {
        long[] ids = new long[values.length];
        for (int i = 0; i < values.length; i++) {
            ContentValues cv = values[i];
            db.insertOrThrow(table, null, cv);
        }
        return ids;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = database.getWritableDatabase();
        db.beginTransaction();
        try {
            switch (MATCHER.match(uri)) {
                case MEMOS_CONTENT_URI: {
                    long[] ids = insertValues(db, "memos", values);
                    getContext().getContentResolver().notifyChange(uri, null);
                    break;
                }
                case MEMOS_MEMO_ID: {
                    long[] ids = insertValues(db, "memos", values);
                    getContext().getContentResolver().notifyChange(uri, null);
                    break;
                }
                case CONNECTIONS_CONTENT_URI: {
                    long[] ids = insertValues(db, "connections", values);
                    getContext().getContentResolver().notifyChange(uri, null);
                    break;
                }
                case CONNECTIONS_CONNECTION_USERNAME: {
                    long[] ids = insertValues(db, "connections", values);
                    getContext().getContentResolver().notifyChange(uri, null);
                    break;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return values.length;
    }

    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> ops) throws OperationApplicationException {
        ContentProviderResult[] results;
        final SQLiteDatabase db = database.getWritableDatabase();
        db.beginTransaction();
        try {
            results = super.applyBatch(ops);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return results;
    }

    @Override
    public String getType(Uri uri) {
        switch (MATCHER.match(uri)) {
            case MEMOS_CONTENT_URI: {
                return "vnd.android.cursor.dir/memos";
            }
            case MEMOS_MEMO_ID: {
                return "vnd.android.cursor.item/memos";
            }
            case CONNECTIONS_CONTENT_URI: {
                return "vnd.android.cursor.dir/connections";
            }
            case CONNECTIONS_CONNECTION_USERNAME: {
                return "vnd.android.cursor.item/connections";
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = database.getReadableDatabase();
        switch (MATCHER.match(uri)) {
            case MEMOS_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Memos");
                if (sortOrder == null) {
                    sortOrder = "date ASC";
                }
                final String groupBy = null;
                final String having = null;
                final String limit = null;
                Cursor cursor = builder.table("memos")
                        .where(selection, selectionArgs)
                        .query(db, projection, groupBy, having, sortOrder, limit);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            case MEMOS_MEMO_ID: {
                SelectionBuilder builder = getBuilder("Memos");
                final String groupBy = null;
                final String having = null;
                final String limit = null;
                Cursor cursor = builder.table("memos")
                        .where("id=?", uri.getPathSegments().get(1))
                        .where(selection, selectionArgs)
                        .query(db, projection, groupBy, having, sortOrder, limit);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            case CONNECTIONS_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Connections");
                if (sortOrder == null) {
                    sortOrder = "username ASC";
                }
                final String groupBy = null;
                final String having = null;
                final String limit = null;
                Cursor cursor = builder.table("connections")
                        .where(selection, selectionArgs)
                        .query(db, projection, groupBy, having, sortOrder, limit);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            case CONNECTIONS_CONNECTION_USERNAME: {
                SelectionBuilder builder = getBuilder("Connections");
                final String groupBy = null;
                final String having = null;
                final String limit = null;
                Cursor cursor = builder.table("connections")
                        .where("username=?", uri.getPathSegments().get(2))
                        .where(selection, selectionArgs)
                        .query(db, projection, groupBy, having, sortOrder, limit);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = database.getWritableDatabase();
        switch (MATCHER.match(uri)) {
            case MEMOS_CONTENT_URI: {
                final long id = db.insertOrThrow("memos", null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            case MEMOS_MEMO_ID: {
                final long id = db.insertOrThrow("memos", null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            case CONNECTIONS_CONTENT_URI: {
                final long id = db.insertOrThrow("connections", null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            case CONNECTIONS_CONNECTION_USERNAME: {
                final long id = db.insertOrThrow("connections", null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        final SQLiteDatabase db = database.getWritableDatabase();
        switch (MATCHER.match(uri)) {
            case MEMOS_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Memos");
                builder.where(where, whereArgs);
                final int count = builder.table("memos")
                        .update(db, values);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return count;
            }
            case MEMOS_MEMO_ID: {
                SelectionBuilder builder = getBuilder("Memos");
                builder.where("id=?", uri.getPathSegments().get(1));
                builder.where(where, whereArgs);
                final int count = builder.table("memos")
                        .update(db, values);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return count;
            }
            case CONNECTIONS_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Connections");
                builder.where(where, whereArgs);
                final int count = builder.table("connections")
                        .update(db, values);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return count;
            }
            case CONNECTIONS_CONNECTION_USERNAME: {
                SelectionBuilder builder = getBuilder("Connections");
                builder.where("username=?", uri.getPathSegments().get(2));
                builder.where(where, whereArgs);
                final int count = builder.table("connections")
                        .update(db, values);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return count;
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        final SQLiteDatabase db = database.getWritableDatabase();
        switch (MATCHER.match(uri)) {
            case MEMOS_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Memos");
                builder.where(where, whereArgs);
                final int count = builder
                        .table("memos")
                        .delete(db);
                getContext().getContentResolver().notifyChange(uri, null);
                return count;
            }
            case MEMOS_MEMO_ID: {
                SelectionBuilder builder = getBuilder("Memos");
                builder.where("id=?", uri.getPathSegments().get(1));
                builder.where(where, whereArgs);
                final int count = builder
                        .table("memos")
                        .delete(db);
                getContext().getContentResolver().notifyChange(uri, null);
                return count;
            }
            case CONNECTIONS_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Connections");
                builder.where(where, whereArgs);
                final int count = builder
                        .table("connections")
                        .delete(db);
                getContext().getContentResolver().notifyChange(uri, null);
                return count;
            }
            case CONNECTIONS_CONNECTION_USERNAME: {
                SelectionBuilder builder = getBuilder("Connections");
                builder.where("username=?", uri.getPathSegments().get(2));
                builder.where(where, whereArgs);
                final int count = builder
                        .table("connections")
                        .delete(db);
                getContext().getContentResolver().notifyChange(uri, null);
                return count;
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }
}
