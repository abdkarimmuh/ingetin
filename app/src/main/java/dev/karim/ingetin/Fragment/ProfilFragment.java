package dev.karim.ingetin.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.karim.ingetin.R;

/**
 * Created by Karim on 11/17/2017.
 */

public class ProfilFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profil, container, false);
        return rootView;
    }
}
