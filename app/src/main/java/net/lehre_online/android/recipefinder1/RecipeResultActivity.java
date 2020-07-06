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

/**Diese Klasse dient zur Anzeige der Rezeptbeschreibung eines Gerichts
 * @author Maxim Klameth
 * @version 05.07.2020
 */
public class RecipeResultActivity extends AppCompatActivity {

    //deklarieren der variablen rezeptname, Rezeptzutaten, myDb
    public String RezeptName;
    public String RezeptZutaten;
    RecipeMemoDbHelper myDb;

    //deklarieren der variablen bezeichnung
    public static ArrayList<String> bezeichnung = new ArrayList<>();

    //deklarieren der variablen Textview, textview2, RecipeImage
    TextView textview;
    TextView textview2;
    ImageView RecipeImage;

    //deklarieren der variablen btn_safe
    private Button btn_safe;

    static final boolean DBG        = MainActivity.DBG;
    static final String TAG         = "RecipeResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String MNAME = "onCreate()";
        if (DBG) Log.d(TAG, MNAME + "entering...");
        myDb = new RecipeMemoDbHelper(this);

        //layout setzen, aufruf recipe_result
        setContentView(R.layout.recipe_result);
        super.onCreate(savedInstanceState);

        /*hierbei wird dem texview aus dem recipe_result Layout, der Gerichtname übergeben, die in der ResultListActivity im intent als
         putExtra, mitübergeben wurde. Dieser wird dann dem textview eingfügt.
         */
        textview = findViewById(R.id.Text_RecipeName);
        RezeptName = getIntent().getExtras().getString("Text_RecipeFinder");
        textview.setText(RezeptName);

        /* Dieser if else Block, dient der Kontrolle darüber, welches Bild zu dem jeweiligen Rezept angezeigt wird.
            Wenn der name des gerichts einen gewissen String Wert hat, so wird aus der drawable folder das entsprechende Bild als Image gesetzt
         */
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

        /*hierbei wird dem texview aus dem recipe_result Layout, die Beschreibung des Gerichtes übergeben, die in der ResultListActivity im intent als
         putExtra, mitübergeben wurde. Dieser wird dann dem textview eingfügt.
         */
        textview2 = findViewById(R.id.Text_RecipeIngredientsList);
        RezeptZutaten = getIntent().getExtras().getString("Text_RecipeIngredientsList");
        textview2.setText("Beschreibung: " + bezeichnung);



    //Button erstellen
        btn_safe = findViewById(R.id.Button_SafeRecipe);
        //OnClick Listener erstellen
        btn_safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //safe Rezept Methode aufrufen
                myDb.safeRecipe(MainActivity.e_mail, RezeptName);
                //Feedback: Rezept gespeichert
                Toast.makeText(RecipeResultActivity.this,"Rezept gespeichert",Toast.LENGTH_LONG).show();
            }
        });
    }



    private void showMessage(String error, String keine_rezepte_gefunden) {
    }


}
