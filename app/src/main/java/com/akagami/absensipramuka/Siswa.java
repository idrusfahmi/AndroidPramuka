package com.akagami.absensipramuka;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Siswa extends AppCompatActivity {
    SwipeRefreshLayout srl_main;
    RecyclerView rv_main;
    ArrayList<String> array_id_siswa,array_nama_siswa;
    ProgressDialog progressDialog;
    AdapterSiswa adapterSiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa);
        srl_main    = findViewById(R.id.srl_main);
        rv_main     = findViewById(R.id.rv_main);
        progressDialog = new ProgressDialog(this);

        rv_main.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rv_main.setLayoutManager(layoutManager);


        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollRefresh();
                srl_main.setRefreshing(false);
            }
        });

        scrollRefresh();
    }

    public void scrollRefresh(){
        progressDialog.setMessage("Mengambil Data.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        },1200);
    }

    void initializeArray(){
        array_id_siswa = new ArrayList<>();
        array_nama_siswa = new ArrayList<>();

        array_id_siswa.clear();
        array_nama_siswa.clear();
    }

    public void getData(){
        initializeArray();
        String url_select = Server.URL + "selectsiswa.php";
        AndroidNetworking.get(url_select)
                .setTag("Get Data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        try{
                            boolean status = response.getBoolean("status");
                            if(status){
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respon",""+ja);
                                for(int i = 0 ; i < ja.length() ; i++){
                                    JSONObject jo = ja.getJSONObject(i);

                                    array_id_siswa.add(jo.getString("id_siswa"));
                                    array_nama_siswa.add(jo.getString("nama_siswa"));
                                }
                            }else{
                                Toast.makeText(Siswa.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                            }
                            adapterSiswa = new AdapterSiswa(Siswa.this,array_id_siswa,array_nama_siswa);
                            rv_main.setAdapter(adapterSiswa);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }}