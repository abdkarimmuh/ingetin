package dev.karim.ingetin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import dev.karim.ingetin.R;
import dev.karim.ingetin.Holder.HolderTugas;
import dev.karim.ingetin.ItemObject.ItemObjectTugas;

/**
 * Created by Karim on 11/17/2017.
 */

public class AdapterTugas extends RecyclerView.Adapter<HolderTugas> {

    List<ItemObjectTugas> itemObjectTugasList;
    Context context;

    public AdapterTugas(Context context, List<ItemObjectTugas> itemObjectTugasList){
        this.context = context;
        this.itemObjectTugasList = itemObjectTugasList;
    }

    @Override
    public HolderTugas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tugas, null);
        HolderTugas holderTugas = new HolderTugas(view);
        return holderTugas;
    }

    @Override
    public void onBindViewHolder(HolderTugas holder, final int position) {
        holder.txt_judul.setText(itemObjectTugasList.get(position).judul);
        holder.txt_jenis.setText(itemObjectTugasList.get(position).jenis);
        holder.txt_deadline.setText(itemObjectTugasList.get(position).deadline);

        holder.cardview_tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, itemObjectTugasList.get(position).judul, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemObjectTugasList.size();
    }
}
