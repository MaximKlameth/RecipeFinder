package net.lehre_online.android.recipefinder1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**Diese Klasse Recipe Scearch enthält das Backend zur Seite der Zutateneingabe inkl. der Suche
 * @author Kevin Giesen
 * @version 05.07.2020
 */
public class RecipeSearchActivity extends AppCompatActivity {

    //Deklaration der Variabeln
    static final boolean DBG        = MainActivity.DBG;
    static final String TAG         = "RecipeSearchActivity";

    RecipeMemoDbHelper myDb;

    //Deklaration der Buttons & EditText Feldern
    public Button btnSearch;
    private Button btnAddIngredient;
    private Button btnAddIngredient2;
    private Button btnMyRecipes;

    private Button btnShowAll;
    public EditText editTextZutat1;
    public static String zutat1;
    public EditText editTextZutat2;
    public static String zutat2;
    public EditText editTextZutat3;
    public static String zutat3;
    public EditText editTextZutat4;
    public static String zutat4;
    public EditText editTextZutat5;
    public static String zutat5;

    //ArrayList in welcher die zutreffenden Rezepte abgelegt werden
    public static  ArrayList<String> rezeptlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String MNAME = "onCreate()";
        if (DBG) Log.d(TAG, MNAME + "entering...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_search);
        //Erzeugung eines DB-Objekts
        myDb = new RecipeMemoDbHelper(this);

        //Erstellung eines onClick-Listener für den "Show All" Button
        btnShowAll = (Button) findViewById(R.id.Button_Show_All);
        //Aufruf der Methode onClickShowALL, über welche alle Rezepte in der DB angezeigt werden
                onClickShowAll();


        //Erstellung eines onClick-Listener für den "Suche" Button
        btnSearch = findViewById(R.id.Button_Search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Aufruf der Methode onClickScearch, über welche die Rezeptsuche gestartet wird
                onClickSearch();
            }
        });

        btnMyRecipes = findViewById(R.id.Button_My_Recipes);
        btnMyRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aufruf der Methode onClickMyRecipes, über welche die gespeicherten Rezepte gesucht werden
                onClickMyRecipes();
            }
        });

        btnAddIngredient = findViewById(R.id.Button_AddIngredient);
        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aufruf der Methode onClickAddIngredient über welche ein weiteres Feld zur Zutateneingabe hinzugefügt wird
                onClickAddIngredient();
            }
        });

        btnAddIngredient2 = findViewById(R.id.Button_AddIngredient2);
        btnAddIngredient2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aufruf der Methode onClickAddIngredient2 über welche ein weiteres Feld zur Zutateneingabe hinzugefügt wird
                onClickAddIngredient2();
            }
        });



        if(DBG)Log.d(TAG,MNAME +"entering...");



    }
    //Methode für die Suche der gespeicherten Rezepte
    public void onClickMyRecipes(){
        //Datenbank-Methode getRecipeMailFit aus der Klasse RecipeMemoDbHelper auf einem erzeugten DB-Objekte ausführen
        //Angegebene Mail des Users übergeben
        //Rückgabewert in einen Cursor speichern
        Cursor mailfit= myDb.getRecipeMailFit(MainActivity.e_mail);
        if(mailfit.getCount() == 0){
            //Nachricht zeigen für den Fall, dass keine
            showMessage("Error", "Keine Rezepte gefunden");
            return;
        }
        //rezeptlist Array leeren (durch vorherige Suchen könnte dieses gefüllt sein)
        rezeptlist.clear();
        //Daten aus dem Cursor in die Rezeptliste einfügen
        while (mailfit.moveToNext()){
            rezeptlist.add(mailfit.getString(0));
        }
        //Über einen Intent die nächste Klasse ResultListActivity aufrufen auf welcher die Rezeptliste verarbeitet wird
        Intent intent2 = new Intent( RecipeSearchActivity.this, ResultListActivity.class );
        startActivity(intent2);

        if( DBG ) Log.d( TAG,   "...exiting" );
    }

    //Methode für den Suche Button
    private void onClickSearch() {

        final String MNAME = "onClickSearch()";
        if( DBG ) Log.v( TAG, MNAME + "entering..." );

        //Zutaten aus den EditText Feldern entnehmen
        editTextZutat1 = findViewById(R.id.EditText_SearchField1);
        zutat1 = editTextZutat1.getText().toString();
        editTextZutat2 =  findViewById(R.id.EditText_SearchField2);
        zutat2 = editTextZutat2.getText().toString();
        editTextZutat3 =  findViewById(R.id.EditText_SearchField3);
        zutat3 = editTextZutat3.getText().toString();
        editTextZutat4 =  findViewById(R.id.EditText_SearchField4);
        zutat4 = editTextZutat4.getText().toString();
        editTextZutat5 =  findViewById(R.id.EditText_SearchField5);
        zutat5 = editTextZutat5.getText().toString();

        //Datenbank-Methode getRecipeFit aus der Klasse RecipeMemoDbHelper auf einem erzeugten DB-Objekte ausführen
        //Rückgabewert in einen Cursor speichern
        Cursor fitRec = myDb.getRecipeFit(RecipeSearchActivity.zutat1, RecipeSearchActivity.zutat2,
                RecipeSearchActivity.zutat3, RecipeSearchActivity.zutat4, RecipeSearchActivity.zutat5);
        if(fitRec.getCount() == 0){
            //Nachricht zeigen für den Fall, dass keine
            showMessage("Error", "Keine Rezepte gefunden");
            return;
        }
        //rezeptlist Array leeren (durch vorherige Suchen könnte dieses gefüllt sein)
        rezeptlist.clear();
        //Daten aus dem Cursor in die Rezeptliste einfügen
        while (fitRec.moveToNext()){
            rezeptlist.add(fitRec.getString(0));
        }
        //Über einen Intent die nächste Klasse ResultListActivity aufrufen auf welcher die Rezeptliste verarbeitet wird
        Intent intent2 = new Intent( RecipeSearchActivity.this, ResultListActivity.class );
        startActivity(intent2);

        if( DBG ) Log.d( TAG, MNAME + "...exiting" );
    }

    //Methoden zum hinzufügen eines weiteren EditText für die Eingabe von Rezepten
    private void onClickAddIngredient() {

        final String MNAME = "onClickAddIngredient";
        if( DBG ) Log.v( TAG, MNAME + "entering..." );

        //Button zum weiteren hinzufügen
        Button button_AddIngredient = (Button) findViewById(R.id.Button_AddIngredient);
        button_AddIngredient.setVisibility(View.INVISIBLE);

        //Editext sichtbar
        LinearLayout layout3_recipe_search = (LinearLayout) findViewById(R.id.layout3_recipe_search);
        layout3_recipe_search.setVisibility(View.VISIBLE);

        if( DBG ) Log.d( TAG, MNAME + "...exiting" );
    }

    //Methoden zum hinzufügen eines weiteren EditText für die Eingabe von Rezepten
    private void onClickAddIngredient2() {

        final String MNAME = "onClickAddIngredient2";
        if( DBG ) Log.v( TAG, MNAME + "entering..." );

        Button button_AddIngredient2 = (Button) findViewById(R.id.Button_AddIngredient2);
        button_AddIngredient2.setVisibility(View.INVISIBLE);

        EditText editText_SearchField5 = (EditText) findViewById(R.id.EditText_SearchField5);
        editText_SearchField5.setVisibility(View.VISIBLE);

        if( DBG ) Log.d( TAG, MNAME + "...exiting" );
    }

    //Methode zum testen welche Rezepte abgespeichert sind
    //Mit onClickListener auf dem "showall" Button
    public void onClickShowAll(){
        btnShowAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        //Db-Methode getAllRecipes die alle rezepte zurückgibt in Cursor res ablegen
                       Cursor res = myDb.getAllRecipes();
                       if(res.getCount() == 0){
                           //Nachricht zeigen für den Fall, dass keine
                           showMessage("Error", "Keine Rezepte gefunden");
                           return;
                       }

                       //Auslesen und in den StringBuffer buffer speichern
                       StringBuffer buffer = new StringBuffer();
                       while (res.moveToNext()){
                           buffer.append("Rezept Nummer " + res.getString(0)+"\n");
                           buffer.append(res.getString(1)+"\n\n");
                       }
                       //Alle Rezepte anzeigen
                        showMessage("Rezepte", buffer.toString());
                    }

                }
        );
    }

    //erzeugen eines Alert Dialogs mit einer Nachricht
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
