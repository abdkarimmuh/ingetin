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

    final static String TABLE_TUGAS = "tugas";
    final static String TABLE_AKADEMIK = "akademik";
    final static String TABLE_LAINNYA = "lainnya";


    final static String KEY_ID = "id";
    final static String KEY_JUDUL = "judul";
    final static String KEY_JENIS = "jenis";
    final static String KEY_DESKRIPSI = "deskripsi";
    final static String KEY_DEADLINE = "deadline";
    final static String KEY_CREATE = "create";
    final static String KEY_NOTULENSI = "notulensi";
    final static String KEY_PRESENSI = "presensi";
    final static String KEY_DONE = "done";

    final static String COMA = ",";
    final static String TEXT = "TEXT";
    final static String DATE = "date";
    final static String BIT = "bit";

    public SQLiteHandler(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_create_tugas = "CREATE TABLE " + TABLE_TUGAS + "(" +
                KEY_ID +  "INTEGER PRIMARY KEY autoincrement,"+
                KEY_JUDUL + TEXT + COMA +
                KEY_JENIS + TEXT + COMA +
                KEY_DESKRIPSI + TEXT + COMA +
                KEY_DEADLINE + DATE + COMA +
                KEY_CREATE + DATE + COMA +
                KEY_DONE + BIT + ")";

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
        String query_drop = "DROP TABLE IF EXISTS " + TABLE_TUGAS;
        db.execSQL(query_drop);
        onCreate(db);
    }
}
