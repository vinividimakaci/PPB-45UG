package com.example.tugas5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.ContextThemeWrapper;

import androidx.annotation.Nullable;

public class BantuDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_BUAH = "db_buah";
    private static final String KODE = "kode";
    private static final String NAMA_BUAH = "nm_buah";
    private static final String tabel_buah = "tabel_buah";

    public BantuDatabase(Context context) {
        super(context, DATABASE_BUAH, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String nama_tabel = "CREATE TABLE " + tabel_buah + "(" + KODE + " integer primary key auto increment, " + NAMA_BUAH + " text)"
        db.execSQL(nama_tabel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean tambahData(String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA_BUAH, nama);

        long hasil = db.insert(tabel_buah, null, contentValues);
        return hasil != -1;
    }

    public void tampilBuah() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + tabel_buah;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public boolean hapusRecord(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean hasil = db.delete(tabel_buah, KODE + "=" +id, null)>0;
    }

}
