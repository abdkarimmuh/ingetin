package dev.karim.ingetin;

import android.provider.BaseColumns;

/**
 * Created by Karim on 11/18/2017.
 */

public final class DBContract {
    private DBContract(){

    }

//    final static String COLUMN_ID = "id";
//    final static String COLUMN_JUDUL = "judul";
//    final static String COLUMN_JENIS = "jenis";
//    final static String COLUMN_DESKRIPSI = "deskripsi";
//    final static String COLUMN_DEADLINE = "deadline";
//    final static String COLUMN_CREATE = "create";
//    final static String COLUMN_NOTULENSI = "notulensi";
//    final static String COLUMN_PRESENSI = "presensi";
//    final static String COLUMN_DONE = "done";

    public static class Tugas implements BaseColumns{
        public static final String TABLE_NAME_TUGAS = "table_tugas";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_JUDUL = "judul";
        public static final String COLUMN_JENIS = "jenis";
        public static final String COLUMN_DESKRIPSI = "deskripsi";
        public static final String COLUMN_DEADLINE = "deadline";
        public static final String COLUMN_CREATE = "create";
        public static final String COLUMN_DONE = "done";

        public static final String CREATE_TABLE_TUGAS = "CREATE TABLE " +
                TABLE_NAME_TUGAS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_JUDUL + " TEXT, " +
                COLUMN_JENIS + " TEXT, " +
                COLUMN_DESKRIPSI + " TEXT, " +
                COLUMN_DEADLINE + " TEXT, " +
                COLUMN_CREATE + " TEXT, " +
                COLUMN_DONE + " TEXT" + ")";
    }
}
