package com.akagami.absensipramuka.ui.sku;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.akagami.absensipramuka.API;
import com.akagami.absensipramuka.R;
import com.akagami.absensipramuka.adapter.SkuAdapter;
import com.akagami.absensipramuka.adapter.SkuAdapterItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SkuFragment extends Fragment {

    private View view;
    private ListView listView;
    private ArrayList<SkuAdapterItems> listSkuData = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SkuViewModel skuViewModel = ViewModelProviders.of(this).get(SkuViewModel.class);
        return inflater.inflate(R.layout.fragment_sku, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view = v;

        listView = view.findViewById(R.id.lv_sku);
        callApiSku();
    }

    private void callApiSku() {
        listSkuData.clear();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String username = sharedPreferences.getString("username", "empty");

        Call<String> call = api.postSkuSiswa(username);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> res) {
                if (res.isSuccessful() && res.body() != null) {
                    String jsonResponse = res.body();
                    try {
                        JSONObject jsonObject = new JSONObject(jsonResponse);
                        String status = jsonObject.getString("status");
                        if(status.equals("sukses")){
                            try{
                                skuSiswa(jsonObject);
                            } catch (JSONException e){
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getContext(), "Kesalahan Sistem", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getContext(), "Kesalahan Sistem", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void skuSiswa(JSONObject jsonObject) throws JSONException {
        JSONArray data = jsonObject.getJSONArray("data");
        for (int i = 0; i < data.length(); i++) {
            JSONObject object = data.getJSONObject(i);

            String nomor;
            if (object.has("nomor")) {
                nomor = object.getString("nomor");
            }
            else{
                nomor = "-";
            }

            String judul_test;
            if (object.has("judul_test")) {
                judul_test = object.getString("judul_test");
            }
            else{
                judul_test = "-";
            }

            String hasil;
            if (object.has("hasil")) {
                hasil = object.getString("hasil");
            }
            else{
                hasil = "-";
            }

            String penguji;
            if (object.has("penguji")) {
                penguji = object.getString("penguji");
            }
            else{
                penguji = "-";
            }

            listSkuData.add(new SkuAdapterItems(
                    nomor, judul_test, hasil, penguji
            ));
        }

        SkuAdapter skuAdapter = new SkuAdapter(listSkuData, getContext());
        listView.setAdapter(skuAdapter);
    }
}
