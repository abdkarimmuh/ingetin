package dev.karim.ingetin.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import dev.karim.ingetin.Model.OrganisasiModel;
import dev.karim.ingetin.R;

/**
 * Created by Karim on 12/28/2017.
 */

public class AdapterOrganisasi extends RecyclerView.Adapter<AdapterOrganisasi.ViewHolder> {

    private final OnItemClickListener listener;
    private ArrayList<OrganisasiModel> organisasiModels;

    public AdapterOrganisasi(ArrayList<OrganisasiModel> organisasiModels, OnItemClickListener listener){
        this.organisasiModels = organisasiModels;
        this.listener = listener;
    }

    @Override
    public AdapterOrganisasi.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_organisasi, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterOrganisasi.ViewHolder holder, final int position) {
        holder.click(organisasiModels.get(position), listener);
        holder.txt_judul.setText(organisasiModels.get(position).getJudul());
        holder.txt_jenis.setText(organisasiModels.get(position).getJenis());
        holder.txt_deadline.setText(organisasiModels.get(position).getDeadline());

        try {
            if (organisasiModels.get(position).getDone().equals("yes")){
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
                    organisasiModels.get(position).setDone("yes");
                    Log.d("You are :", "Checked");
                }
                else {
                    organisasiModels.get(position).setDone("no");
                    Log.d("You are :", " Not Checked");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return organisasiModels.size();
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

        public void click(final OrganisasiModel organisasiModel, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(organisasiModel);
                }
            });
        }
    }


    public interface OnItemClickListener {
        void onClick(OrganisasiModel item);
    }

}
