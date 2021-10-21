package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbh;
    ListView listView;
    Button tblInput;

    ArrayList<String> listItem;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_data);
        tblInput = findViewById(R.id.tombol_input);
        dbh = new DatabaseHelper(this);
        listItem = new ArrayList<>();

//        String id_game = "G01";
//        String judul_game = "Final Fantasy V";
//        String tahun_rilis = "1992";
//        String pencipta = "Square Enix";
//        String karakter = "Cecil Harvey";

//        dbh.tambah_data(id_game, judul_game, tahun_rilis, pencipta, karakter);

        Cursor cursor = dbh.baca_data();

        listItem.clear();
        while(cursor.moveToNext()) {
            listItem.add(cursor.getString(0) + " | " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4));
        }
        adapter = new ArrayAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, listItem);
        listView.setAdapter(adapter);

        tblInput.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, inputGame.class);
                startActivity(intent);
            }
        });
    }
}