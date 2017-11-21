package dev.karim.ingetin.AddActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import dev.karim.ingetin.DBContract;
import dev.karim.ingetin.DBSQLiteHelper;
import dev.karim.ingetin.R;

public class AddTugasActivity extends AppCompatActivity {

    private static final String TAG = AddTugasActivity.class.getSimpleName();

    EditText edit_text_judul, edit_text_deskripsi, edit_text_deadline;
    Spinner spinner_text_jenis;
    Switch switch_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tugas);

        ArrayAdapter<String> spinnerCountShoesArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                        getResources().getStringArray(R.array.nav_item_activity_titles));

        edit_text_judul = (EditText) findViewById(R.id.edit_text_judul);
        edit_text_deskripsi = (EditText) findViewById(R.id.edit_text_deskripsi);
        edit_text_deadline = (EditText) findViewById(R.id.edit_text_deadline);
        spinner_text_jenis = (Spinner) findViewById(R.id.spinner_text_jenis);
        switch_done = (Switch) findViewById(R.id.switch_done);

        spinner_text_jenis.setAdapter(spinnerCountShoesArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_check:
                Toast.makeText(getApplicationContext(), "Save", Toast.LENGTH_SHORT).show();
                saveToDB();
                break;

            case R.id.action_delete:
                Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveToDB() {
        SQLiteDatabase database = new DBSQLiteHelper(this).getWritableDatabase();

        if (!CheckData(edit_text_judul.getText().toString())){
            ContentValues values = new ContentValues();
            values.put(DBContract.Tugas.COLUMN_JUDUL, edit_text_judul.getText().toString());
            values.put(DBContract.Tugas.COLUMN_JENIS, spinner_text_jenis.getOnItemSelectedListener().toString());
            values.put(DBContract.Tugas.COLUMN_DONE, switch_done.getText().toString());
            values.put(DBContract.Tugas.COLUMN_DESKRIPSI, edit_text_deskripsi.getText().toString());

            try {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                        edit_text_deadline.getText().toString()));
                long date = calendar.getTimeInMillis();
                values.put(DBContract.Tugas.COLUMN_DEADLINE, date);

                values.put(DBContract.Tugas.COLUMN_CREATE, Calendar.getInstance().getTime().toString());
            } catch (Exception e) {
                Log.e(TAG, "Error", e);
                Toast.makeText(this, "Date is in the wrong format", Toast.LENGTH_LONG).show();
                return;
            }
            database.insert(DBContract.Tugas.TABLE_NAME_TUGAS, null, values);

            Toast.makeText(this, "The new Row Id is " + database.insert(DBContract.Tugas.TABLE_NAME_TUGAS, null, values),
                    Toast.LENGTH_LONG).show();
        } else {
            long date;
            try {
                try {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                            edit_text_deadline.getText().toString()));
                    date = calendar.getTimeInMillis();
                } catch (Exception e) {
                    Log.e(TAG, "Error", e);
                    Toast.makeText(this, "Date is in the wrong format", Toast.LENGTH_LONG).show();
                    return;
                }
                database.beginTransaction();
                database.execSQL("UPDATE " + DBContract.Tugas.TABLE_NAME_TUGAS +
                        " SET " + DBContract.Tugas.COLUMN_JUDUL + "='" + edit_text_judul.getText().toString() +
                        "', " + DBContract.Tugas.COLUMN_JENIS + "='" + spinner_text_jenis.getOnItemSelectedListener().toString() +
                        "', " + DBContract.Tugas.COLUMN_DESKRIPSI + "='" + edit_text_deskripsi.getText().toString() +
                        "', " + DBContract.Tugas.COLUMN_DEADLINE + "='" + date +
                        "' WHERE " + DBContract.Tugas.COLUMN_JUDUL + "='" + edit_text_judul.getText().toString() + "'");
                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }
        }
        database.close();
    }

    public boolean CheckData(String judul) {
        SQLiteDatabase sqldb = new DBSQLiteHelper(this).getWritableDatabase();
        String Query = "SELECT * FROM " + DBContract.Tugas.TABLE_NAME_TUGAS + " WHERE judul='" + judul + "'";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
