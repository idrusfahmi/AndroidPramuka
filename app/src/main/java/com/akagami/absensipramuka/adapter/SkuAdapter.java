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

public class SkuAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SkuAdapterItems> listSkuAdapter;

    public SkuAdapter(ArrayList<SkuAdapterItems> listSkuAdapter, Context context) {
        this.listSkuAdapter = listSkuAdapter;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listSkuAdapter.size();
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
            convertView = layoutInflater.inflate(R.layout.list_view_sku_siswa, parent, false);
            final SkuAdapterItems items = listSkuAdapter.get(position);

            TextView nomor = convertView.findViewById(R.id.nomor);
            nomor.setText(items.getNomor());

            TextView judul_test = convertView.findViewById(R.id.judul_test);
            judul_test.setText(items.getJudul_test());

            TextView hasil = convertView.findViewById(R.id.hasil);
            hasil.setText(items.getHasil());

            TextView penguji = convertView.findViewById(R.id.penguji);
            penguji.setText(items.getPenguji());

            Log.i("fahmi", "aaa");
        }
        Log.i("fahmi", "ccc");
        return convertView;

    }
}