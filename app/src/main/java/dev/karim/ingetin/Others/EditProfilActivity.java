package dev.karim.ingetin.Others;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import dev.karim.ingetin.MainActivity;
import dev.karim.ingetin.Model.ProfilModel;
import dev.karim.ingetin.R;
import dev.karim.ingetin.RealmHelper;

public class EditProfilActivity extends AppCompatActivity {

    private static final String TAG = "Edit Profil Activity";

    private RealmHelper realmHelper;

    private ArrayList<ProfilModel> profilModels = new ArrayList<>();

    private EditText edit_text_nama, edit_text_email, edit_text_instansi;
    private Button btn_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        realmHelper = new RealmHelper(EditProfilActivity.this);

        edit_text_nama = (EditText) findViewById(R.id.edit_text_nama);
        edit_text_email = (EditText) findViewById(R.id.edit_text_email);
        edit_text_instansi = (EditText) findViewById(R.id.edit_text_instansi);
        btn_save = (Button) findViewById(R.id.btn_save);

        try {
            profilModels = realmHelper.findAllProfil();
            if (!realmHelper.findAllProfil().isEmpty()){
                edit_text_nama.setText(profilModels.get(0).getNama());
                edit_text_email.setText(profilModels.get(0).getEmail());
                edit_text_instansi.setText(profilModels.get(0).getInstansi());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = edit_text_nama.getText().toString();
                String email = edit_text_email.getText().toString();
                String instansi = edit_text_instansi.getText().toString();

                if (realmHelper.findAllProfil().isEmpty()){
                    realmHelper.addProfil(nama, email, instansi);
                } else {
                    realmHelper.updateProfil(profilModels.get(0).getId(), nama, email, instansi);
                }

                Toast.makeText(getApplicationContext(), "Profil Disimpan" + " " + realmHelper.findAllProfil().toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditProfilActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
