package net.lehre_online.android.recipefinder1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class RecipeResultActivity extends AppCompatActivity {

    static final boolean DBG        = MainActivity.DBG;
    static final String TAG         = "RecipeResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String MNAME = "onCreate()";
        if (DBG) Log.d(TAG, MNAME + "entering...");

        setContentView(R.layout.recipe_result);
        super.onCreate(savedInstanceState);
    }
}
