package net.lehre_online.android.recipefinder1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ResultListActivity extends AppCompatActivity {

    ListView listview;

    static final boolean DBG = MainActivity.DBG;
    static final String TAG = "ResultListActivity";

    protected void onCreate(Bundle savedInstanceState) {
        final String MNAME = "onCreate()";
        if (DBG) Log.d(TAG, MNAME + "entering...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_resultlist);

        listview = (ListView)findViewById(R.id.listview_recipelist);

        final ArrayList<String> dummydaten = new ArrayList<>();

        dummydaten.add("Spaghetti Bolognese");
        dummydaten.add("Spaghetti Carbonara");
        dummydaten.add("Spaghetti Frutti di Mare");
        dummydaten.add("Pizza");
        dummydaten.add("Knoblauchbrot");
        dummydaten.add("Thunfischsalat");

        ArrayAdapter arrayAdapterTest = new ArrayAdapter(this,android.R.layout.simple_list_item_1,dummydaten);

        listview.setAdapter(arrayAdapterTest);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arrayAdapter, View view, int position, long id) {
               // Log.d(TAG, "onItemClick: name " + dummydaten.get(position));
               // Toast.makeText(ResultListActivity.this, "You clicked on: " + dummydaten.get(position),Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alertDialogRezept = new AlertDialog.Builder(ResultListActivity.this);
                final View customLayout = getLayoutInflater().inflate(R.layout.recipe_resultdialog,null);
                alertDialogRezept.setView(customLayout);


                alertDialogRezept.setMessage(dummydaten.get(position));


                alertDialogRezept.create();
                alertDialogRezept.show();

                //Intent intent4 = new Intent(ResultListActivity.this, RecipeResultActivity.class);
                //startActivity(intent4);
            }
        });


    }
}
