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
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if(listSkuAdapter.size() == 0){
            return 1;
        }
        return listSkuAdapter.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("fahmi", "bbb");
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_view_sku_siswa, parent, false);
            final SkuAdapterItems items = listSkuAdapter.get(position);

//            TextView no_sku = convertView.findViewById(R.id.nomor);
//            no_sku.setText(items.getNo_sku());
            TextView no_sku = convertView.findViewById(R.id.no_sku);
            no_sku.setText(items.getNo_sku());


            TextView judul_test = convertView.findViewById(R.id.judul_test);
            judul_test.setText(items.getJudul_test());

            TextView hasil = convertView.findViewById(R.id.hasil);
            hasil.setText(items.getHasil());

            TextView nama = convertView.findViewById(R.id.nama);
            nama.setText(items.getNama());

            Log.i("fahmi", "aaa");
        }
        Log.i("fahmi", "ccc");
        return convertView;

    }
}

