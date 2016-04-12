package com.dita.dev.memoapp.data.database;

/**
 * Created by WHITE on 4/6/2016.
 */
public interface DBTables {
    String CONNECTIONS_QUERY = "CREATE TABLE IF NOT EXISTS connections(" +
            "username INTEGER PRIMARY KEY," +
            "image_uri TEXT DEFAULT NULL," +
            "type TEXT NOT NULL)";
    String MEMO_QUERY = "CREATE TABLE IF NOT EXISTS memos(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT NOT NULL," +
            "subject TEXT," +
            "message TEXT," +
            "attachment TEXT" +
            "status TEXT NOT NULL )";
}
