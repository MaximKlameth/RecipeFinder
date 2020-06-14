package net.lehre_online.android.recipefinder1;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipeResultActivity extends AppCompatActivity {

    public String RezeptName;
    public String RezeptZutaten;
    RecipeMemoDbHelper myDb;

    public static ArrayList<String> bezeichnung = new ArrayList<>();

    TextView textview;
    TextView textview2;
    ImageView RecipeImage;


    private Button btn_safe;

    static final boolean DBG        = MainActivity.DBG;
    static final String TAG         = "RecipeResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String MNAME = "onCreate()";
        if (DBG) Log.d(TAG, MNAME + "entering...");
        myDb = new RecipeMemoDbHelper(this);

        setContentView(R.layout.recipe_result);
        super.onCreate(savedInstanceState);

        textview = findViewById(R.id.Text_RecipeName);
        RezeptName = getIntent().getExtras().getString("Text_RecipeFinder");
        textview.setText(RezeptName);

        if(RezeptName.equals("Spaghetti Bolognese")) {
            RecipeImage = findViewById(R.id.Image_Rezept);
            RecipeImage.setImageResource(R.drawable.spaghettibolognese);
        } else if(RezeptName.equals("Hähnchen Geschnetzeltes")) {
            RecipeImage = findViewById(R.id.Image_Rezept);
            RecipeImage.setImageResource(R.drawable.huhn);
        } else if(RezeptName.equals("Annas Massaman-Curry")) {
            RecipeImage = findViewById(R.id.Image_Rezept);
            RecipeImage.setImageResource(R.drawable.massamancurry);
        } else if(RezeptName.equals("Erdäpfel-Bauzerl")) {
            RecipeImage = findViewById(R.id.Image_Rezept);
            RecipeImage.setImageResource(R.drawable.bauzerl);
        } else if(RezeptName.equals("Spitzkohl-Champignon-Hack-Pfanne mit Reis")) {
            RecipeImage = findViewById(R.id.Image_Rezept);
            RecipeImage.setImageResource(R.drawable.spitzkohlchampignonhackpfanne);
        }

        Cursor fitbes = myDb.getBeschreibung(RezeptName);
        if(fitbes.getCount() == 0){
            //Nachricht zeigen für den Fall, dass keine
            showMessage("Error", "Keine Beschreibung gefunden");
            return;
        }
        bezeichnung.clear();
        while (fitbes.moveToNext()){
            bezeichnung.add(fitbes.getString(0));
        }

        textview2 = findViewById(R.id.Text_RecipeIngredientsList);
        RezeptZutaten = getIntent().getExtras().getString("Text_RecipeIngredientsList");
        textview2.setText("Beschreibung: " + bezeichnung);




        btn_safe = findViewById(R.id.Button_SafeRecipe);
        btn_safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDb.safeRecipe(MainActivity.e_mail, RezeptName);
                Toast.makeText(RecipeResultActivity.this,"Rezept gespeichert",Toast.LENGTH_LONG).show();
            }
        });


    }



    private void showMessage(String error, String keine_rezepte_gefunden) {
    }


}
