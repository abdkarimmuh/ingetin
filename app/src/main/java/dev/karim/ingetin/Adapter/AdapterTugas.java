package dev.karim.ingetin.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import dev.karim.ingetin.MainActivity;
import dev.karim.ingetin.Model.TugasModel;
import dev.karim.ingetin.R;
import dev.karim.ingetin.RealmHelper;

/**
 * Created by Karim on 11/17/2017.
 */

public class AdapterTugas extends RecyclerView.Adapter<AdapterTugas.ViewHolder> {
    private final OnItemClickListener listener;
    private ArrayList<TugasModel> tugasModels;

    public AdapterTugas(ArrayList<TugasModel> tugasModels, OnItemClickListener listener){
        this.tugasModels = tugasModels;
        this.listener = listener;
    }

    @Override
    public AdapterTugas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tugas, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterTugas.ViewHolder holder, final int position) {
        holder.click(tugasModels.get(position), listener);
        holder.txt_judul.setText(tugasModels.get(position).getJudul());
        holder.txt_jenis.setText(tugasModels.get(position).getJenis());
        holder.txt_deadline.setText(tugasModels.get(position).getDeadline());

        try {
            if (tugasModels.get(position).getDone().equals("yes")){
                holder.switch_done.setChecked(true);
            } else {
                holder.switch_done.setChecked(false);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        holder.switch_done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    tugasModels.get(position).setDone("yes");
                    Log.d("You are :", "Checked");
                } else {
                    tugasModels.get(position).setDone("no");
                    Log.d("You are :", " Not Checked");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return tugasModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_judul, txt_jenis, txt_deadline;
        Switch switch_done;


        public ViewHolder(View itemView) {
            super(itemView);
            txt_judul = (TextView) itemView.findViewById(R.id.txt_judul);
            txt_jenis = (TextView) itemView.findViewById(R.id.txt_jenis);
            txt_deadline = (TextView) itemView.findViewById(R.id.txt_deadline);
            switch_done = (Switch) itemView.findViewById(R.id.switch_done);
        }

        public void click(final TugasModel tugasModel, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(tugasModel);
                }
            });
        }
    }


    public interface OnItemClickListener {
        void onClick(TugasModel item);
    }
}
