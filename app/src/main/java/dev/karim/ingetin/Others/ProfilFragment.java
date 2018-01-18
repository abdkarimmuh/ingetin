package dev.karim.ingetin.Others;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import dev.karim.ingetin.LoginActivity;
import dev.karim.ingetin.Model.ProfilModel;
import dev.karim.ingetin.R;
import dev.karim.ingetin.RealmHelper;

/**
 * Created by Karim on 11/17/2017.
 */

public class ProfilFragment extends Fragment {

    TextView txt_nama, txt_email, txt_instansi;
    Button btn_edit_profil;

    private RealmHelper realmHelper;

    private ArrayList<ProfilModel> profilModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profil, container, false);

        txt_nama = (TextView) rootView.findViewById(R.id.txt_nama);
        txt_email = (TextView) rootView.findViewById(R.id.txt_email);
        txt_instansi = (TextView) rootView.findViewById(R.id.txt_instansi);
        btn_edit_profil = (Button) rootView.findViewById(R.id.btn_edit_profil);

        try {
            realmHelper = new RealmHelper(ProfilFragment.super.getContext());

            profilModels = realmHelper.findAllProfil();
            txt_nama.setText(profilModels.get(0).getNama());
            txt_email.setText(profilModels.get(0).getEmail());
            txt_instansi.setText(profilModels.get(0).getInstansi());
        } catch (Exception e) {
            e.printStackTrace();
        }

        btn_edit_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), LoginActivity.class);
//                startActivity(intent);

                EditProfilFragment editProfilFragment = new EditProfilFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_profil, editProfilFragment);
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }
}
