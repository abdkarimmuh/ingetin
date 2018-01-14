package dev.karim.ingetin.Others;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dev.karim.ingetin.LoginActivity;
import dev.karim.ingetin.MainActivity;
import dev.karim.ingetin.Model.ProfilModel;
import dev.karim.ingetin.R;
import dev.karim.ingetin.RealmHelper;

/**
 * Created by Karim on 1/14/2018.
 */

public class EditProfilFragment extends Fragment {

    private RealmHelper realmHelper;

    private ArrayList<ProfilModel> profilModels = new ArrayList<>();

    private EditText edit_text_nama, edit_text_email, edit_text_instansi;
    private Button btn_save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_profil, container, false);

        realmHelper = new RealmHelper(EditProfilFragment.super.getContext());

        edit_text_nama = (EditText) rootView.findViewById(R.id.edit_text_nama);
        edit_text_email = (EditText) rootView.findViewById(R.id.edit_text_email);
        edit_text_instansi = (EditText) rootView.findViewById(R.id.edit_text_instansi);
        btn_save = (Button) rootView.findViewById(R.id.btn_save);

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

                Toast.makeText(getContext(), "Profil Disimpan", Toast.LENGTH_SHORT).show();

                ProfilFragment profilFragment = new ProfilFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_edit_profil, profilFragment);
                fragmentTransaction.commit();

//                Intent intent = new Intent(EditProfilFragment.super.getContext(), MainActivity.class);
//                startActivity(intent);
            }
        });

        return rootView;
    }
}

