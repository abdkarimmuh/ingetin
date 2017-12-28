package dev.karim.ingetin.AddEdit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dev.karim.ingetin.MainActivity;
import dev.karim.ingetin.R;
import dev.karim.ingetin.RealmHelper;

public class AddLainnyaActivity extends AppCompatActivity {

    private static final String TAG = "Add Lainnya Activity";

    private RealmHelper realmHelper;
    private EditText edit_text_judul, edit_text_deadline, edit_text_deskripsi;
    private String check;
    private Switch switch_done;
    private Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_lainnya);

        realmHelper = new RealmHelper(AddLainnyaActivity.this);
        edit_text_judul = (EditText) findViewById(R.id.edit_text_judul);
        edit_text_deadline = (EditText) findViewById(R.id.edit_text_deadline);
        edit_text_deskripsi = (EditText) findViewById(R.id.edit_text_deskripsi);
        switch_done = (Switch) findViewById(R.id.switch_done);

        btn_save = (Button) findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inisialisasi string
                String judul, deadline, deskripsi, done;

                //jika swich di pilih
                if (switch_done.isChecked()){
                    check = "yes";
                } else {
                    check = "no";
                }

                //mengambil text dr edittext
                judul = edit_text_judul.getText().toString();
                deadline = edit_text_deadline.getText().toString();
                deskripsi = edit_text_deskripsi.getText().toString();
                done = check;

                if(!judul.equals("")){
                    try {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                                deadline));

                        //menambahkan data pada database
                        realmHelper.addLainnya(judul, deadline, deskripsi, done);

                        Log.d("Add Lainnya", "Check : " + done);

                        //kembali ke MainActivity
                        Intent i = new Intent(AddLainnyaActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                    catch (Exception e) {
                        Log.e(TAG, "Error", e);
                        Toast.makeText(getApplicationContext(), "Date is in the wrong format", Toast.LENGTH_LONG).show();
                        return;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Maaf Judul Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
