package dev.karim.ingetin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Karim on 11/17/2017.
 */

public class SQLiteHandler extends SQLiteOpenHelper {

    final static String DBNAME = "ingetin.db";
    final static int DBVERSION = 1;

    public SQLiteHandler(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_create_tugas = "CREATE TABLE tugas(" +
                "_id INTEGER PRIMARY KEY autoincrement,"+
                "judul TEXT," +
                "jenis TEXT," +
                "deskripsi TEXT," +
                "deadline DATE," +
                "create_date DATE," +
                "done BOOLEAN)";
        String query_create_organisasi = "CREATE TABLE organisasi(" +
                "_id INTEGER PRIMARY KEY autoincrement,"+
                "judul TEXT," +
                "jenis TEXT," +
                "deskripsi TEXT," +
                "deadline DATE," +
                "create_date DATE," +
                "done BOOLEAN," +
                "presensi TEXT," +
                "notulensi TEXT)";
        String query_create_lainnya = "CREATE TABLE lainnya(" +
                "_id INTEGER PRIMARY KEY autoincrement,"+
                "judul TEXT," +
                "deskripsi TEXT," +
                "deadline DATE," +
                "create_date DATE," +
                "done BOOLEAN)";
        db.execSQL(query_create_tugas);
        db.execSQL(query_create_organisasi);
        db.execSQL(query_create_lainnya);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query_drop = "KOSONG";
        db.execSQL(query_drop);
        onCreate(db);
    }
}
