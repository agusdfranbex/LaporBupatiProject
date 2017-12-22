package com.wearedev.laporbupati;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Form_beranda extends AppCompatActivity implements ListView.OnItemClickListener {
    private ListView mListView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_beranda);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnItemClickListener(this);
        getJson();
    }

    private void tampil() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(konfigurasi.TAG_ID);
                String email = jo.getString(konfigurasi.TAG_EMAIL);
                String telp = jo.getString(konfigurasi.TAG_TELP);
                String lapor = jo.getString(konfigurasi.TAG_LAPOR);

                HashMap<String, String> data = new HashMap<>();
                data.put(konfigurasi.TAG_ID, "id     : " + id);
                data.put(konfigurasi.TAG_EMAIL, "email   : " + email);
                data.put(konfigurasi.TAG_TELP, "telp : " + telp);
                data.put(konfigurasi.TAG_LAPOR, "laporan   : " + lapor);
                list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                Form_beranda.this, list, R.layout.list_laporan,
                new String[]{konfigurasi.TAG_ID,
                        konfigurasi.TAG_EMAIL,
                        konfigurasi.TAG_TELP,
                        konfigurasi.TAG_LAPOR},
                new int[]{R.id.id, R.id.email, R.id.telp, R.id.laporan});
        mListView.setAdapter(adapter);
    }

    public void getJson() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Form_beranda.this, "Mengambil data", "loading...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                tampil();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_TAMPIL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Form_beranda.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String empID = map.get(konfigurasi.TAG_ID).toString();
        intent.putExtra(konfigurasi.EMP_ID, empID);
        startActivity(intent);
    }
}