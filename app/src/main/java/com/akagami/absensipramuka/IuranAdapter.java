package com.akagami.absensipramuka;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IuranAdapter extends RecyclerView.Adapter<IuranAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<String> array_id_siswa, array_nama_siswa;
    ProgressDialog progressDialog;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id_siswa, nama_siswa;
        public CardView cv_main;

        public MyViewHolder(View view) {
            super(view);
            cv_main = itemView.findViewById(R.id.cv_main);
            id_siswa = itemView.findViewById(R.id.id_siswa);
            nama_siswa = itemView.findViewById(R.id.nama_siswa);

            progressDialog = new ProgressDialog(mContext);
        }
    }

    public IuranAdapter(Context mContext, ArrayList<String> array_id_siswa, ArrayList<String> array_nama_siswa) {
        super();
        this.mContext = mContext;
        this.array_id_siswa = array_id_siswa;
        this.array_nama_siswa = array_nama_siswa;
    }

    @NonNull
    @Override
    public IuranAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.template_name, parent, false);
        return new IuranAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IuranAdapter.MyViewHolder holder, final int position) {
        holder.id_siswa.setText(array_id_siswa.get(position));
        holder.nama_siswa.setText(array_nama_siswa.get(position));
        holder.cv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id_siswa",array_id_siswa.get(position));
                bundle.putString("nama_siswa",array_nama_siswa.get(position));
                Intent i = new Intent(mContext, Iuran.class);
                i.putExtras(bundle);
                ((SiswaIuran) mContext).startActivityForResult(i, 2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array_id_siswa.size();
    }

}
