package net.lehre_online.android.recipefinder1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class RecipeSearchActivity extends AppCompatActivity {

    static final boolean DBG        = MainActivity.DBG;
    static final String TAG         = "RecipeSearchActivity";

    private Button btnSearch;
    private Button btnAddIngredient;
    private Button btnAddIngredient2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String MNAME = "onCreate()";
        if (DBG) Log.d(TAG, MNAME + "entering...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_search);

        btnSearch = findViewById(R.id.Button_Search);
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
}
