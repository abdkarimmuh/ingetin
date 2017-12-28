package dev.karim.ingetin.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import dev.karim.ingetin.Adapter.AdapterLainnya;
import dev.karim.ingetin.AddEdit.AddLainnyaActivity;
import dev.karim.ingetin.AddEdit.EditLainnyaActivity;
import dev.karim.ingetin.Model.LainnyaModel;
import dev.karim.ingetin.R;
import dev.karim.ingetin.RealmHelper;

/**
 * Created by Karim on 11/17/2017.
 */

public class LainnyaFragment extends Fragment {
    private static final String TAG = "OrganisasiFragment";


    private RecyclerView recyclerView;
    private RealmHelper helper;
    private ArrayList<LainnyaModel> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lainnya, container, false);

        data = new ArrayList<>();
        helper = new RealmHelper(getContext());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvLainnya);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setRecyclerView();

        return rootView;
    }

    /**
     * set recyclerview with try get data from realm
     */
    private void setRecyclerView() {
        try {
            data = helper.findAllLainnya();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdapterLainnya adapterLainnya = new AdapterLainnya(data, new AdapterLainnya.OnItemClickListener() {
            @Override
            public void onClick(LainnyaModel item) {
                Intent i = new Intent(getContext(), EditLainnyaActivity.class);
                i.putExtra("id", item.getId());
                i.putExtra("judul", item.getJudul());
                i.putExtra("deadline", item.getDeadline());
                i.putExtra("deskripsi", item.getDeskripsi());
                i.putExtra("done", item.getDone());
                startActivity(i);
            }
        });
        recyclerView.setAdapter(adapterLainnya);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            data = helper.findAllLainnya();
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
                Toast.makeText(getContext(), "Lainnya", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this.getContext(), AddLainnyaActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
