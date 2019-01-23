package com.example.yacineescalante.readfileextern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by yacineescalante on 26/05/2017.
 */

public class MainAc extends AppCompatActivity {

    private Button button_Nouveau_Test = null;
    private Button button_Exit = null;
    private ProgressBar Bar;
    public float heartrate=0;
    public static float EXTRAS_RESULT=0;
    public static String EXTRAS_BAD="";
    public String fc = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Dire qu'on utilise le graphisme xml de activity_mainac
        setContentView(R.layout.activity_mainac);
        //Initialiser le TextView d'affichage
        TextView result = (TextView) findViewById(R.id.textView2);

        //Initalisation de l'intent, état d'attente, qui doit recevoir de MainActivity
        Intent intent2=getIntent();
        //Récuperer la moyenne calculée dans MainActivity et l'arrondir à deux chiffres après la virgule
        fc = intent2.getStringExtra(EXTRAS_BAD);
        //Toast.makeText(MainAc.this, fc, Toast.LENGTH_SHORT).show();
        // arrondir
        //heartrate = java.lang.Math.round(intent2.getFloatExtra(EXTRAS_BAD,EXTRAS_RESULT) * 100);
        heartrate = (heartrate/((float)100));
        //Afficher la moyenne dans le TextView
        result.setText(fc);

        ////////////////////////////// BOUTON_NOUVEAU_TEST/////////////////////////////////////////
        //Lancer une nouvelle lecture de données du capteur
        //Initalisation du bouton (lien avec xml)
        button_Nouveau_Test=(Button) findViewById(R.id.button_new_test);
        //Fonction lors que l'utilisateur clique sur ce bouton
        button_Nouveau_Test.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainAc.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //////////////////////////////// BOUTON_EXIT ///////////////////////////////////////////
        //Quitte l'application et revenir sur le menu de la Newton
        //Initalisation du bouton (lien avec xml)
        button_Exit = (Button) findViewById(R.id.button_exit);
        //Fonction lorsque l'utilisateur clique sur ce bouton
        button_Exit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);

            }
        });

    }

}
