package dev.karim.ingetin.Others;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dev.karim.ingetin.R;

/**
 * Created by Karim on 11/17/2017.
 */

public class ProfilFragment extends Fragment {

    TextView txt_nama, txt_email, txt_instansi, txt_edit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profil, container, false);

        txt_nama = (TextView) rootView.findViewById(R.id.txt_nama);
        txt_email = (TextView) rootView.findViewById(R.id.txt_email);
        txt_instansi = (TextView) rootView.findViewById(R.id.txt_instansi);
        txt_edit = (TextView) rootView.findViewById(R.id.txt_edit);

        txt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditUserActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
