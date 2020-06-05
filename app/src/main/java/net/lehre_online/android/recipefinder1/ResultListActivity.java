package net.lehre_online.android.recipefinder1;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

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
            public void onItemClick(AdapterView<?> arrayAdapter, View view, final int position, long id) {

                ImageView RecipeImage = new ImageView(ResultListActivity.this);
                RecipeImage.setImageResource(R.drawable.recipefinderlogo);

                AlertDialog.Builder alertDialogRezept = new AlertDialog.Builder(ResultListActivity.this);

                        alertDialogRezept.setTitle(dummydaten.get(position));
                        alertDialogRezept.setMessage("Zutatenliste");
                        alertDialogRezept.setPositiveButton("Go to Recipe!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent3 = new Intent(ResultListActivity.this, RecipeResultActivity.class);
                                intent3.putExtra("Text_RecipeFinder", dummydaten.get(position));
                                startActivity(intent3);
                            }
                        });

                //alertDialogRezept.setView(RecipeImage);


                alertDialogRezept.create();
                alertDialogRezept.show();

            }
        });

        if(DBG)Log.d(TAG,MNAME +"entering...");
    }
}
