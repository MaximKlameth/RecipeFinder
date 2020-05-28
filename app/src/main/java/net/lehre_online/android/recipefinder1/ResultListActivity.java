package net.lehre_online.android.recipefinder1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultListActivity extends AppCompatActivity {

    static final boolean DBG = MainActivity.DBG;
    static final String TAG = "ResultListActivity";

    protected void onCreate(Bundle savedInstanceState) {
        final String MNAME = "onCreate()";
        if (DBG) Log.d(TAG, MNAME + "entering...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_resultlist);



    }
}
