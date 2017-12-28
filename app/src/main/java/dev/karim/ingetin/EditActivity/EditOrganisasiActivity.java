package dev.karim.ingetin.EditActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText edit_text_judul, edit_text_jenis, edit_text_deadline, edit_text_deskripsi, edit_text_presensi, edit_text_notulensi;
    private Switch switch_done;

    private String judul, jenis, deadline, deskripsi, presensi, notulensi, check;

    private RealmHelper helper;
    private ArrayList<OrganisasiModel> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_organisasi);

        helper = new RealmHelper(EditOrganisasiActivity.this);
        data = new ArrayList<>();
        position = getIntent().getIntExtra("id", 0);

        judul = getIntent().getStringExtra("judul");
        jenis = getIntent().getStringExtra("jenis");
        deadline = getIntent().getStringExtra("deadline");
        notulensi = getIntent().getStringExtra("notulensi");
        deskripsi = getIntent().getStringExtra("deskripsi");
        presensi = getIntent().getStringExtra("presensi");
        check = getIntent().getStringExtra("done");

        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_save = (Button)findViewById(R.id.btn_save);

        edit_text_judul = (EditText) findViewById(R.id.edit_text_judul);
        edit_text_jenis = (EditText) findViewById(R.id.edit_text_jenis);
        edit_text_deadline = (EditText) findViewById(R.id.edit_text_deadline);
        edit_text_deskripsi = (EditText) findViewById(R.id.edit_text_deskripsi);
        edit_text_presensi = (EditText) findViewById(R.id.edit_text_presensi);
        edit_text_notulensi = (EditText) findViewById(R.id.edit_text_notulensi);
        switch_done = (Switch) findViewById(R.id.switch_done);

        edit_text_judul.setText(judul);
        edit_text_jenis.setText(jenis);
        edit_text_deadline.setText(deadline);
        edit_text_deskripsi.setText(deskripsi);
        edit_text_presensi.setText(presensi);
        edit_text_notulensi.setText(notulensi);

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
                presensi = edit_text_presensi.getText().toString();
                notulensi = edit_text_notulensi.getText().toString();
                if (switch_done.isChecked()) {
                    check = "yes";
                } else {
                    check = "false";
                }

                //update
                helper.updateOrganisasi(position, judul, jenis, deadline, deskripsi, presensi, notulensi, check);

                //pindah activity
                startActivity(new Intent(EditOrganisasiActivity.this, MainActivity.class));
            }
        });

    }
}
