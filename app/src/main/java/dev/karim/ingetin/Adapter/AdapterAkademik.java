package dev.karim.ingetin.Adapter;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import dev.karim.ingetin.Model.AkademikModel;
import dev.karim.ingetin.R;

/**
 * Created by Karim on 11/17/2017.
 */

public class AdapterAkademik extends RecyclerView.Adapter<AdapterAkademik.ViewHolder> {
    private final OnItemClickListener listener;
    private ArrayList<AkademikModel> akademikModels;

    public AdapterAkademik(ArrayList<AkademikModel> akademikModels, OnItemClickListener listener){
        this.akademikModels = akademikModels;
        this.listener = listener;
    }

    @Override
    public AdapterAkademik.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_akademik, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterAkademik.ViewHolder holder, final int position) {
        holder.click(akademikModels.get(position), listener);
        holder.txt_judul.setText(akademikModels.get(position).getJudul());
        holder.txt_jenis.setText(akademikModels.get(position).getJenis());
        holder.txt_deadline.setText(akademikModels.get(position).getDeadline());

        try {
            if (akademikModels.get(position).getDone().equals("yes")){
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
                    akademikModels.get(position).setDone("yes");
                    Log.d("You are :", "Checked");
                } else {
                    akademikModels.get(position).setDone("no");
                    Log.d("You are :", " Not Checked");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return akademikModels.size();
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

        public void click(final AkademikModel akademikModel, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(akademikModel);
                }
            });
        }
    }


    public interface OnItemClickListener {
        void onClick(AkademikModel item);
    }
}
