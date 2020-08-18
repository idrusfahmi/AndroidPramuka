package com.akagami.absensipramuka.ui.iuran;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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
import com.akagami.absensipramuka.adapter.IuranAdapter;
import com.akagami.absensipramuka.adapter.IuranAdapterItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class IuranFragment extends Fragment {

    private View view;
    private ListView listView;
    private ArrayList<IuranAdapterItems> listIuranData = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        IuranViewModel iuranViewModel = ViewModelProviders.of(this).get(IuranViewModel.class);
        return inflater.inflate(R.layout.fragment_iuran, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view = v;

        listView = view.findViewById(R.id.lv_iuran);
        callApiIuran();
    }

    private void callApiIuran() {
        listIuranData.clear();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String username = sharedPreferences.getString("username", "empty");
        Log.i("buwong", username);
        Call<String> call = api.postIuranSiswa(username);
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
                                iuranSiswa(jsonObject);
                            } catch (JSONException e){
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getContext(), "Kesalahan Sistem ke 2", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getContext(), "Kesalahan Sistem 1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void iuranSiswa(JSONObject jsonObject) throws JSONException {
        JSONArray data = jsonObject.getJSONArray("data");
        for (int i = 0; i < data.length(); i++) {
            JSONObject object = data.getJSONObject(i);

            String bulan = object.getString("bulan");
            String iuran_minggu1;
            if (object.has("minggu_1")) {
                iuran_minggu1 = object.getString("minggu_1");
            }
            else{
                iuran_minggu1 = "-";
            }

            String iuran_minggu2;
            if (object.has("minggu_2")) {
                iuran_minggu2 = object.getString("minggu_2");
            }
            else{
                iuran_minggu2 = "-";
            }

            String iuran_minggu3;
            if (object.has("minggu_3")) {
                iuran_minggu3 = object.getString("minggu_3");
            }
            else{
                iuran_minggu3 = "-";
            }

            String iuran_minggu4;
            if (object.has("minggu_4")) {
                iuran_minggu4 = object.getString("minggu_4");
            }
            else{
                iuran_minggu4 = "-";
            }

            String iuran_minggu5;
            if (object.has("minggu_5")) {
                iuran_minggu5 = object.getString("minggu_5");
            }
            else{
                iuran_minggu5 = "-";
            }

            listIuranData.add(new IuranAdapterItems(
                    bulan, iuran_minggu1, iuran_minggu2, iuran_minggu3, iuran_minggu4, iuran_minggu5
            ));
        }

        IuranAdapter iuranAdapter = new IuranAdapter(listIuranData, getContext());
        listView.setAdapter(iuranAdapter);
    }
}
