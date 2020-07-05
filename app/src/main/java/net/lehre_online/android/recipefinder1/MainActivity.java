package net.lehre_online.android.recipefinder1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**Diese Klasse Main Acitivity enthält das Backend zur Startseite
 * @author Kevin Giesen
 * @version 05.07.2020
 */

public class MainActivity extends AppCompatActivity {

    //Variablen deklaration
    static final boolean DBG        = true;
    static final String TAG         = "MainActivity";
    RecipeMemoDbHelper myDb;

    private Button btnLetsCook;
    private Button btnHelp;
    private EditText editTextMail;
    public static String e_mail;


    public static final String LOG_TAG = MainActivity.class.getSimpleName();


    //onCreate Methode wird beim Seitenaufruf gestartet
    @Override
    protected void onCreate(Bundle savedInstanceState) {


       final String MNAME = "onCreate()";
        if (DBG) Log.d(TAG, MNAME + "entering...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new RecipeMemoDbHelper(this);
        System.out.println("Db gestartet");


        //onClickListener für den Button "LetsCook" erstellen
        btnLetsCook = findViewById(R.id.Button_LetsCook);
        btnLetsCook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Die eingegebene Mail des Nutzers wird aus dem EditText Feld ausgelesen und in eine Variable gespeichert
                editTextMail = (EditText) findViewById(R.id.EditText_MailText);
                 e_mail= editTextMail.getText().toString();
                 //Methode onClickLetsCook gestartet
                onClickLetsCook();
            }

        });

        if(DBG)Log.d(TAG,MNAME +"entering...");

        //OnClick-Listener für den Hilfe Button
        btnHelp = findViewById(R.id.Button_Help);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Aufruf der Methode onClickHelp
                onClickHelp();
            }
        });
    }

    //Methode onClickLetsCook aufgerufen bei Klick auf "LetsCook" Button
     public void onClickLetsCook() {

           final String MNAME = "onClickLetsCook()";
           if( DBG ) Log.v( TAG, MNAME + "entering..." );

           //Über einen Intent die nächste Klasse "RecipeScearchActivity" Aufgerufen
            Intent intent = new Intent( MainActivity.this, RecipeSearchActivity.class );
            startActivity(intent);

            if( DBG ) Log.d( TAG, MNAME + "...exiting" );
    }

    //Methode onClickGHelp aufgerufen bei klick auf den Hilfe Button
    public void onClickHelp() {

        final String MNAME = "onClickLetsCook()";
        if( DBG ) Log.v( TAG, MNAME + "entering..." );

        //Anzeigen eines Toast inkl. Erklärtext
        Toast.makeText(MainActivity.this,"Diese App dient Ihnen dazu, übrig gebliebene Lebensmittel bestmöglich zu verwerten, indem Sie Ihnen eine Vielzahl von Rezepten vorschlägt, die man mit Ihren Lebensmitteln kochen kann.",Toast.LENGTH_LONG).show();

        if( DBG ) Log.d( TAG, MNAME + "...exiting" );
    }



}
