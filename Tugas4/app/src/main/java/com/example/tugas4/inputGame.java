package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class inputGame extends AppCompatActivity {
    Button btnSave, btnView;
    EditText x_id, x_judul, x_tahun, x_pcp, x_karakter;
    DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_game);

        x_id = findViewById(R.id.id_game);
        x_judul = findViewById(R.id.judul_game);
        x_tahun = findViewById(R.id.tahun_rilis);
        x_pcp = findViewById(R.id.pencipta);
        x_karakter = findViewById(R.id.karakter);

        btnSave = findViewById(R.id.btnInput);
        btnView = findViewById(R.id.btnView);

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbh.tambah_data(x_id.getText(), x_judul.getText(), x_tahun.getText(), x_pcp.getText(), x_karakter.getText());
            }
        });

        btnView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inputGame.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}