package com.app.umariana.testvelocidaddatosestructurados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by guerr on 06/02/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "empresa.sqlite";
    public static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + DbManager.TABLE_NAME);
        onCreate(db);
    }
}
