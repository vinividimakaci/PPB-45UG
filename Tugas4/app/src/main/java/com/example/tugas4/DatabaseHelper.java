package com.example.tugas4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String nama_db = "db_game";
    public final static String nama_table = "tbl_game";

    public final static String field_01 = "id_game";
    public final static String field_02 = "judul_game";
    public final static String field_03 = "tahun_rilis";
    public final static String field_04 = "pencipta";
    public final static String field_05 = "karakter";


    public DatabaseHelper(Context context) {
        super(context, nama_db, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+nama_table+"(id_game text primary key, judul_game text, tahun_rilis text, pencipta text, karakter text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+nama_table);
        onCreate(db);
    }

    public Cursor baca_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + nama_table, null);
        return res;
    }

    public void tambah_data(String x_id, String x_judul, String x_tahun, String x_pcp, String x_karakter) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(field_01, x_id);
        contentValues.put(field_02, x_judul);
        contentValues.put(field_03, x_tahun);
        contentValues.put(field_04, x_pcp);
        contentValues.put(field_05, x_karakter);

        db.insert(nama_table, null, contentValues);
    }
}
