package com.example.wilfried.asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.app.Activity;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class  AsyncBigCalculActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private Button mButton;

    /** Called when the activity is first created. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



            // On récupère les composants de notre layout
            mProgressBar = (ProgressBar) findViewById(R.id.pBAsync);
            mButton = (Button) findViewById(R.id.btnLaunch);

            // On met un Listener sur le bouton
            mButton.setOnClickListener(new OnClickListener() {
                @Override

                public void onClick(View arg0) {
                    Toast.makeText(AsyncBigCalculActivity.this, "debut de la tache", Toast.LENGTH_SHORT).show();
                    FreeGeoIpAsync asycn = new FreeGeoIpAsync();
                    asycn.execute();
                    //BigCalcul calcul=new BigCalcul();
                    Toast.makeText(AsyncBigCalculActivity.this, "fin de la tache", Toast.LENGTH_SHORT).show();
                   // calcul.execute();
                }
            });
        }

        private class FreeGeoIpAsync extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... params) {

                URL url= null;HttpURLConnection urlConnection = null;

                try {

                    url = new URL("http://freegeoip.net/xml/4.2.2.2");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    //readStream(in);
                } catch (MalformedURLException e) {
                    Log.d(e.getMessage(), e.getMessage(), e);
                } catch (IOException e) {
                    Log.d(e.getMessage(), e.getMessage(), e);
                } finally {
                    urlConnection.disconnect();
                }
                return null;
            }
        }

        private class BigCalcul extends AsyncTask<Void, Integer, Void>
        {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(getApplicationContext(), "Début du traitement asynchrone", Toast.LENGTH_LONG).show();
            }

            @Override
            protected void onProgressUpdate(Integer... values){
                super.onProgressUpdate(values);
                // Mise à jour de la ProgressBar
                mProgressBar.setProgress(values[0]);
            }

            @Override
            protected Void doInBackground(Void... arg0) {

                int progress;
                for (progress=0;progress<=100;progress++)
                {
                    for (int i=0; i<1000000; i++){}
                    //la méthode publishProgress met à jour l'interface en invoquant la méthode onProgressUpdate
                    publishProgress(progress);
                    progress++;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                Toast.makeText(getApplicationContext(), "Le traitement asynchrone est terminé", Toast.LENGTH_LONG).show();
            }
        }
    }


