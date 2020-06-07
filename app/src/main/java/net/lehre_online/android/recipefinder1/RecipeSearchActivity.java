package net.lehre_online.android.recipefinder1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class RecipeSearchActivity extends AppCompatActivity {

    static final boolean DBG        = MainActivity.DBG;
    static final String TAG         = "RecipeSearchActivity";

    RecipeMemoDbHelper myDb;

    private Button btnSearch;
    private Button btnAddIngredient;
    private Button btnAddIngredient2;
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

    public static  ArrayList<String> dummydaten = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String MNAME = "onCreate()";
        if (DBG) Log.d(TAG, MNAME + "entering...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_search);
        myDb = new RecipeMemoDbHelper(this);

        btnSearch = findViewById(R.id.Button_Search);
        btnShowAll = (Button) findViewById(R.id.Button_Show_All);

        onClickShowAll();


        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onClickSearch();
            }
        });

        btnAddIngredient = findViewById(R.id.Button_AddIngredient);
        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClickAddIngredient();
            }
        });

        btnAddIngredient2 = findViewById(R.id.Button_AddIngredient2);
        btnAddIngredient2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClickAddIngredient2();
            }
        });

        if(DBG)Log.d(TAG,MNAME +"entering...");



    }

    private void onClickSearch() {

        final String MNAME = "onClickSearch()";
        if( DBG ) Log.v( TAG, MNAME + "entering..." );

        editTextZutat1 = (EditText) findViewById(R.id.EditText_SearchField1);
        zutat1 = editTextZutat1.getText().toString();
        editTextZutat2 = (EditText) findViewById(R.id.EditText_SearchField2);
        zutat2 = editTextZutat2.getText().toString();
        editTextZutat3 = (EditText) findViewById(R.id.EditText_SearchField3);
        zutat3 = editTextZutat3.getText().toString();
        editTextZutat4 = (EditText) findViewById(R.id.EditText_SearchField4);
        zutat4 = editTextZutat4.getText().toString();
        editTextZutat5 = (EditText) findViewById(R.id.EditText_SearchField5);
        zutat5 = editTextZutat5.getText().toString();



        Cursor fitRec = myDb.getRecipeFit(RecipeSearchActivity.zutat1, RecipeSearchActivity.zutat2, RecipeSearchActivity.zutat3, RecipeSearchActivity.zutat4, RecipeSearchActivity.zutat5);
        if(fitRec.getCount() == 0){
            //Nachricht zeigen für den Fall, dass keine
            showMessage("Error", "Keine Rezepte gefunden");
            return;
        }
        dummydaten.clear();
        while (fitRec.moveToNext()){
            dummydaten.add(fitRec.getString(0));
        }


        Intent intent2 = new Intent( RecipeSearchActivity.this, ResultListActivity.class );
        startActivity(intent2);

        if( DBG ) Log.d( TAG, MNAME + "...exiting" );
    }

    private void onClickAddIngredient() {

        final String MNAME = "onClickAddIngredient";
        if( DBG ) Log.v( TAG, MNAME + "entering..." );

        Button button_AddIngredient = (Button) findViewById(R.id.Button_AddIngredient);
        button_AddIngredient.setVisibility(View.INVISIBLE);

        LinearLayout layout3_recipe_search = (LinearLayout) findViewById(R.id.layout3_recipe_search);
        layout3_recipe_search.setVisibility(View.VISIBLE);

        if( DBG ) Log.d( TAG, MNAME + "...exiting" );
    }

    private void onClickAddIngredient2() {

        final String MNAME = "onClickAddIngredient2";
        if( DBG ) Log.v( TAG, MNAME + "entering..." );

        Button button_AddIngredient2 = (Button) findViewById(R.id.Button_AddIngredient2);
        button_AddIngredient2.setVisibility(View.INVISIBLE);

        EditText editText_SearchField5 = (EditText) findViewById(R.id.EditText_SearchField5);
        editText_SearchField5.setVisibility(View.VISIBLE);

        if( DBG ) Log.d( TAG, MNAME + "...exiting" );
    }

    public void onClickShowAll(){
        btnShowAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                       Cursor res = myDb.getAllRecipes();
                       if(res.getCount() == 0){
                           //Nachricht zeigen für den Fall, dass keine
                           showMessage("Error", "Keine Rezepte gefunden");
                           return;
                       }

                       StringBuffer buffer = new StringBuffer();
                       while (res.moveToNext()){
                           buffer.append("Rezept Nummer " + res.getString(0)+"\n");
                           buffer.append(res.getString(1)+"\n\n");
                       }
                       //Alle Rezepte anzeigen
                        showMessage("Data", buffer.toString());
                    }

                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
