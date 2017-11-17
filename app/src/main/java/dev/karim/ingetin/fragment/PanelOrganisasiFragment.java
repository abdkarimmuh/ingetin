package dev.karim.ingetin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.karim.ingetin.R;

/**
 * Created by Karim on 11/17/2017.
 */

public class PanelOrganisasiFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_panel_organisasi, container, false);
        return rootView;
    }
}
