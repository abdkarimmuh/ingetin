package dev.karim.ingetin.AddEdit;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.ArrayList;

import dev.karim.ingetin.MainActivity;
import dev.karim.ingetin.Model.TugasModel;
import dev.karim.ingetin.R;
import dev.karim.ingetin.RealmHelper;

public class EditTugasActivity extends AppCompatActivity {

    private int position;
    private Button btn_save, btn_delete;
    private EditText edit_text_judul, edit_text_jenis, edit_text_deadline, edit_text_deskripsi;
    private Switch switch_done;

    private String judul, jenis, deadline, deskripsi, check;

    private RealmHelper helper;
    private ArrayList<TugasModel> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_tugas);

        helper = new RealmHelper(EditTugasActivity.this);
        data = new ArrayList<>();
        position = getIntent().getIntExtra("id", 0);

        judul = getIntent().getStringExtra("judul");
        jenis = getIntent().getStringExtra("jenis");
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

        edit_text_judul.setText(judul);
        edit_text_jenis.setText(jenis);
        edit_text_deadline.setText(deadline);
        edit_text_deskripsi.setText(deskripsi);

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
                helper.deleteTugas(position);

                startActivity(new Intent(EditTugasActivity.this, MainActivity.class));
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
                if (switch_done.isChecked()) {
                    check = "yes";
                } else {
                    check = "false";
                }

                //update
                helper.updateTugas(position, judul, jenis, deadline, deskripsi, check);

                //pindah activity
                startActivity(new Intent(EditTugasActivity.this, MainActivity.class));
            }
        });

    }
}
