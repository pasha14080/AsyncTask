package com.example.itzpulu.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String stringUrl = "https://iiitd.ac.in/about";
    Button b;
    boolean flag = false,done = false;
    TextView text;
    ProgressDialog Dialog;
    String buffer="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b= (Button)findViewById(R.id.button);
        text = (TextView)findViewById(R.id.textView);
    }
    public void click(View v)
    {
        flag=true;
        System.out.println("flag true now");
            new Data().execute();
    }
    private class Data extends AsyncTask <Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Dialog = new ProgressDialog(MainActivity.this);
            Dialog.setTitle("Wait for Data to retrieve");
            Dialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect(stringUrl).get();
                buffer = document.html();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            text.setText(buffer);
            Dialog.dismiss();
        }
    }
}
