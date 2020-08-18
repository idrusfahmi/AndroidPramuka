package com.akagami.absensipramuka.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.akagami.absensipramuka.R;

import java.util.ArrayList;

public class IuranAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<IuranAdapterItems> listIuranAdapter;

    public IuranAdapter(ArrayList<IuranAdapterItems> listIuranAdapter, Context context) {
        this.listIuranAdapter = listIuranAdapter;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listIuranAdapter.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("fahmi", "bbb");
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_view_iuran_siswa, parent, false);
            final IuranAdapterItems items = listIuranAdapter.get(position);

            TextView bulan = convertView.findViewById(R.id.bulan);
            bulan.setText(items.getBulan());

            TextView iuran_minggu1 = convertView.findViewById(R.id.iuran_minggu1);
            iuran_minggu1.setText(items.getIuran_minggu1());

            TextView iuran_minggu2 = convertView.findViewById(R.id.iuran_minggu2);
            iuran_minggu2.setText(items.getIuran_minggu2());

            TextView iuran_minggu3 = convertView.findViewById(R.id.iuran_minggu3);
            iuran_minggu3.setText(items.getIuran_minggu3());

            TextView iuran_minggu4 = convertView.findViewById(R.id.iuran_minggu4);
            iuran_minggu4.setText(items.getIuran_minggu4());

            TextView iuran_minggu5 = convertView.findViewById(R.id.iuran_minggu5);
            iuran_minggu5.setText(items.getIuran_minggu5());

            Log.i("fahmi", "aaa");
        }
        Log.i("fahmi", "ccc");
        return convertView;

    }
}
