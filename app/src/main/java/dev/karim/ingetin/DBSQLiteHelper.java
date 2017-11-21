package dev.karim.ingetin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Karim on 11/18/2017.
 */

public class DBSQLiteHelper extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "ingetin.db";
    final static int DATABASE_VERSION = 1;

    public DBSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.Tugas.CREATE_TABLE_TUGAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.Tugas.TABLE_NAME_TUGAS);
        onCreate(db);
    }
}
