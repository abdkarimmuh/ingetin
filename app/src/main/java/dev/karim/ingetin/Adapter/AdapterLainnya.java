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

import dev.karim.ingetin.Model.LainnyaModel;
import dev.karim.ingetin.R;

/**
 * Created by Karim on 12/29/2017.
 */

public class AdapterLainnya extends RecyclerView.Adapter<AdapterLainnya.ViewHolder> {

    private final OnItemClickListener listener;
    private ArrayList<LainnyaModel> lainnyaModels;

    public AdapterLainnya(ArrayList<LainnyaModel> lainnyaModels, OnItemClickListener listener){
        this.lainnyaModels = lainnyaModels;
        this.listener = listener;
    }

    @Override
    public AdapterLainnya.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_lainnya, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterLainnya.ViewHolder holder, final int position) {
        holder.click(lainnyaModels.get(position), listener);
        holder.txt_judul.setText(lainnyaModels.get(position).getJudul());
        holder.txt_deadline.setText(lainnyaModels.get(position).getDeadline());

        try {
            if (lainnyaModels.get(position).getDone().equals("yes")){
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
                    lainnyaModels.get(position).setDone("yes");
                    Log.d("You are :", "Checked");
                }
                else {
                    lainnyaModels.get(position).setDone("no");
                    Log.d("You are :", " Not Checked");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return lainnyaModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_judul, txt_deadline;
        Switch switch_done;


        public ViewHolder(View itemView) {
            super(itemView);
            txt_judul = (TextView) itemView.findViewById(R.id.txt_judul);
            txt_deadline = (TextView) itemView.findViewById(R.id.txt_deadline);
            switch_done = (Switch) itemView.findViewById(R.id.switch_done);
        }

        public void click(final LainnyaModel lainnyaModel, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(lainnyaModel);
                }
            });
        }
    }


    public interface OnItemClickListener {
        void onClick(LainnyaModel item);
    }

}
