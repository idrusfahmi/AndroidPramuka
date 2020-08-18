package com.akagami.absensipramuka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityAdmin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        Button absensi = findViewById(R.id.absen);
        Button iuran = findViewById(R.id.iuran);
        absensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityAdmin.this,Siswa.class);
                startActivity(intent);
            }
        });
        iuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityAdmin.this,SiswaIuran.class);
                startActivity(intent);
            }
        });
    }
}