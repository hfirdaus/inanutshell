package com.example.hushcoolcat.inanutshell;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseTable {
    private final DatabaseOpenHelper myDatabaseOpenHelper;

    private static final String TAG = "SearchDirectory";

    //columns we'll include in table
    public static final String COL_TITLE = "TITLE";
    public static final String COL_INGREDIENTS = "INGREDIENTS";

    //stats
    private static final String DATABASE_NAME = "DIRECTORY";
    private static final String FTS_VIRTUAL_TABLE = "FTS";
    private static final int DATABASE_VERSION = 1;

    public DatabaseTable(Context context) {
        myDatabaseOpenHelper = new DatabaseOpenHelper(context);
    }

    private static class DatabaseOpenHelper extends SQLiteOpenHelper {

        private final Context myHelperContext;

        private SQLiteDatabase myDatabase;

        private static final String FTS_TABLE_CREATE =
                "CREATE VIRTUAL TABLE " + FTS_VIRTUAL_TABLE +
                        " USING fts3 (" +
                        COL_TITLE + ", " +
                        COL_INGREDIENTS + ")";

        DatabaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            myHelperContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            myDatabase = db;
            myDatabase.execSQL(FTS_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + FTS_VIRTUAL_TABLE);
            onCreate(db);
        }
    }

}
