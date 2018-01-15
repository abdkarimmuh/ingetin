package dev.karim.ingetin.Home;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import dev.karim.ingetin.Adapter.AdapterAkademik;
import dev.karim.ingetin.AddEdit.EditAkademikActivity;
import dev.karim.ingetin.Model.AkademikModel;
import dev.karim.ingetin.R;
import dev.karim.ingetin.AddEdit.AddAkademikActivity;
import dev.karim.ingetin.RealmHelper;

/**
 * Created by Karim on 11/17/2017.
 */

public class AkademikFragment extends Fragment {

    private static final String TAG = "AkademikFragment";


    private RecyclerView recyclerView;
    private RealmHelper helper;
    private ArrayList<AkademikModel> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_akademik, container, false);

        data = new ArrayList<>();
        helper = new RealmHelper(getContext());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvTugas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setRecyclerView();

        return rootView;
    }

    /**
     * set recyclerview with try get data from realm
     */
    private void setRecyclerView() {
        try {
            data = helper.findAllAkademik();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdapterAkademik adapterAkademik = new AdapterAkademik(data, new AdapterAkademik.OnItemClickListener() {
            @Override
            public void onClick(AkademikModel item) {
                Intent i = new Intent(getContext(), EditAkademikActivity.class);
                i.putExtra("id", item.getId());
                i.putExtra("judul", item.getJudul());
                i.putExtra("jenis", item.getJenis());
                i.putExtra("option", item.getOption());
                i.putExtra("deadline", item.getDeadline());
                i.putExtra("deskripsi", item.getDeskripsi());
                i.putExtra("done", item.getDone());
                startActivity(i);
            }
        });
        recyclerView.setAdapter(adapterAkademik);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            data = helper.findAllAkademik();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //data = helper.findAllArticle();
        setRecyclerView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Toast.makeText(getContext(), "Akademik", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this.getContext(), AddAkademikActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
