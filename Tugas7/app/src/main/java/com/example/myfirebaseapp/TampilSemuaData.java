package com.example.myfirebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TampilSemuaData extends AppCompatActivity {
    ListView listView;
    ArrayAdapter arrayAdapter;

    ArrayList<String> arrayTampil = new ArrayList<>();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_semua_data);
        listView = findViewById(R.id.listdataku);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("game");
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrayTampil);
        listView.setAdapter(arrayAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String hasil = dataSnapshot.getValue(Game.class).toPrint();
                arrayTampil.add(hasil);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TampilSemuaData.class);
                startActivity(intent);
            }
        })
    }
}
