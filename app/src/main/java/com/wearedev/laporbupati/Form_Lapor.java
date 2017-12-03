package com.wearedev.laporbupati;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Form_Lapor extends AppCompatActivity implements View.OnClickListener{
    Button bt_lapor;
    EditText Et_email;
    EditText Et_telfon;
    EditText Et_laporan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form__lapor);
        bt_lapor = (Button)findViewById(R.id.button_laporkan);
        Et_email = (EditText)findViewById(R.id.editemail);
        Et_telfon = (EditText)findViewById(R.id.edittelfon);
        Et_laporan = (EditText)findViewById(R.id.editlaporanku);

        bt_lapor.setOnClickListener(this);
    }

    //fungsi clear edit text
    public void clear(){
        Et_email.setText("");
        Et_telfon.setText("");
        Et_laporan.setText("");
    }

    //fungsi untuk posting laporan
    private void laporkan(){
        final String email = Et_email.getText().toString().trim();
        final String telp = Et_telfon.getText().toString().trim();
        final String lapor = Et_laporan.getText().toString().trim();

        class laporkan extends AsyncTask<Void,Void,String>{
            ProgressDialog Loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Loading =ProgressDialog.show(Form_Lapor.this,"Posting Laporan","Mohon Tunggu",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Loading.dismiss();
                Toast.makeText(Form_Lapor.this,s,Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String>params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_EMAIL,email);
                params.put(konfigurasi.KEY_EMP_TELP,telp);
                params.put(konfigurasi.KEY_EMP_LAPOR,lapor);

                RequestHandler rh = new RequestHandler();
                String r = rh.sendPostRequest(konfigurasi.URL_SIMPAN,params);
                return r;
            }
        }

        laporkan lpr = new laporkan();
        lpr.execute();
    }

    //fungsi untuk tindakan klik button
    @Override
    public void onClick(View v) {
        if (v==bt_lapor){
            laporkan();
            clear();
        }
    }
}
