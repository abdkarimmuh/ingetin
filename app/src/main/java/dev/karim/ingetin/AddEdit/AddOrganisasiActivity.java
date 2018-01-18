package dev.karim.ingetin.AddEdit;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dev.karim.ingetin.MainActivity;
import dev.karim.ingetin.R;
import dev.karim.ingetin.RealmHelper;

public class AddOrganisasiActivity extends AppCompatActivity {

    private static final String TAG = "Add Organisasi Activity";

    private RealmHelper realmHelper;
    private EditText edit_text_judul, edit_text_jenis, edit_text_deadline, edit_text_deskripsi;
    private String check;
    private Switch switch_done;
    private Button btn_save;
    private Spinner spinner_option_organisasi;
    private EditText edit_text_sebagai, edit_text_tugas,  edit_text_presensi, edit_text_notulensi;
    private TextInputLayout input_layout_tugas, input_layout_sebagai, input_layout_presensi, input_layout_notulensi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_organisasi);

        realmHelper = new RealmHelper(AddOrganisasiActivity.this);

        edit_text_judul = (EditText) findViewById(R.id.edit_text_judul);
        edit_text_jenis = (EditText) findViewById(R.id.edit_text_jenis);
        edit_text_deadline = (EditText) findViewById(R.id.edit_text_deadline);
        edit_text_deskripsi = (EditText) findViewById(R.id.edit_text_deskripsi);
        edit_text_sebagai = (EditText) findViewById(R.id.edit_text_sebagai);
        edit_text_tugas = (EditText) findViewById(R.id.edit_text_tugas);
        edit_text_presensi = (EditText) findViewById(R.id.edit_text_presensi);
        edit_text_notulensi = (EditText) findViewById(R.id.edit_text_notulensi);

        spinner_option_organisasi = (Spinner) findViewById(R.id.spinner_option_organisasi);

        input_layout_tugas = (TextInputLayout) findViewById(R.id.input_layout_tugas);
        input_layout_sebagai = (TextInputLayout) findViewById(R.id.input_layout_sebagai);
        input_layout_presensi = (TextInputLayout) findViewById(R.id.input_layout_presensi);
        input_layout_notulensi = (TextInputLayout) findViewById(R.id.input_layout_notulensi);

        switch_done = (Switch) findViewById(R.id.switch_done);

        btn_save = (Button) findViewById(R.id.btn_save);

        spinner_option_organisasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                try {
                    if (position == 0){
                        input_layout_sebagai.setVisibility(View.VISIBLE);
                        input_layout_presensi.setVisibility(View.GONE);
                        input_layout_notulensi.setVisibility(View.GONE);
                        input_layout_tugas.setVisibility(View.GONE);
                    } else if (position == 1){
                        input_layout_sebagai.setVisibility(View.GONE);
                        input_layout_presensi.setVisibility(View.VISIBLE);
                        input_layout_notulensi.setVisibility(View.VISIBLE);
                        input_layout_tugas.setVisibility(View.GONE);
                    } else {
                        input_layout_sebagai.setVisibility(View.GONE);
                        input_layout_presensi.setVisibility(View.GONE);
                        input_layout_notulensi.setVisibility(View.GONE);
                        input_layout_tugas.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inisialisasi string
                String judul, jenis, deadline, deskripsi, option, sebagai, tugas, presensi, notulensi, done;

                //jika swich di pilih
                if (switch_done.isChecked()){
                    check = "yes";
                } else {
                    check = "no";
                }

                //mengambil text dr edittext
                judul = edit_text_judul.getText().toString();
                jenis = edit_text_jenis.getText().toString();
                deadline = edit_text_deadline.getText().toString();
                deskripsi = edit_text_deskripsi.getText().toString();
                option = spinner_option_organisasi.getSelectedItem().toString();
                sebagai = edit_text_sebagai.getText().toString();
                tugas = edit_text_tugas.getText().toString();
                presensi = edit_text_presensi.getText().toString();
                notulensi = edit_text_notulensi.getText().toString();
                done = check;

                if(!judul.equals("")){
                    try {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                                deadline));

                        //menambahkan data pada database
                        realmHelper.addOrganisasi(judul, jenis, deadline, deskripsi, option, sebagai, tugas, presensi, notulensi, done);

                        Log.d("Add Organisasi", "Check : " + done);

                        //kembali ke MainActivity
                        Intent i = new Intent(AddOrganisasiActivity.this, MainActivity.class);
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
