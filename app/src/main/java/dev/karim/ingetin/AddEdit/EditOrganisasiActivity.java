package dev.karim.ingetin.AddEdit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;

import dev.karim.ingetin.MainActivity;
import dev.karim.ingetin.Model.OrganisasiModel;
import dev.karim.ingetin.R;
import dev.karim.ingetin.RealmHelper;

/**
 * Created by Karim on 12/28/2017.
 */

public class EditOrganisasiActivity extends AppCompatActivity {

    private int position;
    private Button btn_save, btn_delete;
    private EditText edit_text_judul, edit_text_jenis, edit_text_deadline, edit_text_deskripsi;
    private Switch switch_done;
    private Spinner spinner_option_organisasi;

    private String judul, jenis, deadline, deskripsi, option, sebagai, tugas, presensi, notulensi, check;

    private RealmHelper helper;
    private ArrayList<OrganisasiModel> data;
    private EditText edit_text_sebagai, edit_text_tugas, edit_text_presensi, edit_text_notulensi;
    private TextInputLayout input_layout_tugas, input_layout_sebagai, input_layout_presensi, input_layout_notulensi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_organisasi);

        helper = new RealmHelper(EditOrganisasiActivity.this);
        data = new ArrayList<>();

        //get Intent
        position = getIntent().getIntExtra("id", 0);
        judul = getIntent().getStringExtra("judul");
        jenis = getIntent().getStringExtra("jenis");
        deadline = getIntent().getStringExtra("deadline");
        option = getIntent().getStringExtra("option");
        sebagai = getIntent().getStringExtra("sebagai");
        tugas = getIntent().getStringExtra("tugas");
        notulensi = getIntent().getStringExtra("notulensi");
        deskripsi = getIntent().getStringExtra("deskripsi");
        presensi = getIntent().getStringExtra("presensi");
        check = getIntent().getStringExtra("done");


        //parsing
        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_save = (Button)findViewById(R.id.btn_save);

        edit_text_judul = (EditText) findViewById(R.id.edit_text_judul);
        edit_text_jenis = (EditText) findViewById(R.id.edit_text_jenis);
        edit_text_deadline = (EditText) findViewById(R.id.edit_text_deadline);
        edit_text_deskripsi = (EditText) findViewById(R.id.edit_text_deskripsi);

        edit_text_sebagai = (EditText) findViewById(R.id.edit_text_sebagai);
        edit_text_tugas = (EditText) findViewById(R.id.edit_text_tugas);
        edit_text_presensi = (EditText) findViewById(R.id.edit_text_presensi);
        edit_text_notulensi = (EditText) findViewById(R.id.edit_text_notulensi);
        switch_done = (Switch) findViewById(R.id.switch_done);

        spinner_option_organisasi = (Spinner) findViewById(R.id.spinner_option_organisasi);

        input_layout_tugas = (TextInputLayout) findViewById(R.id.input_layout_tugas);
        input_layout_sebagai = (TextInputLayout) findViewById(R.id.input_layout_sebagai);
        input_layout_presensi = (TextInputLayout) findViewById(R.id.input_layout_presensi);
        input_layout_notulensi = (TextInputLayout) findViewById(R.id.input_layout_notulensi);

        //set Text
        edit_text_judul.setText(judul);
        edit_text_jenis.setText(jenis);
        edit_text_deadline.setText(deadline);
        edit_text_deskripsi.setText(deskripsi);

        try {
            if (option.equals("Event")) {
                spinner_option_organisasi.setSelection(0);
            } else if (option.equals("Meeting")) {
                spinner_option_organisasi.setSelection(1);
            } else {
                spinner_option_organisasi.setSelection(2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        edit_text_sebagai.setText(sebagai);
        edit_text_tugas.setText(tugas);
        edit_text_presensi.setText(presensi);
        edit_text_notulensi.setText(notulensi);

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

        try {
            if (check.equals("yes")){
                switch_done.setChecked(true);
            } else {
                switch_done.setChecked(false);
            }
        } catch (Exception e){
            e.printStackTrace();
        }


        btn_delete.setVisibility(View.VISIBLE);

        //perintah untuk mengapus
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //menghapus data dari database
                helper.deleteOrganisasi(position);

                startActivity(new Intent(EditOrganisasiActivity.this, MainActivity.class));
                finish();
            }
        });

        //untuk update
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judul = edit_text_judul.getText().toString();
                jenis = edit_text_jenis.getText().toString();
                deadline = edit_text_deadline.getText().toString();
                deskripsi = edit_text_deskripsi.getText().toString();
                option = spinner_option_organisasi.getSelectedItem().toString();
                sebagai = edit_text_sebagai.getText().toString();
                tugas = edit_text_tugas.getText().toString();
                presensi = edit_text_presensi.getText().toString();
                notulensi = edit_text_notulensi.getText().toString();
                if (switch_done.isChecked()) {
                    check = "yes";
                } else {
                    check = "false";
                }

                //update
                helper.updateOrganisasi(position, judul, jenis, deadline, deskripsi, option, sebagai, tugas, presensi, notulensi, check);

                //pindah activity
                startActivity(new Intent(EditOrganisasiActivity.this, MainActivity.class));
            }
        });

    }
}
