package net.lehre_online.android.recipefinder1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class RecipeSearchActivity extends AppCompatActivity {

    static final boolean DBG        = MainActivity.DBG;
    static final String TAG         = "RecipeSearchActivity";

    private Button btnSearch;

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

        if(DBG)Log.d(TAG,MNAME +"entering...");

    }

    private void onClickSearch() {

        final String MNAME = "onClickSearch()";
        if( DBG ) Log.v( TAG, MNAME + "entering..." );

        Intent intent2 = new Intent( RecipeSearchActivity.this, ResultListActivity.class );
        startActivity(intent2);

        if( DBG ) Log.d( TAG, MNAME + "...exiting" );
    }
}
