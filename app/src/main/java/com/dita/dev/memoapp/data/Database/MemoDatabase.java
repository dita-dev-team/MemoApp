package com.dita.dev.memoapp.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemoDatabase extends SQLiteOpenHelper {
    public static final String MEMOS = "CREATE TABLE IF NOT EXISTS memos ("
            + MemosColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MemosColumns.USERNAME + " TEXT NOT NULL,"
            + MemosColumns.SUBJECT + " TEXT,"
            + MemosColumns.MESSAGE + " TEXT,"
            + MemosColumns.ATTACHMENT + " TEXT,"
            + MemosColumns.STATUS + " TEXT NOT NULL,"
            + MemosColumns.DATE + " INTEGER)";
    public static final String CONNECTIONS = "CREATE TABLE IF NOT EXISTS connections ("
            + ConnectionsColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ConnectionsColumns.USERNAME + " TEXT UNIQUE,"
            + ConnectionsColumns.PROFILE_IMAGE + " TEXT,"
            + ConnectionsColumns.TYPE + " TEXT NOT NULL)";
    private static final int DATABASE_VERSION = 1;
    private static volatile MemoDatabase instance;

    private Context context;

    private MemoDatabase(Context context) {
        super(context, "memoDatabase.db", null, DATABASE_VERSION);
        this.context = context;
    }

    public static MemoDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (MemoDatabase.class) {
                if (instance == null) {
                    instance = new MemoDatabase(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MEMOS);
        db.execSQL(CONNECTIONS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
