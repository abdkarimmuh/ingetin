package dev.karim.ingetin.AddEdit;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;

import dev.karim.ingetin.MainActivity;
import dev.karim.ingetin.Model.AkademikModel;
import dev.karim.ingetin.R;
import dev.karim.ingetin.RealmHelper;

public class EditAkademikActivity extends AppCompatActivity {

    private int position;
    private Button btn_save, btn_delete;
    private EditText edit_text_judul, edit_text_jenis, edit_text_deadline, edit_text_deskripsi;
    private Switch switch_done;
    private Spinner spinner_option_akademik;

    private String judul, jenis, deadline, deskripsi, check, option;

    private RealmHelper helper;
    private ArrayList<AkademikModel> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_akademik);

        helper = new RealmHelper(EditAkademikActivity.this);
        data = new ArrayList<>();
        position = getIntent().getIntExtra("id", 0);

        judul = getIntent().getStringExtra("judul");
        jenis = getIntent().getStringExtra("jenis");
        option = getIntent().getStringExtra("option");
        deadline = getIntent().getStringExtra("deadline");
        deskripsi = getIntent().getStringExtra("deskripsi");
        check = getIntent().getStringExtra("done");

        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_save = (Button)findViewById(R.id.btn_save);

        edit_text_judul = (EditText) findViewById(R.id.edit_text_judul);
        edit_text_jenis = (EditText) findViewById(R.id.edit_text_jenis);
        edit_text_deadline = (EditText) findViewById(R.id.edit_text_deadline);
        edit_text_deskripsi = (EditText) findViewById(R.id.edit_text_deskripsi);
        switch_done = (Switch) findViewById(R.id.switch_done);
        spinner_option_akademik = (Spinner) findViewById(R.id.spinner_option_akademik);

        edit_text_judul.setText(judul);
        edit_text_jenis.setText(jenis);
        edit_text_deadline.setText(deadline);
        edit_text_deskripsi.setText(deskripsi);

        try {
            if (option.equals("Tugas")) {
                spinner_option_akademik.setSelection(0);
            } else if (option.equals("Kerja Kelompok")) {
                spinner_option_akademik.setSelection(1);
            } else {
                spinner_option_akademik.setSelection(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                helper.deleteAkademmik(position);

                startActivity(new Intent(EditAkademikActivity.this, MainActivity.class));
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
                option = spinner_option_akademik.getSelectedItem().toString();
                deskripsi = edit_text_deskripsi.getText().toString();
                if (switch_done.isChecked()) {
                    check = "yes";
                } else {
                    check = "false";
                }

                //update
                helper.updateAkademik(position, judul, jenis, option, deadline, deskripsi, check);

                Log.v("Tugas : ", option);

                //pindah activity
                startActivity(new Intent(EditAkademikActivity.this, MainActivity.class));
            }
        });

    }
}
