package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends  Activity {

    private Button But;
    private TextView mDataMoyenne;
    private String moyenne = " ";


    public final static String EXTRA_MOYENNE = "toto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mDataMoyenne = (TextView) findViewById(R.id.textView5);

        //Recuperation de la valeur moyenne de la frequence cardiaque calculee dans MainAc
        Intent intent = getIntent();
        moyenne = intent.getStringExtra(EXTRA_MOYENNE);

        //Affichage de la moyenne
        displayData(moyenne);

        //Bouton pour retour sur ecran d'affichage en temps reel de la frequence cardiaque
        But=(Button) findViewById(R.id.button);
        But.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent2 = new Intent(Main2Activity.this, MainAc.class);
                startActivity(intent2);
            }
        });


    }

    private void displayData(String data) {
        if (data != null) {
            mDataMoyenne.setText(data);
        }
    }

}
