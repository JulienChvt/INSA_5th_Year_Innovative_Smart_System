package com.example.yacineescalante.readfileextern;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//////////////////////////////////// READ  ////////////////////////////////////////////////////////

public class MainActivity extends AppCompatActivity {

    // fichier texte dans lequel sera lu les valeurs de FC
    // placé dans le répertoire "/sdcard" de la Newton => emplacement dans mémoire externe
    private String NOMFICHIER = "HeartRateData.txt";
    // nom du répertoire où est placé le fichier ".txt"
    private String NOMREPERTOIRE = "/sdcard/";

    // fichier test de lecture
    private String NOM = "toto.txt";

    // fichier texte dans lequel sera lu les valeurs de FC
    // placé dans le répertoire "/storage/emulated/0/FCData" de la Newton => emplacement dans mémoire interne
    private String NAME = "HeartRateData.txt";

    // texte qu'on souhaite écrire dans le fichier "HeartrateData.txt"
    // Test pour l'écriture dans les fichiers de la Newton
    // initialisé en texte vide
    private String texte = "";

    // fichier dans lequel on range nos fichiers ".txt""
    private File mFile = null;

    // bouton renvoyant à l'interface graphique affichant la FC moyenne
    private Button b_FC;

    // Pour l'algorithme de calcul de la moyenne
    private int total = 0;
    private int nbElements = 0 ;
    private float moyenne = 0;
    private int valeur = 0;
    private boolean fermeture = true;
    private float moyenne2 = 0;

    private ProgressBar Bar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        ///////////////////////////////// CREATION DU FICHIER //////////////////////////////////////

        // SI FICHIER EN MEMOIRE INTERNE
        //mFile = new File(Environment.getExternalStorageDirectory().getPath() + "/FCdata/ " + NAME);

        // SI FICHIER EN MEMOIRE EXTERNE
        mFile = new File("/sdcard/" + NOMFICHIER);
        ////////////////////////////////////////////////////////////////////////////////////////////

        try {

            // Dans ce bloc try, beaucoup de Toast sont présents afin de tester notre code
            // par un affichage court et spontanné sur l'écran de la Newton

            // Test d'entrée dans le bloc try
            //Toast.makeText(getApplicationContext(), "Entree try !", Toast.LENGTH_SHORT).show();

            // Vérification si la lecture en mémoire interne est autorisée ou non
            // Résultat : "Lecture autorisée !"
            /*if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
                // Le périphérique est bien monté
                Toast.makeText(getApplicationContext(), "Lecture autorisée !", Toast.LENGTH_SHORT).show();
            else
                // Le périphérique n'est pas bien monté ou on ne peut pas écrire dessus
                Toast.makeText(getApplicationContext(), "Lecture non autorisée !", Toast.LENGTH_SHORT).show();*/

            // Pour connaitre le path de la mémoire interne
            // Résultat : affiche "/storage/emulated/0"
            //Toast.makeText(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath(), Toast.LENGTH_SHORT).show();

            // Tentative de lecture du fichier "HeartRateData.txt" en mémoire interne

            // Path du fichier ".txt" qu'on souhaite lire
            // Résultat :
                        // affiche "/storage/emulated/0/FCdata/HeartRateData.txt" si fichier en mémoire interne
                        // affiche "/sdcard/HeartRateData.txt" si fichier placé en mémoire externe
                        // affiche "/sdcard/toto.txt" pour le fichier test
            //Toast.makeText(getApplicationContext(), mFile.getAbsolutePath(), Toast.LENGTH_LONG).show();


            //////////////////////////////// Flux d'entrée ////////////////////////////////////////

            // MODE_WORLD_WRITEABLE : pour donner le droit d'écriture sur le fichier
            // MODE_PRIVATE : mode par défaut, fonctionne pour la simple lecture
            FileOutputStream output = openFileOutput(NOMFICHIER, MODE_WORLD_WRITEABLE);
            // Ecriture dans le flux interne
            // texte = "coucou";
            //output.write(texte.getBytes());
            // Fermeture du fichier en flux d'entrée
            /*if(output != null)
                output.close();*/
            ////////////////////////////////////////////////////////////////////////////////////////

            //////////////////////////////// Flux de sortie ////////////////////////////////////////

            int value;
            // On utilise un StringBuffer pour construire la chaîne au fur et à mesure
            // Utilisé pour afficher en Toast les valeurs lues
            StringBuffer lu = new StringBuffer();
            int [] tab = new int[3]; // tableau d'entier pour ranger nos mesures
            int i =0;

            FileInputStream input = openFileInput(NOMFICHIER);

            // A décommenter que dans le cas de fichier en mémoire interne
            /*
            // On lit les caractères les uns après les autres
            while((value = input.read()) != -1) {
                // On écrit dans le fichier le caractère lu
                lu.append((char)value);
            }
            // On affiche les valeurs lues dans le fichier
            Toast.makeText(MainActivity.this, lu.toString(), Toast.LENGTH_SHORT).show();
            if(input != null)
                input.close();
            */

            // Cas de fichier en mémoire externe
            if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                // On utilise un StringBuffer pour construire la chaîne au fur et à mesure
                // Utilisé pour afficher en Toast les valeurs lues
                lu = new StringBuffer();
                input = new FileInputStream(mFile);

                // Tant que la lecture du fichier est possible (!= -1) ou que le bouton STOP n'a pas été appuyé
                // Les mesures peuvent donner des nombres à 3, 2 ou 1 chiffres (ex : 100 bpm, 75 bpm ou 0 bpm
                // La lecture se faisant caractère par caractère, on range le caractère lu dans un
                // tableau de 3 cases maximum jusqu'à arriver en fin de ligne

                while( ((value = input.read()) != -1) && fermeture) {
                    // si la valeur du charactère lue est != de -1, alors on la range dans le tableau
                    if (Character.getNumericValue((char) value) != -1) {
                        tab[i] = Character.getNumericValue((char) value);
                        //Toast.makeText(MainActivity.this, String.valueOf(tab[i]), Toast.LENGTH_SHORT).show();
                        i++; // on incrémente l'indicage du tableau
                    }
                    // sinon cela veut dire qu'on est arrivé à la fin de la ligne
                    // on concatène alors les deux voire trois caractères lues sur la ligne pour
                    // obtenir un nombre à 3, 2 ou 1 chiffre(s)
                    else if (Character.getNumericValue((char) value) == -1) {
                        // si le tableau contient 3 caractères
                        if (i == 3) {
                            valeur = (tab[0]*100) + (tab[1]*10) + (tab[2]*1);
                        }
                        // si le tableau contient 2 caractères
                        else if (i == 2) {
                            valeur = (tab[0]*10) + (tab[1]*1);
                        }
                        // si le tableau contient 1 caractère
                        else if (i == 1) {
                            valeur = (tab[0]*1);
                        }
                        // Vérification du chiffre concaténé
                        //Toast.makeText(MainActivity.this, String.valueOf(valeur), Toast.LENGTH_SHORT).show();
                        total += valeur; // on actualise le total des valeurs lues
                        nbElements++; // on incrémente le nombre de mesures effectuées

                        // On réinitialise nos paramètres
                        tab = new int[3];
                        valeur = 0;
                        i = 0;
                    }

                    lu.append((char) value);

                    // Fonction permettant de réaliser un sleep à la Newton laissant le temps au Toast
                    // de s'afficher ligne par ligne pour les tests
                    /*mHandler.postDelayed(new Runnable() {
                        public void run() {
                        }
                    }, 1000);*/
                }

                // Calcul de la moyenne
                moyenne = ((float) total / (float) nbElements) ;
                // Affichage des valeurs lues dans le fichier ".txt"
                //Toast.makeText(MainActivity.this, lu.toString(), Toast.LENGTH_SHORT).show();
                // Affichag de la moyenne de toutes ces valeurs
                //Toast.makeText(MainActivity.this, String.valueOf(moyenne), Toast.LENGTH_LONG).show();
                // On ferme le fichier
                if(input != null)
                    input.close();
            }
            ////////////////////////////////////////////////////////////////////////////////////////

            //////////////////////////// BOUTON_CALCUL_FC_MOYENNE //////////////////////////////////
            //Lancer l'activité MainAc pour afficher la fc moyenne calculée ci dessous
            //Initialisation du bouton
            b_FC = (Button) findViewById(R.id.button_fcmoy);

            //Fonction s'exécutant lors de l'appui sur le bouton FC MOYENNE
            b_FC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fermeture= false;

                    //Initialisation du "lien de passage" de cette activité vers MainAc
                    Intent intent = new Intent(MainActivity.this, MainAc.class);
                    //Calcul de la moyenne
                    moyenne2 = moyenne;
                    //Toast.makeText(MainActivity.this, String.valueOf(moyenne2), Toast.LENGTH_SHORT).show();
                    moyenne2 = java.lang.Math.round(moyenne2 * 100);
                    moyenne2 = (moyenne2/(100));

                    //Passage de la valeur moyenne d'une activité vers une autre
                    intent.putExtra(MainAc.EXTRAS_BAD,String.valueOf(moyenne2));
                    //Lancement de l'activité MainAc
                    startActivity(intent);
                }
            });

            //////////////////////////////// Progress bar ///////////////////////////////////////////
            //initiate progress bar
            Bar=(ProgressBar) findViewById(R.id.progressBar2);
            Bar.setVisibility(View.VISIBLE);

        } catch (FileNotFoundException e) {
            // Cette exception est levée si l'objet FileInputStream ne trouve aucun fichier
            Toast.makeText(getApplicationContext(), "Fichier non trouvé !", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            // Cette exception est levée si l'objet FileInputStream a été fermé (n=fis.available())
            Toast.makeText(getApplicationContext(), "Fichier fermé !", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

