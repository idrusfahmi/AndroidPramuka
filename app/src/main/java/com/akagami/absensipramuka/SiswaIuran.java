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

public class SiswaIuran extends AppCompatActivity {
    SwipeRefreshLayout srl_main_iuran;
    RecyclerView rv_main_iuran;
    ArrayList<String> array_id_siswa,array_nama_siswa;
    ProgressDialog progressDialog;
    IuranAdapter iuranAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa_iuran);
        srl_main_iuran    = findViewById(R.id.srl_main_iuran);
        rv_main_iuran     = findViewById(R.id.rv_main_iuran);
        progressDialog = new ProgressDialog(this);

        rv_main_iuran.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rv_main_iuran.setLayoutManager(layoutManager);


        srl_main_iuran.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollRefresh();
                srl_main_iuran.setRefreshing(false);
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
                                Toast.makeText(SiswaIuran.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                            }
                            iuranAdapter = new IuranAdapter(SiswaIuran.this,array_id_siswa,array_nama_siswa);
                            rv_main_iuran.setAdapter(iuranAdapter);
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