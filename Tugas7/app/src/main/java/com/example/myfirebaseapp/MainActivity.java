package com.example.myfirebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    EditText judul_game, tahun_rilis, pencipta, karakter;
    Button tblSave, tblView;
    DatabaseReference reference;
    Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        judul_game = findViewById(R.id.judul_game);
        tahun_rilis = findViewById(R.id.tahun_rilis);
        pencipta = findViewById(R.id.pencipta);
        karakter = findViewById(R.id.karakter);

        tblSave = findViewById(R.id.btnInput);
        game = new Game();

        reference = FirebaseDatabase.getInstance().getReference().child("game");
        tblSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                game.setJudul_game(judul_game.getText().toString().trim());
                game.setTahun_rilis(tahun_rilis.getText().toString());
                game.setPencipta(pencipta.getText().toString());
                game.setTokoh(karakter.getText().toString());
                Toast.makeText(MainActivity.this, "Data Tersimpan", Toast.LENGTH_LONG).show();
            }
        });

    }
}