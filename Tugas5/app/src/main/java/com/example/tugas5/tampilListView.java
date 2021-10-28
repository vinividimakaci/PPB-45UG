package com.example.tugas5;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class tampilListView extends AppCompatActivity {
    BantuDatabase bd;
    ListView listView;
    EditText editText;
    Button tblTambah;

    ArrayAdapter adapter;
    ArrayList<String> lstViewKu;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.lauout.activity_tampil_lstView);

        listView = findViewById(R.id.listDataBuah);
        editText = findViewById(R.id.dataBuah);
        tblTambah = findViewById(R.id.tblTambah);
        bd = new BantuDatabase(this);
        lstViewKu = new ArrayList<>();
        tampilkan_buah();
        listView.setOnItemClickListener();
        tblTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                bd.tambahData(editText.getText().toString());
                Toast.makeText(tampilListView.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                lstViewKu.clear();
                tampilkan_buah();
            }
        });
    }

    private void tampilkan_buah() {
        Cursor cursor = bd.tampilBuah();
        if (cursor.getCount()==0) {
            Toast.makeText(this, "Record Kosong!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                lstViewKu.add(String.valueOf(cursor.getInt(0)) + " " + cursor.getString(1));
            }
            adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listView);
            listView.setAdapter(adapter);
        }
    }

}
