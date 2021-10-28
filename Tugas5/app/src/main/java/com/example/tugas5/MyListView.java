package com.example.tugas5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MyListView {
    ListView listView;
    public String bhs_program[] = {"Prolog", "C+", "Pascal", "Cobol", "Perl", "Algol L", "Java", "PHP", "Phyton"}

    Spinner combo;
    @Override
    protected void onCreateBundle (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_listview);

        listView = (ListView) findViewById(R.id.listdata);
        combo = (Spinner) findViewById(R.id.combodata);

        ArrayAdapter adapter = new ArrayAdapter(MyListView.this, R.layout.support_simple_spinner_dropdown_item);
        listView.setAdapter(adapter);
        combo.setAdapter(adapter);
    }
}
