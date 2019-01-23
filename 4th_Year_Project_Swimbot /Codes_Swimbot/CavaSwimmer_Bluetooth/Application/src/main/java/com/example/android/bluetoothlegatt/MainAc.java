package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.UUID;

import static java.lang.Integer.parseInt;
import static java.security.AccessController.getContext;
import static java.util.logging.Level.parse;

public class MainAc extends Activity {

    private Button b = null;
    private ProgressBar Bar;
    private int heartrateresult = 0;
    private TextView mDataBT;
    private int sum = 0;
    private int nbVal = 0;
    public double moyenne = 0;




    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainac);


        //Recuperation de l'ID du text view où afficher les data
        mDataBT = (TextView) findViewById(R.id.textView7);

        //Creation de l'intent pour recuperer les données bluetooth envoyées par le class BluetoothLeService
        Intent intent2 = getIntent();

        //Init ProgressBar
        Bar = (ProgressBar) findViewById(R.id.progressBar);
        Bar.setMax(160); //maximum value for the progressbar
        Bar.setVisibility(View.VISIBLE);



        //Changer la valeur du BPM sur l'affichage
        TextView result = (TextView) findViewById(R.id.textView7);
        result.setText(Integer.toString(heartrateresult));



        //initiate button
        b=(Button) findViewById(R.id.button5);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Calcul de la moyenne de la frequence cardiaque
                if (nbVal != 0) {
                    moyenne = sum / nbVal;

                    //Envoie de la valeur de la moyenne dans l'actitive Main2Activity
                    Intent intent = new Intent(MainAc.this, Main2Activity.class);
                    intent.putExtra(Main2Activity.EXTRA_MOYENNE, Double.toString(moyenne));
                    startActivity(intent);
                }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //Remise a zero des varibales pour calcul d'un nouvelle moyenne de frequence cardiaque
        sum = 0;
        nbVal = 0;
        moyenne = 0;
        registerReceiver(mHeartRateReceiver, mHeartRateReceiverIntentFilter());
    }

    //BroadcastReceveiver permettant de recevoir les donnees recu par le Bluetooth
    //(cf BroadcastUpdate dans BluetoothLeService.java)
    private final BroadcastReceiver mHeartRateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String msg = intent.getAction();
            if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(msg)) {
                displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
            }
        }
    };

    private static IntentFilter mHeartRateReceiverIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    //Affichage des données dans le text view
    private void displayData(String data) {
        if (data != null) {
            //Convertion des donnees recues de string vers int pour calcul de moyenne
            sum += Integer.parseInt(data.replaceAll("[\\D]",""));
            nbVal++;
            mDataBT.setText(data);
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MainAc Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
