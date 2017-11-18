package dev.karim.ingetin.Holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import dev.karim.ingetin.R;

/**
 * Created by Karim on 11/17/2017.
 */

public class HolderTugas extends RecyclerView.ViewHolder {

    public TextView txt_judul, txt_jenis, txt_deadline;
    public CheckBox cb;
    public CardView cardview_tugas;

    public HolderTugas(View itemView) {
        super(itemView);
        txt_judul = (TextView) itemView.findViewById(R.id.txt_judul);
        txt_jenis = (TextView) itemView.findViewById(R.id.txt_jenis);
        txt_deadline = (TextView) itemView.findViewById(R.id.txt_deadline);
        cb = (CheckBox) itemView.findViewById(R.id.cb);
        cardview_tugas = (CardView) itemView.findViewById(R.id.cardview_tugas);
    }
}
