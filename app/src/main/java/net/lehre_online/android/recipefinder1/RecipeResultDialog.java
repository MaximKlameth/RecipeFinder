package net.lehre_online.android.recipefinder1;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class RecipeResultDialog extends DialogFragment {

    static final boolean DBG = MainActivity.DBG;
    static final String TAG = "RecipeResultActivity";

    /*protected void onCreate(Bundle savedInstanceState) {
        final String MNAME = "onCreate()";
        if (DBG) Log.d(TAG, MNAME + "entering...");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_resultdialog);
    }*/

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.recipe_resultdialog, null));

        return builder.create();
    }
}
