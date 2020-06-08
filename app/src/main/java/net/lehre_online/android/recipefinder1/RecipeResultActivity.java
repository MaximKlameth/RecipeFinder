package net.lehre_online.android.recipefinder1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class RecipeResultActivity extends AppCompatActivity {

    public String RezeptName;
    public String RezeptZutaten;

    TextView textview;
    TextView textview2;

    static final boolean DBG        = MainActivity.DBG;
    static final String TAG         = "RecipeResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String MNAME = "onCreate()";
        if (DBG) Log.d(TAG, MNAME + "entering...");

        setContentView(R.layout.recipe_result);
        super.onCreate(savedInstanceState);

        textview = findViewById(R.id.Text_RecipeName);
        RezeptName = getIntent().getExtras().getString("Text_RecipeFinder");
        textview.setText(RezeptName);

        textview2 = findViewById(R.id.Text_RecipeIngredientsList);
        RezeptZutaten = getIntent().getExtras().getString("Text_RecipeIngredientsList");
        textview2.setText("Zutatenliste: " + RezeptZutaten);

    }



}
