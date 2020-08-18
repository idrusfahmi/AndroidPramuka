package com.akagami.absensipramuka;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Objects;

public class Iuran extends AppCompatActivity {

    String id_siswaTop,nama_siswa;
    private int mYear, mMonth, mDay;
    private String[] IuranSp = {
            "Lunas","Belum Lunas"
    };
    com.weiwangcn.betterspinner.library.BetterSpinner et_keterangan;
    com.rengwuxian.materialedittext.MaterialEditText et_id_iuran,et_id_siswa,et_tanggal,et_nominal;
    Button btn_submit;
    String id_iuran,id_siswa,nominal,tanggal,keterangan;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iuran);
        if(getIntent().getExtras()!=null) {
            Bundle bundle = getIntent().getExtras();
            id_siswaTop= bundle.getString("id_siswa");
            nama_siswa = bundle.getString("nama_siswa");
            TextView id_siswatext = findViewById(R.id.siswa);
            id_siswatext.setText(id_siswaTop);
            TextView name = findViewById(R.id.nama);
            name.setText(nama_siswa);
        }
        et_id_iuran = findViewById(R.id.et_id_iuran);
        et_id_siswa = findViewById(R.id.et_id_siswa);
        et_tanggal          = findViewById(R.id.et_tanggal);
        et_nominal             = findViewById(R.id.et_nominal);
        et_keterangan           = findViewById(R.id.et_keterangan);
        btn_submit          = findViewById(R.id.btn_submit);

        progressDialog      = new ProgressDialog(this);

        iuran();
        et_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Iuran.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                et_tanggal.setText(year + "-" + (monthOfYear + 1) + "-" +dayOfMonth );

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Menambahkan Data...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                id_iuran = Objects.requireNonNull(et_id_iuran.getText()).toString();
                id_siswa = Objects.requireNonNull(et_id_siswa.getText()).toString();
                nominal = Objects.requireNonNull(et_nominal.getText()).toString();
                //                absensi = et_absensi.getText().toString();
                tanggal = Objects.requireNonNull(et_tanggal.getText()).toString();
                keterangan = Objects.requireNonNull(et_keterangan.getText()).toString();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        validasiData();
                    }
                },1000);
            }
        });

    }
    private void iuran() {
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, IuranSp);

        // mengeset Array Adapter tersebut ke Spinner
        et_keterangan.setAdapter(adapter);

        // mengeset listener untuk mengetahui saat item dipilih
        et_keterangan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
                Toast.makeText(getApplicationContext(), "Selected "+ adapter.getItem(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    void validasiData(){
        if(id_siswaTop.equals("") || nominal.equals("") ||  tanggal.equals("") || keterangan.equals("")){
            progressDialog.dismiss();
            Log.i("zala", id_siswaTop);
            Log.i("zala", nominal);
            Log.i("zala", tanggal);
            Log.i("zala", keterangan);
            Toast.makeText(Iuran.this, "Periksa kembali data yang anda masukkan !", Toast.LENGTH_SHORT).show();
        }else {
            kirimData();
        }
    }
    void kirimData(){
        Log.i("zala", id_siswaTop);
        Log.i("zala", nominal);
        Log.i("zala", tanggal);
        Log.i("zala", keterangan);
        String url_insert = Server.URL + "insertiuran.php";
        AndroidNetworking.post(url_insert) //sesuaikan dengan api server kalian
                .addBodyParameter("id_iuran",""+id_iuran)
                .addBodyParameter("id_siswa",""+id_siswaTop)
                .addBodyParameter("periode",""+tanggal)
                .addBodyParameter("nominal",""+nominal)
                .addBodyParameter("keterangan",""+keterangan)
                .setPriority(Priority.MEDIUM)
                .setTag("Tambah Data")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("cekTambah",""+response);
                        try {
                            boolean status = response.getBoolean("status");
                            String pesan = response.getString("result");
                            Toast.makeText(Iuran.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(Iuran.this)
                                        .setMessage("Berhasil Menambahkan Data !")
                                        .setCancelable(false)
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent i = getIntent();
                                                setResult(RESULT_OK,i);
//                                                Iuran.this.finish();
                                            }
                                        })
                                        .show();
                            }
                            else{
                                new AlertDialog.Builder(Iuran.this)
                                        .setMessage("Gagal Menambahkan Data !")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent i = getIntent();
                                                setResult(RESULT_CANCELED,i);
                                                Iuran.this.finish();
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorTambahData",""+anError.getErrorBody());
                    }
                });
    }
}