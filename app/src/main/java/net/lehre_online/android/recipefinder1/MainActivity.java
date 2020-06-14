package net.lehre_online.android.recipefinder1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    static final boolean DBG        = true;
    static final String TAG         = "MainActivity";
    RecipeMemoDbHelper myDb;

    private Button btnLetsCook;
    private Button btnHelp;
    private EditText editTextMail;
    public static String e_mail;


    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private RecipeMemoDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


       final String MNAME = "onCreate()";
        if (DBG) Log.d(TAG, MNAME + "entering...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new RecipeMemoDbHelper(this);
        System.out.println("Db gestartet");



        btnLetsCook = findViewById(R.id.Button_LetsCook);
        btnLetsCook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                editTextMail = (EditText) findViewById(R.id.EditText_MailText);
                 e_mail= editTextMail.getText().toString();
                onClickLetsCook();
            }

        });

        if(DBG)Log.d(TAG,MNAME +"entering...");

        btnHelp = findViewById(R.id.Button_Help);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClickHelp();
            }
        });
    }


     public void onClickLetsCook() {

           final String MNAME = "onClickLetsCook()";
           if( DBG ) Log.v( TAG, MNAME + "entering..." );

            Intent intent = new Intent( MainActivity.this, RecipeSearchActivity.class );
            startActivity(intent);

            if( DBG ) Log.d( TAG, MNAME + "...exiting" );
    }

    public void onClickHelp() {

        final String MNAME = "onClickLetsCook()";
        if( DBG ) Log.v( TAG, MNAME + "entering..." );

        Toast.makeText(MainActivity.this,"Diese App dient Ihnen dazu, übrig gebliebene Lebensmittel bestmöglich zu verwerten, indem Sie Ihnen eine Vielzahl von Rezepten vorschlägt, die man mit Ihren Lebensmitteln kochen kann.",Toast.LENGTH_LONG).show();

        if( DBG ) Log.d( TAG, MNAME + "...exiting" );
    }

 /*

        RecipeMemo testMemo = new RecipeMemo("Pizza", "Pizza dies das", 102);
        Log.d(LOG_TAG, "Inhalt der Testmemo: " + testMemo.toString());

        dataSource = new RecipeMemoDataSource(this);

  */

}
