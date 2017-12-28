package dev.karim.ingetin.Others;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import dev.karim.ingetin.R;

/**
 * Created by Karim on 11/17/2017.
 */

public class PengaturanFragment extends Fragment {

    RelativeLayout btn_pengaturan_peringatan, btn_pengaturan_user, btn_pengaturan_alarm, btn_pengaturan_tema;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pengaturan, container, false);

        btn_pengaturan_peringatan = (RelativeLayout) rootView.findViewById(R.id.btn_pengaturan_peringatan);
        btn_pengaturan_user = (RelativeLayout) rootView.findViewById(R.id.btn_pengaturan_user);
        btn_pengaturan_alarm = (RelativeLayout) rootView.findViewById(R.id.btn_pengaturan_alarm);
        btn_pengaturan_tema = (RelativeLayout) rootView.findViewById(R.id.btn_pengaturan_tema);

        btn_pengaturan_peringatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PeringatanActivity.class);
                startActivity(intent);
            }
        });

        btn_pengaturan_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditUserActivity.class);
                startActivity(intent);
            }
        });

        btn_pengaturan_tema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Tema", Toast.LENGTH_SHORT).show();
            }
        });

        btn_pengaturan_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Alarm", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
