package com.example.projekmysql;

import static android.os.Build.VERSION_CODES.S;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView)findViewById(R.id.listdata);

        tampilBarang("http://192.168.1.4:8080/service_data.php");
    }

    private void tampilBarang(String s) {
        class tampilBarang extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    bacadatakelist(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(s);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine() != null)) {
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        }
        tampilBarang tampil = new tampilBarang();
            tampil.execute();
    }

    private void bacadatakelist(String s) {
        JSONArray jsonaArray = new JSONArray(json);
        String stok[] = new String[jsonArray.length()];

        for (int i = 0; i < jsonaArray.length(); i++) {
            JSONObject obj = jsonaArray.getJSONObject(i);
            stok[i] = obj.getString("kode")+" "+
                      obj.getString("nama_barang")+" "+
                      obj.getString("harga");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, stok);
        listView.setAdapter(arrayAdapter);
    }
}